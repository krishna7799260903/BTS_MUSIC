package com.army.btsmusic.viewmodel

import androidx.lifecycle.ViewModel
import com.army.btsmusic.bluetooth.ArmyBombBleManager
import com.army.btsmusic.bluetooth.LightstickConnectionState
import kotlinx.coroutines.flow.StateFlow

class ArmyBombViewModel(
    val bleManager: ArmyBombBleManager
) : ViewModel() {

    val connectionState: StateFlow<LightstickConnectionState> = bleManager.connectionState
    val connectedDeviceName: StateFlow<String?> = bleManager.connectedDeviceName
    val currentLightColor: StateFlow<Long> = bleManager.currentLightColor
    val lightGlowIntensity: StateFlow<Float> = bleManager.lightGlowIntensity

    val colorPresets = listOf(
        "Borahae Purple" to 0xFF8B5CF6,
        "Neon Orchid" to 0xFFE879F9,
        "Ocean Cyan" to 0xFF38BDF8,
        "Fire Amber" to 0xFFFB923C,
        "Diamond White" to 0xFFFFFFFF,
        "Crimson Seven" to 0xFFF43F5E
    )

    fun startScan() {
        bleManager.startBleScan()
    }

    fun startSimulator() {
        bleManager.startSoftwareSimulator()
    }

    fun disconnect() {
        bleManager.disconnect()
    }

    fun selectColor(colorHex: Long) {
        bleManager.setLightColor(colorHex)
    }
}
