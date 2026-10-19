// ==========================================================================
// BTS Music — Live Stadium Concert & Multicam Engine
// ==========================================================================

class LiveStreamEngine {
  constructor() {
    this.concert = window.BTS_DATA.concert;
    this.currentAngle = 'main';
    this.totalHearts = this.concert.totalHearts;
    this.isDolbyAtmos = true;
    this.currentResolution = '4K HDR';
    this.chatInterval = null;
    this.ytLivePlayer = null;
    this.isLiveVideoActive = false;
    this.createdLivePlayer = false;
    this.retryTimer = null;
    this.liveAudio = null;

    document.addEventListener('DOMContentLoaded', () => {
      this.init();
    });
  }

  init() {
    this.bindCameraAngles();
    this.bindHeartBurst();
    this.bindChatSystem();
    this.bindSetlistDrawer();
    this.bindDolbyAtmos();
    this.bindFullscreen();
  }

  initLivePlayer() {
    // Pause music player whenever the live stage is entered
    if (window.audioPlayer && window.audioPlayer.isPlaying) {
      window.audioPlayer.pause();
    }

    // Never create a second YouTube player for the same viewport — the old
    // code re-instantiated YT.Player on every tab entry, stacking endless
    // iframes and leaking memory.
    if (this.createdLivePlayer) {
      this.isLiveVideoActive = true;
      return;
    }

    const container = document.getElementById('yt-live-viewport');
    if (!container) return;

    const angle = this.concert.angles.find(a => a.id === this.currentAngle) || this.concert.angles[0];

    if (window.YT && window.YT.Player) {
      this.createLiveVideoPlayer(angle);
    } else if (!this.retryTimer) {
      // YouTube IFrame API not injected yet — wait briefly before falling back
      // to the stadium-audio-only mode, so the video is used whenever possible.
      this.retryTimer = setTimeout(() => {
        this.retryTimer = null;
        if (window.YT && window.YT.Player && !this.createdLivePlayer) {
          this.createLiveVideoPlayer(angle);
        } else if (!this.createdLivePlayer) {
          this.createdLivePlayer = true;
          this.startLiveAudioFallback();
        }
      }, 1500);
    }
  }

  createLiveVideoPlayer(angle) {
    this.createdLivePlayer = true;
    this.isLiveVideoActive = true;
    try {
      const playerVars = {
        autoplay: 1,
        controls: 1,
        modestbranding: 1,
        rel: 0,
        playsinline: 1
      };
      if (window.location.protocol.startsWith('http') && window.location.origin && window.location.origin !== 'null') {
        playerVars.origin = window.location.origin;
      }

      this.ytLivePlayer = new YT.Player('yt-live-viewport', {
        height: '100%',
        width: '100%',
        videoId: angle.youtubeId,
        playerVars: playerVars,
        events: {
          onReady: () => {
            if (window.audioPlayer && window.audioPlayer.isPlaying) {
              window.audioPlayer.pause();
            }
          },
          onError: () => {
            this.startLiveAudioFallback();
          }
        }
      });
    } catch (e) {
      this.startLiveAudioFallback();
    }
  }

  startLiveAudioFallback() {
    if (!this.liveAudio) {
      this.liveAudio = new Audio(this.concert.audioSrc || 'audio/mikrokosmos.m4a');
      this.liveAudio.loop = true;
    }
    this.liveAudio.play().catch(() => {});
  }

  pauseLivePlayer() {
    if (this.retryTimer) {
      clearTimeout(this.retryTimer);
      this.retryTimer = null;
    }
    if (this.ytLivePlayer && typeof this.ytLivePlayer.pauseVideo === 'function') {
      try {
        this.ytLivePlayer.pauseVideo();
      } catch (e) {}
    }
    if (this.liveAudio) {
      try {
        this.liveAudio.pause();
      } catch (e) {}
    }
    this.isLiveVideoActive = false;
  }


