# 💜 BTS Music — BTS & ARMY Audio-Visual Experience

[![BTS](https://img.shields.io/badge/BTS-Borahae%20💜-8B5CF6?style=for-the-badge)](https://ibighit.com/bts)
[![Android](https://img.shields.io/badge/Android%20App-Jetpack%20Compose-3DDC84?style=for-the-badge&logo=android&logoColor=white)](android/)
[![Media3](https://img.shields.io/badge/Audio%20Engine-Media3%20ExoPlayer-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](android/)
[![Vanilla JS](https://img.shields.io/badge/Web%20App-HTML5%20%2F%20CSS3%20%2F%20JS-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)](index.html)
[![Web Bluetooth](https://img.shields.io/badge/BLE-ARMY%20Bomb%20Sync-0082FC?style=for-the-badge&logo=bluetooth&logoColor=white)](https://web.dev/bluetooth/)

> **A bespoke, high-fidelity audio and video streaming ecosystem crafted exclusively for the global BTS fandom (ARMY). Features both a responsive Web Application and a full-featured native Android application built with Kotlin, Jetpack Compose, Material Design 3, and AndroidX Media3 (ExoPlayer).**

---

### 📱 Native Android App (Jetpack Compose)
A complete native Android app located in [`android/`](android/) ready to build and run in Android Studio. Powered by YouTube Music as its primary backend:
1. **YouTube Account Sync & Library:** Google / YouTube account login, library sync across group and 7 solo releases.
2. **Offline Downloads:** High-fidelity audio downloads with local Room database storage.
3. **Advanced Audio Engine:** 5-band Equalizer, Borahae Bass Boost, 3D Virtualizer, real-time pitch shifting (-6 to +6 st), tempo shifting (0.5x to 2.0x), and automatic silence skipping.
4. **Interactive Player & Mini-Player:** Smooth animated 24-band frequency visualizer wave.
5. **Synchronized Lyrics & Fanchants:** Live scrolling lyrics with Hangul, Romanized, English scripts + highlighted fanchants.
6. **"Bora-Live" Concert Stream:** Multi-angle camera switching (Main Stage, Jungkook, V, Jimin, Rapline, ARMY Ocean), live chat, and floating purple heart cheers.
7. **Social "Listen Together" Mode:** Real-time synchronized playback rooms with custom codes and participant avatars.
8. **ARMY Bomb Bluetooth Integration:** Android BLE scanning + built-in software simulator.


---

## ✨ Features

### 🎵 1. Immersive Audio Player & Visualizer
- **Full Player & Mini Player:** Seamless transition between a docked mini-player and an expansive, full-bleed immersive player.
- **Dynamic Waveform / Audio Visualizer:** Real-time animated audio visualizer synced with playback.
- **Queue & Playback Controls:** Shuffle, repeat, seek progress bar, volume slider, and track queuing.

### 🎤 2. Synchronized Lyrics & Fanchant Guide
- Real-time karaoke-style scrolling lyrics synchronized with track playback.
- Dual-display Korean, Romanized, and English lyric translations.
- Highlighted ARMY fanchants to sing along with BTS in real time.

### 📹 3. Live & Video Concert Streaming
- Simulated live stadium concert broadcasts and Bangtan Bomb video player.
- **Multi-Angle Camera Switching:** Switch between Main Stage, Fancam, and Member focus angles.
- **Interactive Community Chat:** Simulated live ARMY chat stream with real-time comments.
- **Floating Purple Hearts:** Interactive cheer button that showers the screen with Borahae hearts.

### 💜 4. ARMY Hub & Complete Discography
- **OT7 & Soloist Filter:** Browse releases by BTS as a whole, or explore solo works from RM, Jin, SUGA / Agust D, j-hope, Jimin, V, and Jung Kook.
- **Eras & Albums:** Explore iconic eras from *2 Cool 4 Skool*, *The Most Beautiful Moment in Life (HYYH)*, *Wings*, *Love Yourself*, *Map of the Soul*, *BE*, and *Proof*.
- **Streaming Milestone Tracker:** Community progress bars toward global Spotify and YouTube streaming goals.
- **Fan Playlists:** Curated mood playlists (Chill Bangtan, Workout Hype, Deep Lore & Ballads).

### 💡 5. Bluetooth ARMY Bomb Synchronization
- Connect your official BTS lightstick via the Web Bluetooth API (or run in interactive simulation mode).
- Light color and pulse effects dynamically pulse to the beat of each song and stage event.

### 🌗 6. Dual-Mode Borahae Aesthetic
- **Obsidian Dark Mode:** Deep cosmic purples and blacks for late-night streaming and concert immersion.
- **Lilac Light Mode:** Crisp, airy lavender hues for bright daytime listening.
- Mobile-responsive design optimized for touch screens and desktop browsers.

---

## 📂 Project Structure

```
BTS_MUSIC/
├── index.html                 # Core application shell & view markup
├── css/
│   └── style.css              # Custom styling, responsive layout, animations, Borahae themes
├── js/
│   ├── app.js                 # App initialization, tab navigation, theme switching & search
│   ├── data.js                # BTS discography, solo albums, tracklists, lyrics & playlists
│   ├── player.js              # Audio engine, HTML5 audio, progress bar, queue & visualizer
│   ├── lyrics.js              # Timed lyrics parser & synchronized fanchant renderer
│   ├── live.js                # Concert stream view, multi-angle cams & live fan reactions
│   ├── army.js                # Discography filter, member spotlights & milestone goals
│   └── bluetooth.js           # ARMY Bomb Web Bluetooth connection & pulse visualizer
├── audio/                     # Audio files (.m4a)
├── stitch_bts_fan_music_player_ui (1)/ # Design mockups and screen UI blueprints
├── Product requriment doc (PRD).txt    # Product Requirements Document
├── .gitignore                 # Ignored files (system, IDE, and private records)
└── README.md                  # Project documentation
```

---

## 🚀 Quick Start (Running Locally)

This project runs as a pure frontend web application without needing complex build steps or external dependencies.

### Option 1: Direct File
Simply open `index.html` in any modern web browser (Google Chrome, Microsoft Edge, Brave, Firefox, Safari):
- Double-click `index.html`, or drag and drop it into your browser.

### Option 2: Live Server / Local Web Server (Recommended)
For full Web Bluetooth API and audio streaming performance, serve the directory using any HTTP server:

```bash
# Using Python 3
python -m http.server 8000

# Using Node / npx
npx serve .
```
Then visit `http://localhost:8000` in your browser.

---

## 🛠️ Built With

- **HTML5** — Semantic, accessible markup
- **CSS3 / Vanilla CSS** — Custom properties (CSS variables), CSS Grid, Flexbox, glassmorphic effects, and keyframe animations
- **JavaScript (ES6+)** — Modular DOM manipulation, state management, and event handling
- **Web Audio API** — High-performance audio playback and visualization
- **Web Bluetooth API** — Lightstick peripheral connectivity

---

## 💜 Borahae (보라해)

> *"Purple is the last color of the rainbow colors, so it means I will trust and love you for a long time."*  
> — **Kim Taehyung (V)**

*Created with love for BTS & ARMY worldwide.*
