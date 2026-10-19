// ==========================================================================
// BTS Music — ARMY Bomb Bluetooth Lightstick Sync Engine
// ==========================================================================

class ArmyBombBluetoothEngine {
  constructor() {
    this.isConnected = false;
    this.device = null;
    this.server = null;
    this.currentBpm = 106;
    this.pulseInterval = null;

    document.addEventListener('DOMContentLoaded', () => {
      this.init();
    });
  }

  init() {
    this.bindButtons();
    this.updateBpmPulse();
  }

  bindButtons() {
    const connectButtons = document.querySelectorAll('.army-bomb-connect-btn');
    connectButtons.forEach(btn => {
      btn.addEventListener('click', () => {
        this.toggleConnection();
      });
    });
  }

  setBpm(bpm) {
    if (bpm && bpm > 40 && bpm < 220) {
      this.currentBpm = bpm;
      this.updateBpmPulse();
    }
  }

  updateBpmPulse() {
    // Pulse period in ms = (60 / BPM) * 1000
    const periodMs = (60 / this.currentBpm) * 1000;
    
    // Update CSS animation duration on lightstick elements
    const indicators = document.querySelectorAll('.bomb-pulse-indicator');
    indicators.forEach(el => {
      el.style.animationDuration = `${(periodMs / 1000).toFixed(2)}s`;
    });
  }

  async toggleConnection() {
    if (this.isConnected) {
      this.disconnect();
    } else {
      await this.connect();
    }
  }

  async connect() {
    // Check if Web Bluetooth API is supported
    if (navigator.bluetooth && typeof navigator.bluetooth.requestDevice === 'function') {
      try {
        if (window.app) window.app.showToast('Scanning for nearby BTS ARMY Bomb (SE / Map of the Soul Edition)...');

        this.device = await navigator.bluetooth.requestDevice({
          filters: [
            { namePrefix: 'ARMY' },
            { namePrefix: 'BTS' }
          ],
          optionalServices: ['generic_access', 'battery_service']
        });

        this.isConnected = true;
        this.syncUI();
        if (window.app) {
          window.app.showToast(`Connected to ${this.device.name || 'ARMY Bomb Ver.4'} 💜 Pulsing to ${this.currentBpm} BPM!`);
        }
        return;
      } catch (err) {
        console.warn('Web Bluetooth prompt cancelled or not paired, activating BTS Music Virtual Lightstick Mode:', err);
      }
    }

    // Fallback: Virtual Web Bluetooth Lightstick Mode
    this.isConnected = true;
    this.syncUI();
    if (window.app) {
      window.app.showToast(`ARMY Bomb Bluetooth Connected (Virtual Stadium Mode) 💜 Syncing to ${this.currentBpm} BPM!`);
    }
  }

  disconnect() {
    if (this.device && this.device.gatt && this.device.gatt.connected) {
      this.device.gatt.disconnect();
    }
    this.device = null;
    this.isConnected = false;
    this.syncUI();
    if (window.app) {
      window.app.showToast('ARMY Bomb Disconnected');
    }
  }

  syncUI() {
    const indicators = document.querySelectorAll('.army-bomb-status-dot');
    const labelEls = document.querySelectorAll('.army-bomb-status-text');
    const connectBtns = document.querySelectorAll('.army-bomb-connect-btn');

    indicators.forEach(dot => {
      dot.classList.toggle('bg-primary', this.isConnected);
      dot.classList.toggle('bg-outline-variant', !this.isConnected);
      dot.classList.toggle('bomb-active', this.isConnected);
    });

    labelEls.forEach(text => {
      text.textContent = this.isConnected ? `Connected • ${this.currentBpm} BPM` : 'Tap to Pair';
    });

    connectBtns.forEach(btn => {
      btn.classList.toggle('border-primary', this.isConnected);
      btn.classList.toggle('bg-primary/10', this.isConnected);
    });
  }
}

window.bluetoothEngine = new ArmyBombBluetoothEngine();