  bindCameraAngles() {
    const camButtons = document.querySelectorAll('.cam-btn');
    const angleDesc = document.getElementById('cameraAngleDesc');

    camButtons.forEach(btn => {
      btn.addEventListener('click', () => {
        camButtons.forEach(b => {
          b.className = 'cam-btn px-3 py-1 rounded-full bg-surface-container-high/60 text-on-surface-variant hover:text-on-surface text-xs whitespace-nowrap flex-shrink-0 transition-all border border-outline-variant/10';
        });
        btn.className = 'cam-btn px-3 py-1 rounded-full bg-primary text-on-primary text-xs whitespace-nowrap flex-shrink-0 transition-all font-medium';

        const angleId = btn.getAttribute('data-angle');
        this.switchCameraAngle(angleId);
      });
    });
  }

  switchCameraAngle(angleId) {
    this.currentAngle = angleId;
    const angle = this.concert.angles.find(a => a.id === angleId) || this.concert.angles[0];
    
    const angleBadge = document.getElementById('activeAngleBadge');
    const angleDesc = document.getElementById('cameraAngleDesc');
    if (angleBadge) angleBadge.textContent = angle.label;
    if (angleDesc) angleDesc.textContent = angle.desc;

    // Show toast or switch video stream
    if (window.app) {
      window.app.showToast(`Switched to ${angle.label} (${angle.desc})`);
    }

    if (this.ytLivePlayer && typeof this.ytLivePlayer.loadVideoById === 'function') {
      this.ytLivePlayer.loadVideoById(angle.youtubeId);
    }
  }

  bindHeartBurst() {
    // Actual hero heart button (floating hearts) & player-screen cheer button
    const heartBtn = document.getElementById('floatingHeartBtn');
    const cheerTapBtn = document.getElementById('cheerTapBtn');
    const burstBtn = document.getElementById('burst-heart-btn');

    if (burstBtn) {
      burstBtn.addEventListener('click', (e) => {
        e.preventDefault();
        this.spawnFloatingHearts(3);
      });
    }

    if (heartBtn) {
      heartBtn.addEventListener('click', (e) => {
        e.preventDefault();
        this.spawnFloatingHearts(2);
      });
    }

    if (cheerTapBtn) {
      cheerTapBtn.addEventListener('click', (e) => {
        e.preventDefault();
        this.spawnFloatingHearts(1);
      });
    }

    this.updateHeartCounters();
  }

  updateHeartCounters() {
    const formattedMillions = (this.totalHearts / 1000000).toFixed(2) + 'M';
    const formattedThousands = (this.totalHearts / 1000).toFixed(1) + 'k';

    // The live screen counter (`liveHeartCounter`) and the cheer capsule on
    // the player screen (`cheerCounter`) both stay in sync with heart taps.
    const heartCounter = document.getElementById('liveHeartCounter');
    const cheerCounter = document.getElementById('cheerCounter');
    const altCounter = document.getElementById('heart-count');

    if (heartCounter) heartCounter.textContent = formattedMillions;
    if (cheerCounter) cheerCounter.textContent = formattedThousands;
    if (altCounter) altCounter.textContent = formattedMillions;
  }

  spawnFloatingHearts(count = 1) {
    this.totalHearts += count;
    this.updateHeartCounters();

    const stage = document.getElementById('heart-stage') || document.body;
    const symbols = ['💜', '💜', '✨', '💜', '💖', '🌟', ' BTS Music 💜'];

    for (let i = 0; i < count; i++) {
      const heart = document.createElement('div');
      const symbol = symbols[Math.floor(Math.random() * symbols.length)];
      heart.textContent = symbol;
      heart.className = 'floating-heart text-secondary select-none font-bold';

      const randRight = 10 + Math.random() * 35; // Position near bottom right
      const randX = (Math.random() - 0.5) * 80;
      const randRot = (Math.random() - 0.5) * 60;
      const size = 18 + Math.random() * 16;

      heart.style.right = `${randRight}%`;
      heart.style.bottom = '20px';
      heart.style.fontSize = `${size}px`;
      heart.style.setProperty('--rand-x', `${randX}px`);
      heart.style.setProperty('--rand-rot', `${randRot}deg`);

      stage.appendChild(heart);

      setTimeout(() => {
        if (heart && heart.parentNode) heart.parentNode.removeChild(heart);
      }, 1600);
    }
  }

