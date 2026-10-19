// ==========================================================================
// BTS Music — Audio Player & YouTube Music Streaming Engine
// ==========================================================================

class AudioPlayerEngine {
  constructor() {
    this.tracks = window.BTS_DATA.tracks || [];
    this.currentIndex = 0;
    this.isPlaying = false;
    this.isShuffle = false;
    this.repeatMode = 'all'; // 'none' | 'all' | 'one'
    this.currentTime = 0;
    this.isDraggingScrubber = false;
    this.pendingPlay = false;

    // Dual Playback Modes: 'studio' (offline master audio) | 'yt' (YouTube Music stream)
    this.playMode = 'studio';

    // Native HTML5 Audio Engine for offline master sound
    this.audio = new Audio();
    this.audio.preload = 'auto';

    // YouTube Stream Player state
    this.ytPlayer = null;
    this.ytReady = false;
    this.isVideoDockOpen = false;
    this.isYtViewActive = false; // toggle on full player screen

    // Playback ticker for YouTube Music sync
    this.ytTicker = null;

    // Load favorites from localStorage
    try {
      this.favorites = new Set(JSON.parse(localStorage.getItem('bts_music_favorites') || '["black-swan", "standing-next-to-you", "seven", "like-crazy"]'));
    } catch (e) {
      this.favorites = new Set(['black-swan', 'standing-next-to-you', 'seven', 'like-crazy']);
    }

    this.duration = this.currentTrack ? this.currentTrack.duration : 206;
    this.initNativeAudio();
    this.initYouTubeAPI();
    this.initWaveformVisualizer();

    document.addEventListener('DOMContentLoaded', () => {
      this.initPlayerBindings();
      this.syncUI();
      if (window.lyricsEngine && this.currentTrack) {
        window.lyricsEngine.loadLyrics(this.currentTrack.lyrics);
      }
      this.initUserGestureUnlock();
    });
  }

  get currentTrack() {
    return this.tracks[this.currentIndex] || this.tracks[0];
  }

  // Native HTML5 Audio Core Setup
  initNativeAudio() {
    const track = this.currentTrack;
    if (track && track.audioSrc) {
      this.audio.src = track.audioSrc;
    }

    this.audio.addEventListener('loadedmetadata', () => {
      if (this.playMode === 'studio' && this.audio.duration && !isNaN(this.audio.duration) && this.audio.duration > 0) {
        this.duration = this.audio.duration;
      }
      this.syncTimeline();
    });

    this.audio.addEventListener('timeupdate', () => {
      if (this.playMode === 'studio' && !this.isDraggingScrubber) {
        this.currentTime = this.audio.currentTime;
        if (this.audio.duration && !isNaN(this.audio.duration) && this.audio.duration > 0) {
          this.duration = this.audio.duration;
        }
        this.syncTimeline();
      }
    });

    this.audio.addEventListener('play', () => {
      if (this.playMode === 'studio') {
        this.isPlaying = true;
        this.syncUI();
      }
    });

    this.audio.addEventListener('pause', () => {
      if (this.playMode === 'studio') {
        this.isPlaying = false;
        this.syncUI();
      }
    });

    this.audio.addEventListener('ended', () => {
      if (this.playMode === 'studio') {
        this.onTrackEnded();
      }
    });

    // Fallback hierarchy: local master audio → Apple Music preview stream → YouTube Music
    this.audio.addEventListener('error', () => {
      if (this.playMode !== 'studio') return;
      const track = this.currentTrack;
      if (track && track.audioFallback && this.audio.src.indexOf(track.audioFallback) === -1) {
        console.warn('Native audio source unavailable, trying preview fallback for:', track.title);
        this.audio.src = track.audioFallback;

        // Keep playing if the user had already hit play on the dead source
        this.audio.play().catch(() => {});
        return;
      }
      console.warn('All native sources unavailable, switching to YouTube Music stream for:', (track && track.title) || 'unknown track');
      this.switchToYouTubeMusicStream(this.isPlaying || this.pendingPlay);
      this.pendingPlay = false;
    });
  }

  // Allow one-tap audio unlock across all browsers
  initUserGestureUnlock() {
    const unlockAudio = () => {
      if (this.pendingPlay) {
        this.pendingPlay = false;
        this.play();
      }
    };

    window.addEventListener('click', unlockAudio, { once: true });
    window.addEventListener('keydown', unlockAudio, { once: true });
    window.addEventListener('touchstart', unlockAudio, { once: true });
  }

