// ==========================================================================
// BTS Music — Application Controller & UI State Manager
// ==========================================================================

class BTSMusicApp {
  constructor() {
    this.currentTab = 'home';
    this.previousTab = 'home';
    this.theme = localStorage.getItem('bts_music_theme') || localStorage.getItem('borahae_theme') || 'dark';

    document.addEventListener('DOMContentLoaded', () => {
      this.init();
    });
  }

  init() {
    this.applyTheme(this.theme);
    this.bindThemeSwitcher();
    this.bindNavigation();
    this.bindMiniPlayer();
    this.initUniversalSearch();
    this.initEraFilterAndCatalog();
    this.bindHomeCards();
    this.bindModals();
    this.updateGreeting();
    this.bindCategoryFilters();
    this.bindTrackOptions();
  }

  // Theme Management
  applyTheme(theme) {
    this.theme = theme;
    const html = document.documentElement;
    if (theme === 'dark') {
      html.classList.add('dark');
      html.classList.remove('light');
    } else {
      html.classList.remove('dark');
      html.classList.add('light');
    }
    localStorage.setItem('bts_music_theme', theme);

    // Sync theme toggle icons
    const darkIcons = document.querySelectorAll('.theme-icon-dark');
    const lightIcons = document.querySelectorAll('.theme-icon-light');

    darkIcons.forEach(el => el.classList.toggle('hidden', theme !== 'dark'));
    lightIcons.forEach(el => el.classList.toggle('hidden', theme === 'dark'));
  }

  toggleTheme() {
    const nextTheme = this.theme === 'dark' ? 'light' : 'dark';
    this.applyTheme(nextTheme);
    this.showToast(nextTheme === 'dark' ? 'Switched to Obsidian Dark Mode 🌙' : 'Switched to Crisp Lilac Light Mode ☀️');
  }

  bindThemeSwitcher() {
    const toggles = document.querySelectorAll('.theme-toggle-btn');
    toggles.forEach(btn => {
      btn.addEventListener('click', () => {
        this.toggleTheme();
      });
    });
  }