  bindChatSystem() {
    const chatFeed = document.getElementById('liveChatFeed');
    const chatInput = document.getElementById('chatInput');
    const chatSendBtn = document.getElementById('sendChatBtn');

    // Populate initial comments (feed container is `liveChatFeed` in index.html)
    if (chatFeed) {
      chatFeed.innerHTML = '';
      this.concert.initialComments.forEach(comment => {
        this.appendChatMessage(comment);
      });
    }

    // User message send handler
    const sendMessage = () => {
      if (!chatInput) return;
      const text = chatInput.value.trim();
      if (!text) return;

      this.appendChatMessage({
        user: 'You (ARMY 💜)',
        badge: 'Purple VIP',
        flag: '💜',
        text: text,
        isUser: true
      });

      chatInput.value = '';
      this.spawnFloatingHearts(2);
    };

    if (chatSendBtn) chatSendBtn.addEventListener('click', sendMessage);
    if (chatInput) {
      chatInput.addEventListener('keydown', (e) => {
        if (e.key === 'Enter') {
          e.preventDefault();
          sendMessage();
        }
      });
    }

    // Auto-stream incoming comments every 3.5 seconds
    const simulatedArmyMsgs = [
      { user: 'Taehyung_WinterBear', badge: 'Verified ARMY', flag: '🇰🇷', text: 'V\'s deep voice is healing my entire soul right now! 💜' },
      { user: 'HobiHope_World', badge: 'Level 7', flag: '🇺🇸', text: 'J-HOPE DANCE BREAK COMING UP!! ARE YOU READY ARMY??' },
      { user: 'YoongiMarryMe', badge: 'ARMY Gold', flag: '🇧🇷', text: 'Agust D rap line went OFF! Greatest concert of all time!' },
      { user: 'Worldwide_Jin_92', badge: 'Global ARMY', flag: '🇬🇧', text: 'Seokjin high note made everyone in the stadium cheer so loud!' },
      { user: 'Kookie_GoldenSeven', badge: 'Verified ARMY', flag: '🇫🇷', text: 'The lightsticks synchronizing across the whole stadium looks like the galaxy ✨' }
    ];

    let msgIndex = 0;
    this.chatInterval = setInterval(() => {
      const isVisible = document.getElementById('view-live-video') && !document.getElementById('view-live-video').classList.contains('hidden');
      if (isVisible) {
        this.appendChatMessage(simulatedArmyMsgs[msgIndex % simulatedArmyMsgs.length]);
        msgIndex++;
      }
    }, 4000);
  }

  appendChatMessage(comment) {
    const chatFeed = document.getElementById('liveChatFeed') || document.getElementById('chatFeed');
    if (!chatFeed) return;

    const row = document.createElement('div');
    row.className = `flex flex-col gap-0.5 text-xs py-1.5 px-2.5 rounded-xl transition-all animate-fadeIn ${comment.isUser ? 'bg-primary/15 border border-primary/25' : 'bg-surface-container-high/40'}`;

    row.innerHTML = `
      <div class="flex items-center gap-1.5 font-medium">
        <span class="text-primary text-[11px] truncate">${comment.user}</span>
        <span class="px-1.5 py-0.2 rounded text-[9px] bg-surface-container-highest/60 text-on-surface-variant font-code">${comment.badge}</span>
        <span class="text-[10px]">${comment.flag || '💜'}</span>
      </div>
      <p class="text-on-surface text-[12px] break-words leading-snug">${comment.text}</p>
    `;

    chatFeed.appendChild(row);
    chatFeed.scrollTop = chatFeed.scrollHeight;
  }