  initYouTubeAPI() {
    if (!window.YT) {
      const tag = document.createElement('script');
      tag.src = 'https://www.youtube.com/iframe_api';
      const firstScriptTag = document.getElementsByTagName('script')[0];
      if (firstScriptTag && firstScriptTag.parentNode) {
        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
      } else {
        document.head.appendChild(tag);
      }
    }

    window.onYouTubeIframeAPIReady = () => {
      this.initYouTubePlayerInstance();
    };
  }

  initYouTubePlayerInstance() {
    const container = document.getElementById('yt-audio-container');
    if (!container || !window.YT) return;

    try {
      const playerVars = {
        autoplay: 0,
        controls: 1,
        disablekb: 0,
        fs: 1,
        modestbranding: 1,
        playsinline: 1,
        rel: 0
      };

      if (window.location.protocol.startsWith('http') && window.location.origin && window.location.origin !== 'null') {
        playerVars.origin = window.location.origin;
      }

      this.ytPlayer = new YT.Player('yt-audio-container', {
        height: '100%',
        width: '100%',
        videoId: this.currentTrack ? this.currentTrack.youtubeId : 'UNo0TG9LwwI',
        playerVars: playerVars,
        events: {
          onReady: () => {
            this.ytReady = true;
          },
          onStateChange: (event) => {
            if (event.data === YT.PlayerState.PLAYING) {
              this.isPlaying = true;
              this.syncUI();
              this.startYouTubeTicker();
            } else if (event.data === YT.PlayerState.PAUSED) {
              this.isPlaying = false;
              this.syncUI();
              this.stopYouTubeTicker();
            } else if (event.data === YT.PlayerState.ENDED) {
              this.onTrackEnded();
            }
          },
          onError: (err) => {
            console.warn('YouTube Player notice (Code:', err.data, ')');
          }
        }
      });
    } catch (err) {
      console.warn('YouTube Player initialization caught notice:', err);
    }
  }

  // Ticker to sync timeline & lyrics during YouTube Music playback
  startYouTubeTicker() {
    this.stopYouTubeTicker();
    this.ytTicker = setInterval(() => {
      if (!this.isPlaying) return;

      if (this.ytReady && this.ytPlayer && typeof this.ytPlayer.getCurrentTime === 'function') {
        try {
          const t = this.ytPlayer.getCurrentTime();
          const d = this.ytPlayer.getDuration();
          if (!isNaN(t) && t > 0) this.currentTime = t;
          if (!isNaN(d) && d > 0) this.duration = d;
        } catch (e) {
          this.currentTime += 0.5;
        }
      } else {
        this.currentTime += 0.5;
      }

      if (this.duration > 0 && this.currentTime >= this.duration) {
        this.onTrackEnded();
      } else {
        this.syncTimeline();
      }
    }, 500);
  }

  stopYouTubeTicker() {
    if (this.ytTicker) {
      clearInterval(this.ytTicker);
      this.ytTicker = null;
    }
  }

  // Visual Waveform Frequency Visualizer
  initWaveformVisualizer() {
    let phase = 0;
    const animateWaveform = () => {
      const bars = document.querySelectorAll('#waveformBars span');
      if (bars.length > 0) {
        phase += 0.08;
        bars.forEach((bar, i) => {
          if (this.isPlaying) {
            const v1 = Math.sin(phase + i * 0.45);
            const v2 = Math.cos(phase * 1.3 + i * 0.25);
            const norm = (v1 + v2 + 2) / 4;
            const h = Math.round(4 + norm * 14);
            bar.style.height = `${h}px`;
            bar.style.opacity = '1';
          } else {
            bar.style.height = '4px';
            bar.style.opacity = '0.35';
          }
        });
      }
      requestAnimationFrame(animateWaveform);
    };
    requestAnimationFrame(animateWaveform);
  }

  stopConflictingMedia() {
    if (window.liveStreamEngine && typeof window.liveStreamEngine.pauseLivePlayer === 'function') {
      window.liveStreamEngine.pauseLivePlayer();
    }
    if (window.armyHubEngine && typeof window.armyHubEngine.closeVideoModal === 'function') {
      window.armyHubEngine.closeVideoModal();
    }
  }