  // Tab Navigation
  switchTab(tabId) {
    if (tabId === this.currentTab) return;

    this.previousTab = this.currentTab;
    this.currentTab = tabId;

    // Hide all view containers
    document.querySelectorAll('.view-screen').forEach(screen => {
      screen.classList.add('hidden');
    });

    // Show target view container
    const targetScreen = document.getElementById(`view-${tabId}`);
    if (targetScreen) {
      targetScreen.classList.remove('hidden');
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    // Update bottom nav tabs
    document.querySelectorAll('.nav-tab').forEach(tab => {
      const path = tab.getAttribute('data-path');
      const isActive = path === tabId;
      tab.classList.toggle('active', isActive);
      tab.classList.toggle('text-primary', isActive);
      tab.classList.toggle('text-on-surface-variant', !isActive);

      const icon = tab.querySelector('.material-symbols-outlined');
      if (icon) {
        icon.style.fontVariationSettings = isActive ? "'FILL' 1" : "'FILL' 0";
      }
    });

    // Mini-player visibility: dock stays visible, but full-player handles its own controls
    const miniPlayerDock = document.getElementById('miniPlayerDock');
    if (miniPlayerDock) {
      // In player-lyrics tab, minimize button will allow going back
      miniPlayerDock.classList.toggle('hidden', tabId === 'player-lyrics');
    }

    // Live concert player coordination
    if (tabId === 'live-video') {
      if (window.audioPlayer && window.audioPlayer.isPlaying) {
        window.audioPlayer.pause();
      }
      if (window.liveStreamEngine) {
        window.liveStreamEngine.initLivePlayer();
      }
    } else {
      // Pause live concert video when navigating away so it never bleeds into other tabs
      if (window.liveStreamEngine) {
        window.liveStreamEngine.pauseLivePlayer();
      }
    }
  }

  bindNavigation() {
    const tabs = document.querySelectorAll('.nav-tab');
    tabs.forEach(tab => {
      tab.addEventListener('click', (e) => {
        e.preventDefault();
        const path = tab.getAttribute('data-path');
        this.switchTab(path);
      });
    });
  }

  bindMiniPlayer() {
    const dock = document.getElementById('miniPlayerDock');
    const minimizeBtn = document.getElementById('minimizePlayerBtn');
    const miniPlayBtn = document.getElementById('miniPlayBtn');
    const miniFavBtn = document.getElementById('miniFavBtn');

    if (dock) {
      dock.addEventListener('click', (e) => {
        // Prevent opening if clicking buttons
        if (e.target.closest('button')) return;
        this.switchTab('player-lyrics');
      });
    }

    if (minimizeBtn) {
      minimizeBtn.addEventListener('click', () => {
        this.switchTab(this.previousTab === 'player-lyrics' ? 'home' : this.previousTab);
      });
    }

    if (miniPlayBtn) {
      miniPlayBtn.addEventListener('click', (e) => {
        e.stopPropagation();
        if (window.audioPlayer) window.audioPlayer.togglePlayPause();
      });
    }

    if (miniFavBtn) {
      miniFavBtn.addEventListener('click', (e) => {
        e.stopPropagation();
        if (window.audioPlayer) window.audioPlayer.toggleFavorite();
      });
    }
  }

  // Home Screen Elements
  updateGreeting() {
    const greetingEl = document.getElementById('greetingText');
    if (!greetingEl) return;

    const hour = new Date().getHours();
    let text = 'Good evening, ARMY';
    if (hour >= 5 && hour < 12) {
      text = 'Good morning, ARMY';
    } else if (hour >= 12 && hour < 18) {
      text = 'Good afternoon, ARMY';
    }
    greetingEl.textContent = text;
  }

  bindCategoryFilters() {
    const filterBtns = document.querySelectorAll('.category-filter-btn');
    filterBtns.forEach(btn => {
      btn.addEventListener('click', () => {
        filterBtns.forEach(b => {
          b.className = 'category-filter-btn px-4 py-1.5 rounded-full bg-surface-container-high text-on-surface-variant hover:text-on-surface text-label-md font-label-md whitespace-nowrap active:scale-95 transition-all';
        });
        btn.className = 'category-filter-btn px-4 py-1.5 rounded-full bg-primary text-on-primary text-label-md font-label-md shadow-md shadow-primary/25 whitespace-nowrap transition-all font-bold';

        const category = btn.getAttribute('data-cat');
        if (category === 'live') {
          this.switchTab('live-video');
        } else if (category === 'solos') {
          this.switchTab('army-hub');
        } else {
          this.showToast(`Filter: ${btn.textContent.trim()}`);
        }
      });
    });
  }

  initUniversalSearch() {
    const searchInput = document.getElementById('globalMusicSearch');
    const dropdown = document.getElementById('searchResultsDropdown');
    const clearBtn = document.getElementById('clearSearchBtn');

    if (!searchInput || !dropdown) return;

    const performSearch = () => {
      const query = (searchInput.value || '').trim().toLowerCase();
      if (!query) {
        dropdown.classList.add('hidden');
        if (clearBtn) clearBtn.classList.add('hidden');
        return;
      }

      if (clearBtn) clearBtn.classList.remove('hidden');

      const allTracks = window.BTS_DATA.tracks || [];
      const matches = allTracks.filter(t => {
        const titleMatch = t.title.toLowerCase().includes(query);
        const artistMatch = t.artist.toLowerCase().includes(query);
        const albumMatch = t.album.toLowerCase().includes(query);
        const eraMatch = (t.era || '').toLowerCase().includes(query);
        return titleMatch || artistMatch || albumMatch || eraMatch;
      });

      dropdown.innerHTML = '';

      if (matches.length > 0) {
        matches.slice(0, 10).forEach(track => {
          const globalIdx = allTracks.findIndex(t => t.id === track.id);
          const item = document.createElement('div');
          item.className = 'flex items-center justify-between p-2 rounded-xl hover:bg-surface-container-high transition-colors cursor-pointer group';
          item.innerHTML = `
            <div class="flex items-center gap-2.5 min-w-0 flex-1">
              <img src="${track.cover}" alt="${track.title}" class="w-9 h-9 rounded object-cover flex-shrink-0 shadow-sm">
              <div class="flex flex-col min-w-0">
                <span class="text-xs font-semibold text-on-surface truncate group-hover:text-primary transition-colors">${track.title}</span>
                <span class="text-[11px] text-on-surface-variant truncate">${track.artist} • ${track.album}</span>
              </div>
            </div>
            <div class="flex items-center gap-1.5 flex-shrink-0">
              <button data-yt-url="${track.ytMusicUrl || ('https://music.youtube.com/watch?v=' + track.youtubeId)}" class="search-direct-yt px-2 py-1 rounded-full bg-red-600/10 hover:bg-red-600 hover:text-white text-red-400 text-[10px] font-semibold transition-all flex items-center gap-0.5" title="Stream on YouTube Music" type="button">
                <span class="material-symbols-outlined text-xs">smart_display</span>
                <span>YT</span>
              </button>
              <button class="w-7 h-7 rounded-full bg-primary/20 text-primary flex items-center justify-center group-hover:bg-primary group-hover:text-on-primary transition-all active:scale-95" type="button">
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">play_arrow</span>
              </button>
            </div>
          `;

          item.addEventListener('click', (e) => {
            if (e.target.closest('.search-direct-yt')) {
              const url = item.querySelector('.search-direct-yt').getAttribute('data-yt-url');
              if (url) window.open(url, '_blank');
              return;
            }
            if (window.audioPlayer && globalIdx !== -1) {
              window.audioPlayer.loadTrack(globalIdx, true);
              dropdown.classList.add('hidden');
            }
          });

          dropdown.appendChild(item);
        });
      }

      // External YouTube Music Search Action
      const ytSearchBtn = document.createElement('div');
      ytSearchBtn.className = 'flex items-center justify-between p-2.5 rounded-xl bg-surface-container-high/60 hover:bg-surface-container-high transition-colors cursor-pointer border border-primary/20 mt-1';
      ytSearchBtn.innerHTML = `
        <div class="flex items-center gap-2 text-xs font-semibold text-primary truncate">
          <span class="material-symbols-outlined text-base">smart_display</span>
          <span>Search & Stream "${searchInput.value.trim()}" on YouTube Music</span>
        </div>
        <span class="material-symbols-outlined text-sm text-primary">arrow_forward</span>
      `;
      ytSearchBtn.addEventListener('click', () => {
        const queryUrl = `https://music.youtube.com/search?q=${encodeURIComponent(searchInput.value.trim() + ' BTS')}`;
        window.open(queryUrl, '_blank');
        dropdown.classList.add('hidden');
      });
      dropdown.appendChild(ytSearchBtn);

      dropdown.classList.remove('hidden');
    };

    searchInput.addEventListener('input', performSearch);
    searchInput.addEventListener('focus', () => {
      if (searchInput.value.trim()) performSearch();
    });

    if (clearBtn) {
      clearBtn.addEventListener('click', () => {
        searchInput.value = '';
        dropdown.classList.add('hidden');
        clearBtn.classList.add('hidden');
      });
    }

    document.addEventListener('click', (e) => {
      if (!searchInput.contains(e.target) && !dropdown.contains(e.target)) {
        dropdown.classList.add('hidden');
      }
    });
  }

  initEraFilterAndCatalog() {
    this.currentEra = 'all';
    this.showAllTracks = false;

    this.renderEraPills();
    this.renderCatalogList();

    const toggleBtn = document.getElementById('toggleShowAllTracksBtn');
    if (toggleBtn) {
      toggleBtn.addEventListener('click', () => {
        this.showAllTracks = !this.showAllTracks;
        const label = document.getElementById('toggleShowAllTracksLabel');
        const icon = toggleBtn.querySelector('.material-symbols-outlined');
        if (label) label.textContent = this.showAllTracks ? 'Collapse to Top Chart Tracks' : 'Explore Full 135+ Song BTS Catalog';
        if (icon) icon.textContent = this.showAllTracks ? 'expand_less' : 'expand_more';
        this.renderCatalogList();
      });
    }
  }

  renderEraPills() {
    const container = document.getElementById('eraFilterBar');
    if (!container) return;
    container.innerHTML = '';

    const eras = window.BTS_DATA.eras || [];
    eras.forEach(era => {
      const isSelected = era.id === this.currentEra;
      const pill = document.createElement('button');
      pill.type = 'button';
      pill.className = `px-3 py-1 rounded-full text-xs font-semibold whitespace-nowrap transition-all cursor-pointer ${
        isSelected
          ? 'bg-primary text-on-primary shadow-sm font-bold'
          : 'bg-surface-container-high text-on-surface-variant hover:text-on-surface active:scale-95'
      }`;
      pill.textContent = era.label;

      pill.addEventListener('click', () => {
        this.currentEra = era.id;
        this.renderEraPills();
        this.renderCatalogList();
      });

      container.appendChild(pill);
    });
  }

  renderCatalogList() {
    const container = document.getElementById('dailyChartContainer');
    const badge = document.getElementById('chartTrackCountBadge');
    if (!container) return;

    const allTracks = window.BTS_DATA.tracks || [];
    const filteredTracks = this.currentEra === 'all'
      ? allTracks
      : allTracks.filter(t => t.era === this.currentEra);

    if (badge) {
      badge.textContent = `${filteredTracks.length} Tracks`;
    }

    container.innerHTML = '';

    const displayTracks = this.showAllTracks ? filteredTracks : filteredTracks.slice(0, 10);

    displayTracks.forEach((track, displayIdx) => {
      const globalIdx = allTracks.findIndex(t => t.id === track.id);
      const isCurrent = window.audioPlayer && window.audioPlayer.currentTrack && window.audioPlayer.currentTrack.id === track.id;
      const isPlaying = isCurrent && window.audioPlayer.isPlaying;

      const row = document.createElement('div');
      row.setAttribute('data-track-index', globalIdx);
      row.setAttribute('data-track-id', track.id);
      row.className = `relative flex items-center gap-space-sm p-2 rounded-lg transition-colors group cursor-pointer border ${
        isCurrent
          ? 'bg-surface-container-high/90 border-primary shadow-sm'
          : 'hover:bg-surface-container-high border-transparent'
      }`;

      const rankNumber = String(displayIdx + 1).padStart(2, '0');

      row.innerHTML = `
        ${isCurrent ? '<div class="absolute left-0 top-0 bottom-0 w-1 bg-primary rounded-r"></div>' : ''}
        <span class="w-6 text-center text-xs font-bold ${isCurrent ? 'text-primary' : 'text-on-surface-variant'} font-code flex-shrink-0">${rankNumber}</span>
        <img class="w-10 h-10 rounded object-cover flex-shrink-0 shadow" src="${track.cover}" alt="${track.title}">
        <div class="flex flex-col min-w-0 flex-1">
          <span class="text-xs font-semibold ${isCurrent ? 'text-primary' : 'text-on-surface'} truncate flex items-center gap-1.5 group-hover:text-primary transition-colors">
            ${track.title}
            <span class="eq-indicator ${isPlaying ? '' : 'hidden'} inline-flex items-end gap-0.5 h-3 w-3">
              <span class="w-0.5 h-3 bg-primary animate-pulse"></span>
              <span class="w-0.5 h-1.5 bg-primary animate-ping"></span>
              <span class="w-0.5 h-2.5 bg-primary animate-pulse"></span>
            </span>
          </span>
          <span class="text-[11px] text-on-surface-variant truncate">
            ${track.artist} • <span class="text-primary font-medium">${track.streams || 'Hit'}</span> • <span class="uppercase text-[9px] px-1 py-0.2 bg-surface-container-highest rounded font-code">${track.era || 'BTS'}</span>
          </span>
        </div>
        <div class="flex items-center gap-1.5 flex-shrink-0">
          <span class="text-[11px] text-on-surface-variant font-code hidden sm:inline">${track.durationText || '3:30'}</span>
          <button data-yt-url="${track.ytMusicUrl || ('https://music.youtube.com/watch?v=' + track.youtubeId)}" class="yt-stream-direct-btn px-2 py-1 rounded-full bg-red-600/10 hover:bg-red-600 hover:text-white text-red-400 text-[10px] font-semibold transition-all flex items-center gap-0.5" title="Stream on YouTube Music" type="button">
            <span class="material-symbols-outlined text-xs">smart_display</span>
            <span class="hidden sm:inline">YT</span>
          </button>
          <button data-track-title="${track.title}" class="track-menu-btn w-7 h-7 flex items-center justify-center text-on-surface-variant hover:text-on-surface" type="button">
            <span class="material-symbols-outlined text-base">more_vert</span>
          </button>
        </div>
      `;

      row.addEventListener('click', (e) => {
        if (e.target.closest('.track-menu-btn')) {
          e.stopPropagation();
          const modalTitle = document.getElementById('optionsModalTrackTitle');
          const modal = document.getElementById('trackOptionsModal');
          if (modalTitle) modalTitle.textContent = track.title;
          if (modal) modal.classList.remove('hidden');
          return;
        }
        if (e.target.closest('.yt-stream-direct-btn')) {
          e.stopPropagation();
          const url = row.querySelector('.yt-stream-direct-btn').getAttribute('data-yt-url');
          if (url) window.open(url, '_blank');
          return;
        }
        if (window.audioPlayer && globalIdx !== -1) {
          window.audioPlayer.loadTrack(globalIdx, true);
        }
      });

      container.appendChild(row);
    });
  }

  bindHomeCards() {
    const allTracks = window.BTS_DATA.tracks || [];
    const findTrackIndex = (id) => {
      const idx = allTracks.findIndex(t => t.id === id);
      return idx !== -1 ? idx : 0;
    };

    const albumShortcuts = {
      'proof': findTrackIndex('yet-to-come'),
      'wings': findTrackIndex('blood-sweat-tears'),
      'tear': findTrackIndex('fake-love'),
      'mot7': findTrackIndex('black-swan'),
      'be': findTrackIndex('dynamite'),
      'golden': findTrackIndex('standing-next-to-you'),
      'butter': findTrackIndex('butter'),
      'indigo': findTrackIndex('wild-flower')
    };

    document.querySelectorAll('[data-album-shortcut]').forEach(tile => {
      tile.addEventListener('click', () => {
        const key = tile.getAttribute('data-album-shortcut');
        const trackIdx = albumShortcuts[key] !== undefined ? albumShortcuts[key] : 0;
        if (window.audioPlayer) {
          window.audioPlayer.loadTrack(trackIdx, true);
          this.switchTab('player-lyrics');
        }
      });
    });

    const joinStreamBtn = document.getElementById('homeJoinStreamBtn');
    if (joinStreamBtn) {
      joinStreamBtn.addEventListener('click', () => {
        this.switchTab('live-video');
      });
    }

    document.querySelectorAll('[data-playlist-play]').forEach(btn => {
      btn.addEventListener('click', (e) => {
        e.stopPropagation();
        const plKey = btn.getAttribute('data-playlist-play');
        const mapping = {
          'chill': findTrackIndex('spring-day'),
          'hyper': findTrackIndex('fire'),
          'midnight': findTrackIndex('the-truth-untold'),
          'solo': findTrackIndex('seven')
        };
        const idx = mapping[plKey] !== undefined ? mapping[plKey] : 0;
        if (window.audioPlayer) {
          window.audioPlayer.loadTrack(idx, true);
          this.showToast(`Playing ${btn.getAttribute('aria-label') || 'curated playlist'}`);
        }
      });
    });
  }

  bindTrackOptions() {
    const modal = document.getElementById('trackOptionsModal');
    const closeBtn = document.getElementById('closeTrackOptionsBtn');

    document.querySelectorAll('.track-menu-btn').forEach(btn => {
      btn.addEventListener('click', (e) => {
        e.stopPropagation();
        const trackTitle = btn.getAttribute('data-track-title') || 'BTS Track';
        const modalTitle = document.getElementById('optionsModalTrackTitle');
        if (modalTitle) modalTitle.textContent = trackTitle;
        if (modal) modal.classList.remove('hidden');
      });
    });

    if (closeBtn && modal) {
      closeBtn.addEventListener('click', () => modal.classList.add('hidden'));
    }

    // Modal Action Buttons
    document.querySelectorAll('.options-action-btn').forEach(btn => {
      btn.addEventListener('click', () => {
        const action = btn.getAttribute('data-action');
        if (modal) modal.classList.add('hidden');
        if (action === 'share') {
          this.shareCurrentLink();
        } else if (action === 'queue') {
          this.showToast('Added to ARMY Up Next Queue! 🎵');
        } else if (action === 'discography') {
          this.switchTab('army-hub');
        }
      });
    });
  }

  // Modals (Notifications & Profile)
  bindModals() {
    // Notification Drawer
    const notifBtn = document.getElementById('notificationBtn');
    const notifModal = document.getElementById('notificationModal');
    const closeNotifBtn = document.getElementById('closeNotificationBtn');

    if (notifBtn && notifModal) {
      notifBtn.addEventListener('click', () => notifModal.classList.remove('hidden'));
    }
    if (closeNotifBtn && notifModal) {
      closeNotifBtn.addEventListener('click', () => notifModal.classList.add('hidden'));
    }

    // Profile Modal
    const profileBtn = document.getElementById('profileBtn');
    const profileModal = document.getElementById('profileModal');
    const closeProfileBtn = document.getElementById('closeProfileBtn');

    if (profileBtn && profileModal) {
      profileBtn.addEventListener('click', () => profileModal.classList.remove('hidden'));
    }
    if (closeProfileBtn && profileModal) {
      closeProfileBtn.addEventListener('click', () => profileModal.classList.add('hidden'));
    }

    // Close on clicking backdrop
    [notifModal, profileModal, document.getElementById('trackOptionsModal'), document.getElementById('fullLyricsModal'), document.getElementById('setlistModal'), document.getElementById('archiveVideoModal')].forEach(m => {
      if (m) {
        m.addEventListener('click', (e) => {
          if (e.target === m) m.classList.add('hidden');
        });
      }
    });

    // Share Song Button in Player
    const shareBtn = document.getElementById('playerShareBtn');
    if (shareBtn) {
      shareBtn.addEventListener('click', () => {
        this.shareCurrentLink(true);
      });
    }
  }

  shareCurrentLink(withTrackInfo = false) {
    const track = window.audioPlayer ? window.audioPlayer.currentTrack : null;
    const text = (withTrackInfo && track)
      ? `Listening to "${track.title}" by ${track.artist} on BTS Music! 💜`
      : 'Listen to BTS on BTS Music 💜';

    if (navigator.share) {
      navigator.share({ title: 'BTS Music', text, url: window.location.href }).catch(() => {});
    } else if (navigator.clipboard && navigator.clipboard.writeText) {
      navigator.clipboard.writeText(window.location.href).then(() => {
        this.showToast('Link copied to clipboard! 💜');
      }).catch(() => {
        this.showToast(text);
      });
    } else {
      this.showToast(text);
    }
  }

  // Toast Notification
  showToast(message) {
    let toast = document.getElementById('btsMusicToast');
    if (!toast) {
      toast = document.createElement('div');
      toast.id = 'btsMusicToast';
      toast.className = 'fixed top-20 left-1/2 -translate-x-1/2 z-50 px-4 py-2 rounded-full glass-panel text-on-surface text-xs md:text-sm font-semibold shadow-lg transition-all duration-300 pointer-events-none opacity-0 translate-y-[-10px] flex items-center gap-1.5';
      document.body.appendChild(toast);
    }

    toast.innerHTML = `<span class="text-primary">💜</span> <span>${message}</span>`;
    toast.classList.remove('opacity-0', 'translate-y-[-10px]');
    toast.classList.add('opacity-100', 'translate-y-0');

    clearTimeout(this.toastTimeout);
    this.toastTimeout = setTimeout(() => {
      toast.classList.remove('opacity-100', 'translate-y-0');
      toast.classList.add('opacity-0', 'translate-y-[-10px]');
    }, 2400);
  }
}

window.app = new BTSMusicApp();