  bindSetlistDrawer() {
    const setlistModal = document.getElementById('setlistModal');
    const closeSetlistBtn = document.getElementById('closeSetlistBtn');
    const setlistList = document.getElementById('setlistList');

    if (setlistList) {
      setlistList.innerHTML = '';
      this.concert.setlist.forEach(item => {
        const itemEl = document.createElement('div');
        const isCurrent = item.status === 'performing';
        itemEl.className = `p-3 rounded-xl flex items-center justify-between cursor-pointer transition-colors ${isCurrent ? 'bg-primary/20 border border-primary/40' : 'bg-surface-container hover:bg-surface-container-high'}`;

        itemEl.innerHTML = `
          <div class="flex items-center gap-2.5">
            <span class="w-6 text-center text-label-sm font-bold ${isCurrent ? 'text-primary' : 'text-on-surface-variant'}">${item.id}</span>
            <span class="text-label-md font-bold ${isCurrent ? 'text-primary' : 'text-on-surface'}">${item.title}</span>
          </div>
          <span class="text-[10px] font-bold uppercase tracking-wider px-2 py-0.5 rounded-full ${isCurrent ? 'bg-primary text-on-primary animate-pulse' : 'bg-surface-container-high text-on-surface-variant'}">
            ${item.status}
          </span>
        `;

        itemEl.addEventListener('click', () => {
          if (window.app) window.app.showToast(`Loading performance: ${item.title}`);
          if (setlistModal) setlistModal.classList.add('hidden');
        });

        setlistList.appendChild(itemEl);
      });
    }

    // Setlist drawer: trigger is `openSetlistBtn` in index.html
    const triggers = [document.getElementById('setlistToggleBtn'), document.getElementById('openSetlistBtn')];
    triggers.forEach(trigger => {
      if (trigger && setlistModal) {
        trigger.addEventListener('click', () => setlistModal.classList.remove('hidden'));
      }
    });
    if (closeSetlistBtn && setlistModal) {
      closeSetlistBtn.addEventListener('click', () => setlistModal.classList.add('hidden'));
    }
  }

  bindDolbyAtmos() {
    const dolbyToggle = document.getElementById('dolbyAtmosToggle');
    if (dolbyToggle) {
      const label = document.getElementById('dolbyAtmosLabel');
      dolbyToggle.addEventListener('click', () => {
        this.isDolbyAtmos = !this.isDolbyAtmos;
        dolbyToggle.classList.toggle('opacity-60', !this.isDolbyAtmos);
        if (label) {
          label.textContent = this.isDolbyAtmos ? 'Dolby Atmos Live Crowd' : 'Standard Stereo Crowd';
        }
        if (window.app) {
          window.app.showToast(this.isDolbyAtmos ? 'Dolby Atmos Spatial Audio: ON 🎧' : 'Dolby Atmos: OFF (Standard Stereo)');
        }
      });
    }
  }

  bindFullscreen() {
    // Fullscreen trigger is `fullscreenBtn`; the stage container carries the
    // `liveViewportStage` id used for the fullscreen element.
    const fsBtn = document.getElementById('fullscreenBtn');
    const viewport = document.getElementById('liveViewportStage') || document.getElementById('yt-live-viewport');
    if (fsBtn && viewport) {
      fsBtn.addEventListener('click', () => {
        if (!document.fullscreenElement) {
          const request = viewport.requestFullscreen || viewport.webkitRequestFullscreen || viewport.msRequestFullscreen;
          if (request) {
            const result = request.call(viewport);
            if (result && typeof result.catch === 'function') {
              result.catch(err => console.warn('Fullscreen error:', err));
            }
          } else {
            console.warn('Fullscreen API not supported in this browser.');
          }
        } else {
          document.exitFullscreen();
        }
      });
    }
  }
}

window.liveStreamEngine = new LiveStreamEngine();