  loadTrack(index, autoPlay = true) {
    if (index < 0) index = this.tracks.length - 1;
    if (index >= this.tracks.length) index = 0;
    this.currentIndex = index;
    const track = this.currentTrack;

    this.currentTime = 0;
    this.duration = track.duration || 210;

    this.stopConflictingMedia();

    // Check if offline master audio exists or if we should use YouTube Music stream
    if (track.audioSrc) {
      this.playMode = 'studio';

      // Tear down any active YouTube Music stream so the two never play at once
      this.stopYouTubeTicker();
      if (this.ytReady && this.ytPlayer && typeof this.ytPlayer.pauseVideo === 'function') {
        try {
          this.ytPlayer.pauseVideo();
        } catch (e) {}
      }

      this.audio.src = track.audioSrc;
      this.audio.currentTime = 0;

      // Also cue YouTube in dock
      if (this.ytReady && this.ytPlayer && typeof this.ytPlayer.cueVideoById === 'function') {
        try {
          this.ytPlayer.cueVideoById(track.youtubeId);
          this.ytPlayer.mute();
        } catch (e) {}
      }
    } else {
      // Stream via YouTube Music
      this.switchToYouTubeMusicStream(false);
    }

    if (autoPlay) {
      this.play();
    } else {
      this.pause();
    }

    // Sync lyrics and bluetooth engine
    if (window.lyricsEngine) {
      window.lyricsEngine.loadLyrics(track.lyrics);
    }
    if (window.bluetoothEngine) {
      window.bluetoothEngine.setBpm(track.bpm || 110);
    }

    this.syncUI();
  }

  switchToYouTubeMusicStream(startPlaying = true) {
    this.playMode = 'yt';
    try {
      this.audio.pause();
    } catch (e) {}

    const track = this.currentTrack;
    if (!track) return;

    // Load video in YouTube player
    if (this.ytReady && this.ytPlayer && typeof this.ytPlayer.loadVideoById === 'function') {
      try {
        this.ytPlayer.unMute();
        this.ytPlayer.setVolume(100);
        if (startPlaying) {
          this.ytPlayer.loadVideoById(track.youtubeId);
        } else {
          this.ytPlayer.cueVideoById(track.youtubeId);
        }
      } catch (e) {}
    }

    // Embed in player card if YouTube View is active
    this.updateYtPlayerEmbed();

    if (startPlaying) {
      this.isPlaying = true;
      this.startYouTubeTicker();
    }
    this.syncUI();
  }

  updateYtPlayerEmbed() {
    const embedContainer = document.getElementById('ytStreamPlayerEmbed');
    if (!embedContainer) return;

    const track = this.currentTrack;
    if (this.isYtViewActive || this.playMode === 'yt') {
      embedContainer.innerHTML = `
        <iframe class="w-full h-full rounded-2xl" src="https://www.youtube.com/embed/${track.youtubeId}?autoplay=${this.isPlaying ? 1 : 0}&enablejsapi=1&rel=0&modestbranding=1" title="${track.title} on YouTube Music" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
      `;
    }
  }

  togglePlayMode() {
    const track = this.currentTrack;
    if (this.playMode === 'studio') {
      this.switchToYouTubeMusicStream(this.isPlaying);
      if (window.app) window.app.showToast('Switched to YouTube Music Official Stream 🎵');
    } else {
      if (track.audioSrc) {
        this.playMode = 'studio';
        if (this.ytReady && this.ytPlayer && typeof this.ytPlayer.pauseVideo === 'function') {
          this.ytPlayer.pauseVideo();
        }
        this.stopYouTubeTicker();
        this.audio.currentTime = this.currentTime;
        if (this.isPlaying) this.audio.play();
        if (window.app) window.app.showToast('Switched to Master Lossless Audio 💿');
      } else {
        if (window.app) window.app.showToast('Streaming via YouTube Music (Official Release) 💜');
      }
    }
    this.syncUI();
  }

  toggleYtView() {
    this.isYtViewActive = !this.isYtViewActive;
    const vinylContainer = document.getElementById('vinylShowcaseContainer');
    const ytContainer = document.getElementById('ytStreamPlayerContainer');
    const viewBtn = document.getElementById('toggleYtViewBtn');

    if (vinylContainer && ytContainer) {
      if (this.isYtViewActive) {
        vinylContainer.classList.add('hidden');
        ytContainer.classList.remove('hidden');
        this.updateYtPlayerEmbed();
        if (viewBtn) viewBtn.innerHTML = '<span class="material-symbols-outlined text-xs">album</span> Vinyl View';
      } else {
        vinylContainer.classList.remove('hidden');
        ytContainer.classList.add('hidden');
        if (viewBtn) viewBtn.innerHTML = '<span class="material-symbols-outlined text-xs">smart_display</span> YT Music View';
      }
    }
  }

