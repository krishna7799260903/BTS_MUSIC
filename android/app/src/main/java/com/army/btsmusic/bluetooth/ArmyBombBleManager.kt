package com.army.btsmusic.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.UUID

enum class LightstickConnectionState {
    DISCONNECTED,
    SCANNING,
    CONNECTING,
    CONNECTED_HARDWARE,
    CONNECTED_SIMULATOR
}

class ArmyBombBleManager(private val context: Context) {

    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private var pulseJob: Job? = null

    private val bluetoothManager: BluetoothManager? = try {
        context.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
    } catch (e: Throwable) { null }
    private val bluetoothAdapter: BluetoothAdapter? = try {
        bluetoothManager?.adapter
    } catch (e: Throwable) { null }

    private var bluetoothGatt: BluetoothGatt? = null
    private var writeCharacteristic: BluetoothGattCharacteristic? = null

    private val _connectionState = MutableStateFlow(LightstickConnectionState.DISCONNECTED)
    val connectionState: StateFlow<LightstickConnectionState> = _connectionState.asStateFlow()

    private val _connectedDeviceName = MutableStateFlow<String?>("BTS ARMY Bomb Ver. 4")
    val connectedDeviceName: StateFlow<String?> = _connectedDeviceName.asStateFlow()

    private val _currentLightColor = MutableStateFlow(0xFF8B5CF6) // Borahae Purple
    val currentLightColor: StateFlow<Long> = _currentLightColor.asStateFlow()

    private val _lightGlowIntensity = MutableStateFlow(0.8f)
    val lightGlowIntensity: StateFlow<Float> = _lightGlowIntensity.asStateFlow()

    // BTS Lightstick GATT UUIDs
    private val ARMY_BOMB_SERVICE_UUID = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb")
    private val ARMY_BOMB_CHAR_UUID = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb")

    @SuppressLint("MissingPermission")
    fun startBleScan() {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled) {
            // Automatically fall back to software simulator if hardware Bluetooth is disabled
            startSoftwareSimulator()
            return
        }

        _connectionState.value = LightstickConnectionState.SCANNING
        val scanner = bluetoothAdapter.bluetoothLeScanner
        if (scanner == null) {
            startSoftwareSimulator()
            return
        }

        val scanCallback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                val device = result?.device ?: return
                val name = device.name ?: ""
                if (name.contains("BTS", ignoreCase = true) || name.contains("ARMY", ignoreCase = true) || name.contains("Bomb", ignoreCase = true)) {
                    scanner.stopScan(this)
                    connectToDevice(device)
                }
            }

            override fun onScanFailed(errorCode: Int) {
                startSoftwareSimulator()
            }
        }

        try {
            scanner.startScan(scanCallback)
            // Stop scanning after 5s and fallback to simulator if no hardware device is detected
            scope.launch {
                delay(5000)
                if (_connectionState.value == LightstickConnectionState.SCANNING) {
                    try { scanner.stopScan(scanCallback) } catch (e: Exception) { }
                    startSoftwareSimulator()
                }
            }
        } catch (e: SecurityException) {
            startSoftwareSimulator()
        }
    }

    @SuppressLint("MissingPermission")
    private fun connectToDevice(device: BluetoothDevice) {
        _connectionState.value = LightstickConnectionState.CONNECTING
        _connectedDeviceName.value = device.name ?: "ARMY Bomb"

        bluetoothGatt = device.connectGatt(context, false, object : BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    _connectionState.value = LightstickConnectionState.CONNECTED_HARDWARE
                    gatt?.discoverServices()
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    _connectionState.value = LightstickConnectionState.DISCONNECTED
                }
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
                val service = gatt?.getService(ARMY_BOMB_SERVICE_UUID)
                writeCharacteristic = service?.getCharacteristic(ARMY_BOMB_CHAR_UUID)
                startRhythmSyncLoop()
            }
        })
    }

    fun startSoftwareSimulator() {
        _connectionState.value = LightstickConnectionState.CONNECTED_SIMULATOR
        _connectedDeviceName.value = "ARMY Bomb Ver. 4 (Software Simulator)"
        startRhythmSyncLoop()
    }

    fun disconnect() {
        pulseJob?.cancel()
        try {
            bluetoothGatt?.disconnect()
            bluetoothGatt?.close()
        } catch (e: Exception) { }
        bluetoothGatt = null
        _connectionState.value = LightstickConnectionState.DISCONNECTED
    }

    fun setLightColor(colorHex: Long) {
        _currentLightColor.value = colorHex
        sendColorPacket(colorHex)
    }

    private fun startRhythmSyncLoop() {
        pulseJob?.cancel()
        pulseJob = scope.launch {
            var pulse = 0f
            while (isActive) {
                pulse += 0.2f
                val intensity = (Math.sin(pulse.toDouble()).toFloat() + 1f) / 2f
                _lightGlowIntensity.value = 0.4f + (intensity * 0.6f)
                delay(80)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun sendColorPacket(colorHex: Long) {
        val r = ((colorHex shr 16) and 0xFF).toByte()
        val g = ((colorHex shr 8) and 0xFF).toByte()
        val b = (colorHex and 0xFF).toByte()
        val packet = byteArrayOf(0x01.toByte(), r, g, b, 0x00.toByte())

        writeCharacteristic?.let { char ->
            char.value = packet
            bluetoothGatt?.writeCharacteristic(char)
        }
    }
}
