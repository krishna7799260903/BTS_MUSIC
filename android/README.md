# 💜 BTS Music — Native Android App (Jetpack Compose)

[![Platform](https://img.shields.io/badge/Platform-Android%208.0%2B%20(API%2026--35)-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.10-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Media3 ExoPlayer](https://img.shields.io/badge/AndroidX%20Media3-1.4.1-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://developer.android.com/media/media3)
[![Room](https://img.shields.io/badge/Room%20DB-Offline%20Cache-009688?style=for-the-badge)](https://developer.android.com/training/data-storage/room)
[![Bluetooth BLE](https://img.shields.io/badge/Web%20%26%20Android%20BLE-ARMY%20Bomb-0082FC?style=for-the-badge&logo=bluetooth&logoColor=white)](https://developer.android.com/guide/topics/connectivity/bluetooth/ble)

A production-grade, native Android music streaming and fandom experience app dedicated entirely to BTS (Bangtan Sonyeondan) and ARMY. Built with **100% Kotlin**, **Jetpack Compose**, **Material Design 3**, and **AndroidX Media3 (ExoPlayer)** using **YouTube Music** as its primary audio backend source.

---

## 📱 Feature Mapping & Implementation

| # | Feature | Native Architecture Component |
|---|---|---|
| **1** | **YouTube Account Sync & Library** | `YouTubeMusicService.kt` + `LibraryViewModel.kt` + `LibraryScreen.kt`<br>• One-tap Google / YouTube account login<br>• Syncs playlists, liked songs, and solo works for all 7 members |
| **2** | **Streaming & Offline Downloads** | `DownloadRepository.kt` + `AppDatabase.kt` + `TrackDao.kt` + `DownloadEntity.kt`<br>• Streams high-fidelity audio streams from YouTube Music<br>• Full offline caching with file I/O & Room DB persistence |
| **3** | **Advanced Audio Engine** | `AudioEngineController.kt` + `AudioEngineScreen.kt`<br>• 5-band Graphic Equalizer (`android.media.audiofx.Equalizer`)<br>• Borahae Bass Boost & 3D Spatial Virtualizer<br>• Real-time Pitch Shifting (-6 to +6 semitones)<br>• Tempo / Speed control (0.5x to 2.0x)<br>• Automatic Silence Skipping (`skipSilenceEnabled = true`) |
| **4** | **Interactive Player & Mini Player** | `MiniPlayer.kt` + `FullPlayerScreen.kt` + `VisualizerProcessor.kt`<br>• Floating docked mini-player with swipe/tap to expand<br>• Full-bleed album art with ambient glow<br>• Animated 24-band frequency visualizer wave |
| **5** | **Synchronized Lyrics & Fanchants** | `SyncedLyricsScreen.kt` + `LyricLine.kt`<br>• Millisecond-precise auto-scrolling karaoke lyrics<br>• Script toggle: Korean Hangul, Romanized, English<br>• Highlighted ARMY Fanchant cues with gold badge alerts |
| **6** | **"Bora-Live" Concert Stream** | `BoraLiveScreen.kt` + `BoraLiveViewModel.kt`<br>• Simulated 4K stadium concert view<br>• Multi-Angle camera switching (Main Stage, Jungkook, V, Jimin, Rapline, ARMY Ocean)<br>• Real-time simulated fan chat feed<br>• Interactive "Cheer 💜" button that bursts floating purple hearts |
| **7** | **Social "Listen Together" Mode** | `ListenTogetherScreen.kt` + `ListenTogetherManager.kt`<br>• Host room with custom 6-digit code (e.g., `ARMY-777`)<br>• Synchronized track, timestamp, and playback status across participants<br>• Active listener avatars & bias indicators |
| **8** | **ARMY Bomb Bluetooth Integration** | `ArmyBombBleManager.kt` + `ArmyBombWidget.kt` + `ArmyBombViewModel.kt`<br>• Android BLE scanning (`BluetoothLeScanner`) & GATT rhythm packets<br>• Built-in Software Simulator mode with interactive 3D glowing orb |

---

## 🛠️ How to Open & Run in Android Studio

1. Open **Android Studio** (Koala / Ladybug / Jellyfish or newer).
2. Click **File > Open...**
3. Navigate to and select this folder:
   ```
   C:\Users\User\Desktop\bts\android
   ```
4. Allow Gradle to sync dependencies.
5. Select an Android emulator (API 26+) or connect your physical Android device.
6. Click **Run (Shift + F10)** to install and launch `com.army.btsmusic`!

---

## 📁 Package Organization

```
com.army.btsmusic/
├── BtsMusicApp.kt                 # Application class & DI singleton container
├── MainActivity.kt                # Single-activity Compose host
├── theme/                         # Material 3 Borahae Theme & Typography tokens
├── model/                         # Domain models (Track, Member, LyricLine, AudioEffects, etc.)
├── data/
│   ├── local/                     # Room DB (AppDatabase, TrackDao, DownloadEntity)
│   ├── remote/                    # YouTube Music / InnerTube Service
│   ├── repository/                # Unified Music & Download Repositories
│   └── BtsDiscographyCatalog.kt   # Built-in BTS discography & lyrics database
├── player/                        # ExoPlayer Audio Engine, Visualizer & MediaSessionService
├── bluetooth/                     # ARMY Bomb Bluetooth Low Energy (BLE) & Simulator
├── social/                        # Listen Together room manager & peer sync
├── ui/
│   ├── components/                # MiniPlayer, AudioVisualizer, ArmyBombWidget, FloatingHearts
│   ├── navigation/                # Bottom Navigation bar & scaffold
│   └── screens/                   # Home, FullPlayer, SyncedLyrics, Equalizer, Library, BoraLive, Social
└── viewmodel/                     # ViewModels for all screens
```