  togglePlayPause() {
    if (this.isPlaying) {
      this.pause();
    } else {
      this.play();
    }
  }

  play() {
    this.stopConflictingMedia();

    if (this.playMode === 'studio' && this.currentTrack.audioSrc) {
      const playPromise = this.audio.play();
      if (playPromise !== undefined) {
        playPromise
          .then(() => {
            this.isPlaying = true;
            this.syncUI();
          })
          .catch(() => {
            this.isPlaying = false;
            this.pendingPlay = true;
            this.syncUI();
          });
      } else {
        this.isPlaying = true;
        this.syncUI();
      }
    } else {
      // YouTube Music Stream Mode
      this.isPlaying = true;
      if (this.ytReady && this.ytPlayer && typeof this.ytPlayer.playVideo === 'function') {
        try {
          this.ytPlayer.unMute();
          this.ytPlayer.playVideo();
        } catch (e) {}
      }
      this.startYouTubeTicker();
      this.syncUI();
    }
  }

  pause() {
    this.isPlaying = false;
    this.pendingPlay = false;

    if (this.playMode === 'studio') {
      try {
        this.audio.pause();
      } catch (e) {}
    } else {
      if (this.ytReady && this.ytPlayer && typeof this.ytPlayer.pauseVideo === 'function') {
        try {
          this.ytPlayer.pauseVideo();
        } catch (e) {}
      }
      this.stopYouTubeTicker();
    }

    this.syncUI();
  }

  skipNext() {
    if (this.isShuffle) {
      let randIndex;
      do {
        randIndex = Math.floor(Math.random() * this.tracks.length);
      } while (randIndex === this.currentIndex && this.tracks.length > 1);
      this.loadTrack(randIndex, true);
    } else {
      this.loadTrack(this.currentIndex + 1, true);
    }
  }

  skipPrevious() {
    if (this.currentTime > 3) {
      this.seekTo(0);
    } else {
      this.loadTrack(this.currentIndex - 1, true);
    }
  }

  seekTo(seconds) {
    this.currentTime = Math.max(0, Math.min(seconds, this.duration));

    if (this.playMode === 'studio') {
      try {
        if (this.audio && !isNaN(this.currentTime)) {
          this.audio.currentTime = this.currentTime;
        }
      } catch (e) {}
    } else {
      if (this.ytReady && this.ytPlayer && typeof this.ytPlayer.seekTo === 'function') {
        try {
          this.ytPlayer.seekTo(this.currentTime, true);
        } catch (e) {}
      }
    }

    this.syncTimeline();
  }

  onTrackEnded() {
    if (this.repeatMode === 'one') {
      this.seekTo(0);
      this.play();
    } else if (this.repeatMode === 'all') {
      this.skipNext();
    } else {
      this.pause();
      this.seekTo(0);
    }
  }

  toggleShuffle() {
    this.isShuffle = !this.isShuffle;
    this.syncShuffleUI();
    if (window.app) {
      window.app.showToast(this.isShuffle ? 'Shuffle Mode: ON 🔀' : 'Shuffle Mode: OFF');
    }
  }

  toggleRepeat() {
    if (this.repeatMode === 'none') {
      this.repeatMode = 'all';
    } else if (this.repeatMode === 'all') {
      this.repeatMode = 'one';
    } else {
      this.repeatMode = 'none';
    }
    this.syncRepeatUI();
    if (window.app) {
      const modeLabel = this.repeatMode === 'one' ? 'Repeat 1 Song 🔂' : this.repeatMode === 'all' ? 'Repeat All 🔁' : 'Repeat: OFF';
      window.app.showToast(modeLabel);
    }
  }

  toggleFavorite(trackId = null) {
    const id = trackId || this.currentTrack.id;
    if (this.favorites.has(id)) {
      this.favorites.delete(id);
      if (window.app) window.app.showToast('Removed from favorites');
    } else {
      this.favorites.add(id);
      if (window.app) window.app.showToast('Saved to your BTS favorites! 💜');
    }
    try {
      localStorage.setItem('bts_music_favorites', JSON.stringify([...this.favorites]));
    } catch (e) {}
    this.syncFavoriteUI();
  }

  formatTime(secs) {
    if (isNaN(secs) || secs < 0) secs = 0;
    const m = Math.floor(secs / 60);
    const s = Math.floor(secs % 60);
    return `${m}:${s < 10 ? '0' : ''}${s}`;
  }

  openOnYouTubeMusic() {
    const track = this.currentTrack;
    if (!track) return;
    const url = track.ytMusicUrl || `https://music.youtube.com/watch?v=${track.youtubeId}`;
    window.open(url, '_blank');
    if (window.app) {
      window.app.showToast(`Streaming ${track.title} on YouTube Music 🚀`);
    }
  }

  syncTimeline() {
    const pct = this.duration > 0 ? (this.currentTime / this.duration) * 100 : 0;

    // Mini-player progress
    const miniProgress = document.getElementById('miniPlayerProgress');
    if (miniProgress) {
      miniProgress.style.width = `${pct}%`;
    }

    // Full-player timeline
    const scrubberProgress = document.getElementById('scrubberProgress');
    const scrubberThumb = document.getElementById('scrubberThumb');
    const currentLabel = document.getElementById('currentTimeLabel');
    const durationLabel = document.getElementById('durationLabel');

    if (scrubberProgress) scrubberProgress.style.width = `${pct}%`;
    if (scrubberThumb) scrubberThumb.style.left = `${pct}%`;
    if (currentLabel) currentLabel.textContent = this.formatTime(this.currentTime);
    if (durationLabel) durationLabel.textContent = this.formatTime(this.duration);

    // Notify lyrics engine
    if (window.lyricsEngine) {
      window.lyricsEngine.syncTime(this.currentTime);
    }
  }

  syncUI() {
    const track = this.currentTrack;
    if (!track) return;

    // Mini-player track info
    const miniTitle = document.getElementById('miniTrackTitle');
    const miniArtist = document.getElementById('miniTrackArtist');
    const miniArt = document.getElementById('miniTrackArt');
    const miniPlayIcon = document.getElementById('miniPlayIcon');
    const miniFavIcon = document.getElementById('miniFavIcon');

    if (miniTitle) miniTitle.textContent = track.title;
    if (miniArtist) {
      const modeText = this.playMode === 'yt' ? 'YouTube Music Stream' : 'BTS Music Sync';
      miniArtist.textContent = `${track.artist} • ${modeText}`;
    }
    if (miniArt) {
      miniArt.innerHTML = `<img src="${track.cover}" alt="${track.title}" class="w-full h-full object-cover">`;
    }
    if (miniPlayIcon) {
      miniPlayIcon.textContent = this.isPlaying ? 'pause' : 'play_arrow';
    }
    if (miniFavIcon) {
      const isFav = this.favorites.has(track.id);
      miniFavIcon.style.fontVariationSettings = isFav ? "'FILL' 1" : "'FILL' 0";
      miniFavIcon.classList.toggle('text-primary', isFav);
    }

    // Full Player view elements
    const fullTitle = document.getElementById('fullTrackTitle');
    const fullArtist = document.getElementById('fullTrackArtist');
    const fullCover = document.getElementById('fullTrackCover');
    const fullAlbumContext = document.getElementById('fullAlbumContext');
    const fullPlayIcon = document.getElementById('playPauseIcon');
    const fullPlayBtn = document.getElementById('mainPlayPauseBtn');
    const vinylDisc = document.getElementById('vinylDisc');
    const streamSourceBadge = document.getElementById('streamSourceBadge');

    if (fullTitle) fullTitle.textContent = track.title;
    if (fullArtist) fullArtist.textContent = `${track.artist} • ${track.album} • ${track.year}`;
    if (fullCover) fullCover.src = track.cover;
    if (fullAlbumContext) fullAlbumContext.textContent = track.album;

    if (streamSourceBadge) {
      if (this.playMode === 'yt' || !track.audioSrc) {
        streamSourceBadge.innerHTML = `<span class="material-symbols-outlined text-xs text-red-500">smart_display</span> YouTube Music Stream`;
        streamSourceBadge.classList.add('border-red-500/30', 'text-red-400');
        streamSourceBadge.classList.remove('border-primary/30', 'text-primary');
      } else {
        streamSourceBadge.innerHTML = `<span class="material-symbols-outlined text-xs text-primary">album</span> Master Lossless Audio`;
        streamSourceBadge.classList.add('border-primary/30', 'text-primary');
        streamSourceBadge.classList.remove('border-red-500/30', 'text-red-400');
      }
    }

    if (fullPlayIcon) {
      fullPlayIcon.textContent = this.isPlaying ? 'pause' : 'play_arrow';
    }
    if (fullPlayBtn) {
      if (this.isPlaying) {
        fullPlayBtn.classList.add('shadow-[0_8px_28px_rgba(183,109,255,0.45)]');
      } else {
        fullPlayBtn.classList.remove('shadow-[0_8px_28px_rgba(183,109,255,0.45)]');
      }
    }

    if (vinylDisc) {
      if (this.isPlaying) {
        vinylDisc.classList.add('playing');
      } else {
        vinylDisc.classList.remove('playing');
      }
    }

    // Highlight row in list
    document.querySelectorAll('[data-track-id]').forEach(row => {
      const id = row.getAttribute('data-track-id');
      const isCurrent = id === track.id;
      row.classList.toggle('bg-surface-container-high/90', isCurrent);
      row.classList.toggle('border-primary', isCurrent);
      const eqIcon = row.querySelector('.eq-indicator');
      if (eqIcon) {
        eqIcon.classList.toggle('hidden', !isCurrent || !this.isPlaying);
      }
    });

    this.syncShuffleUI();
    this.syncRepeatUI();
    this.syncFavoriteUI();
    this.syncTimeline();
  }

  syncShuffleUI() {
    const shuffleBtn = document.getElementById('shuffleBtn');
    if (shuffleBtn) {
      const dot = shuffleBtn.querySelector('.shuffle-dot');
      if (this.isShuffle) {
        shuffleBtn.classList.add('text-primary');
        shuffleBtn.classList.remove('text-on-surface-variant');
        if (dot) dot.classList.remove('hidden');
      } else {
        shuffleBtn.classList.remove('text-primary');
        shuffleBtn.classList.add('text-on-surface-variant');
        if (dot) dot.classList.add('hidden');
      }
    }
  }

  syncRepeatUI() {
    const repeatBtn = document.getElementById('repeatBtn');
    if (repeatBtn) {
      const icon = repeatBtn.querySelector('.material-symbols-outlined');
      const dot = repeatBtn.querySelector('.repeat-dot');
      if (this.repeatMode === 'one') {
        icon.textContent = 'repeat_one';
        repeatBtn.classList.add('text-primary');
        repeatBtn.classList.remove('text-on-surface-variant');
        if (dot) dot.classList.remove('hidden');
      } else if (this.repeatMode === 'all') {
        icon.textContent = 'repeat';
        repeatBtn.classList.add('text-primary');
        repeatBtn.classList.remove('text-on-surface-variant');
        if (dot) dot.classList.remove('hidden');
      } else {
        icon.textContent = 'repeat';
        repeatBtn.classList.remove('text-primary');
        repeatBtn.classList.add('text-on-surface-variant');
        if (dot) dot.classList.add('hidden');
      }
    }
  }

  syncFavoriteUI() {
    const isFav = this.favorites.has(this.currentTrack.id);
    const headerFav = document.getElementById('headerFavBtn');
    if (headerFav) {
      const icon = headerFav.querySelector('span');
      if (isFav) {
        headerFav.classList.add('text-primary');
        headerFav.classList.remove('text-on-surface-variant');
        if (icon) icon.style.fontVariationSettings = "'FILL' 1";
      } else {
        headerFav.classList.remove('text-primary');
        headerFav.classList.add('text-on-surface-variant');
        if (icon) icon.style.fontVariationSettings = "'FILL' 0";
      }
    }
    const miniFavIcon = document.getElementById('miniFavIcon');
    if (miniFavIcon) {
      miniFavIcon.style.fontVariationSettings = isFav ? "'FILL' 1" : "'FILL' 0";
      miniFavIcon.classList.toggle('text-primary', isFav);
    }
  }

  initPlayerBindings() {
    const mainPlayBtn = document.getElementById('mainPlayPauseBtn');
    const skipNextBtn = document.getElementById('skipNextBtn');
    const skipPrevBtn = document.getElementById('skipPrevBtn');
    const shuffleBtn = document.getElementById('shuffleBtn');
    const repeatBtn = document.getElementById('repeatBtn');
    const headerFavBtn = document.getElementById('headerFavBtn');
    const ytMusicOpenBtn = document.getElementById('ytMusicOpenBtn');
    const streamSourceBadge = document.getElementById('streamSourceBadge');
    const toggleYtViewBtn = document.getElementById('toggleYtViewBtn');

    if (mainPlayBtn) {
      mainPlayBtn.addEventListener('click', () => this.togglePlayPause());
    }
    if (skipNextBtn) {
      skipNextBtn.addEventListener('click', () => this.skipNext());
    }
    if (skipPrevBtn) {
      skipPrevBtn.addEventListener('click', () => this.skipPrevious());
    }
    if (shuffleBtn) {
      shuffleBtn.addEventListener('click', () => this.toggleShuffle());
    }
    if (repeatBtn) {
      repeatBtn.addEventListener('click', () => this.toggleRepeat());
    }
    if (headerFavBtn) {
      headerFavBtn.addEventListener('click', () => this.toggleFavorite());
    }
    if (ytMusicOpenBtn) {
      ytMusicOpenBtn.addEventListener('click', () => this.openOnYouTubeMusic());
    }
    if (streamSourceBadge) {
      streamSourceBadge.addEventListener('click', () => this.togglePlayMode());
    }
    if (toggleYtViewBtn) {
      toggleYtViewBtn.addEventListener('click', () => this.toggleYtView());
    }
    // Secondary YT-view button in the track info row (de-duplicated id)
    const toggleYtViewAltBtn = document.getElementById('toggleYtViewAlt');
    if (toggleYtViewAltBtn) {
      toggleYtViewAltBtn.addEventListener('click', () => this.toggleYtView());
    }

    // Scrubber drag & click
    const scrubber = document.getElementById('scrubberTrack');
    if (scrubber) {
      const handleSeek = (e) => {
        const rect = scrubber.getBoundingClientRect();
        const clientX = e.touches ? e.touches[0].clientX : e.clientX;
        const clickPos = Math.max(0, Math.min(clientX - rect.left, rect.width));
        const fraction = clickPos / rect.width;
        this.seekTo(fraction * this.duration);
      };

      scrubber.addEventListener('click', handleSeek);

      scrubber.addEventListener('mousedown', (e) => {
        this.isDraggingScrubber = true;
        handleSeek(e);
        const onMouseMove = (moveEvent) => {
          if (this.isDraggingScrubber) handleSeek(moveEvent);
        };
        const onMouseUp = () => {
          this.isDraggingScrubber = false;
          window.removeEventListener('mousemove', onMouseMove);
          window.removeEventListener('mouseup', onMouseUp);
        };
        window.addEventListener('mousemove', onMouseMove);
        window.addEventListener('mouseup', onMouseUp);
      });

      scrubber.addEventListener('touchstart', (e) => {
        this.isDraggingScrubber = true;
        handleSeek(e);
      }, { passive: true });

      scrubber.addEventListener('touchmove', (e) => {
        if (this.isDraggingScrubber) handleSeek(e);
      }, { passive: true });

      scrubber.addEventListener('touchend', () => {
        this.isDraggingScrubber = false;
      });
    }

    // YouTube Dock Toggle
    const toggleDockBtn = document.getElementById('toggleYtDockBtn');
    const closeDockBtn = document.getElementById('closeYtDockBtn');
    const dockContainer = document.getElementById('ytVideoDock');

    if (toggleDockBtn && dockContainer) {
      toggleDockBtn.addEventListener('click', () => {
        this.isVideoDockOpen = !this.isVideoDockOpen;
        if (this.isVideoDockOpen) {
          dockContainer.classList.remove('dock-hidden');
          dockContainer.classList.add('dock-visible');
          toggleDockBtn.innerHTML = '<span class="material-symbols-outlined text-xs">close</span> Hide MV';
        } else {
          dockContainer.classList.remove('dock-visible');
          dockContainer.classList.add('dock-hidden');
          toggleDockBtn.innerHTML = '<span class="material-symbols-outlined text-xs">smart_display</span> Watch MV';
        }
      });
    }

    if (closeDockBtn && dockContainer && toggleDockBtn) {
      closeDockBtn.addEventListener('click', () => {
        this.isVideoDockOpen = false;
        dockContainer.classList.remove('dock-visible');
        dockContainer.classList.add('dock-hidden');
        toggleDockBtn.innerHTML = '<span class="material-symbols-outlined text-xs">smart_display</span> Watch MV';
      });
    }
  }
}

window.audioPlayer = new AudioPlayerEngine();
