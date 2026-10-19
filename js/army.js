// ==========================================================================
// BTS Music — ARMY Hub, Discography & Video Archive Engine
// ==========================================================================

class ArmyHubEngine {
  constructor() {
    this.members = window.BTS_DATA.members;
    this.visualArchive = window.BTS_DATA.visualArchive;
    this.curatedPlaylists = window.BTS_DATA.curatedPlaylists;
    this.milestones = window.BTS_DATA.milestones;
    this.currentMemberFilter = 'all';

    document.addEventListener('DOMContentLoaded', () => {
      this.init();
    });
  }

  init() {
    this.renderMemberSoloRail();
    this.renderMemberDiscography();
    this.renderVisualArchive();
    this.renderPlaylists();
    this.renderMilestones();
    this.bindVideoModal();
  }

  renderMemberSoloRail() {
    const rail = document.getElementById('memberSoloRail');
    if (!rail) return;
    rail.innerHTML = '';

    this.members.forEach(member => {
      const isSelected = member.id === this.currentMemberFilter;
      const chip = document.createElement('button');
      chip.type = 'button';
      chip.className = `flex flex-col items-center gap-1.5 flex-shrink-0 group cursor-pointer focus:outline-none transition-all`;
      chip.setAttribute('data-member-id', member.id);

      chip.innerHTML = `
        <div class="relative w-12 h-12 rounded-full overflow-hidden p-0.5 transition-transform duration-200 group-hover:scale-105 ${isSelected ? 'ring-2 ring-primary ring-offset-2 ring-offset-surface' : 'opacity-75 group-hover:opacity-100'}">
          <img src="${member.avatar}" alt="${member.name}" class="w-full h-full object-cover rounded-full">
          ${isSelected ? '<span class="absolute bottom-0 right-0 w-3 h-3 rounded-full bg-primary ring-1 ring-surface flex items-center justify-center text-[7px] text-on-primary">✓</span>' : ''}
        </div>
        <span class="text-[11px] font-medium ${isSelected ? 'text-primary font-bold' : 'text-on-surface-variant group-hover:text-on-surface'} truncate max-w-[64px]">
          ${member.name}
        </span>
      `;

      chip.addEventListener('click', () => {
        this.selectMemberFilter(member.id);
      });

      rail.appendChild(chip);
    });
  }

  selectMemberFilter(memberId) {
    this.currentMemberFilter = memberId;
    this.renderMemberSoloRail();
    this.renderMemberDiscography();

    const member = this.members.find(m => m.id === memberId) || this.members[0];
    const contextEl = document.getElementById('memberFilterContext');
    if (contextEl) {
      if (memberId === 'all') {
        contextEl.textContent = 'All BTS Era Discography & Solo Releases';
      } else {
        contextEl.textContent = `${member.fullName} (${member.role}) • Albums: ${member.albums.join(', ')}`;
      }
    }

    if (window.app) {
      window.app.showToast(`Filtered for: ${member.name}`);
    }
  }

  renderMemberDiscography() {
    const container = document.getElementById('memberDiscographyContainer');
    const titleEl = document.getElementById('discographyTitle');
    const countEl = document.getElementById('discographyTrackCount');
    if (!container) return;

    const allTracks = window.BTS_DATA.tracks || [];
    const filteredTracks = this.currentMemberFilter === 'all'
      ? allTracks
      : allTracks.filter(t => t.member === this.currentMemberFilter);

    const member = this.members.find(m => m.id === this.currentMemberFilter) || this.members[0];

    if (titleEl) {
      titleEl.textContent = this.currentMemberFilter === 'all'
        ? 'Complete BTS & Solo Catalog (YouTube Music)'
        : `${member.fullName} (${member.name}) Discography`;
    }
    if (countEl) {
      countEl.textContent = `${filteredTracks.length} Songs`;
    }

    container.innerHTML = '';

    if (filteredTracks.length === 0) {
      container.innerHTML = `
        <div class="py-6 text-center text-on-surface-variant text-xs">
          No songs found for this member.
        </div>
      `;
      return;
    }

    filteredTracks.forEach((track) => {
      const globalIdx = allTracks.findIndex(t => t.id === track.id);
      const row = document.createElement('div');
      row.className = 'flex items-center justify-between p-2 rounded-lg hover:bg-surface-container-high transition-colors group cursor-pointer border border-transparent hover:border-outline-variant/15';

      row.innerHTML = `
        <div class="flex items-center gap-2.5 min-w-0 flex-1">
          <img src="${track.cover}" alt="${track.title}" class="w-10 h-10 rounded object-cover flex-shrink-0 shadow-sm">
          <div class="flex flex-col min-w-0">
            <span class="text-xs font-semibold text-on-surface truncate group-hover:text-primary transition-colors flex items-center gap-1.5">
              ${track.title}
              <span class="text-[9px] uppercase px-1.5 py-0.2 rounded bg-surface-container-high text-on-surface-variant font-code">${track.era || 'BTS'}</span>
            </span>
            <span class="text-[11px] text-on-surface-variant truncate">
              ${track.artist} • ${track.album} • <span class="text-primary">${track.streams || 'Hit'}</span>
            </span>
          </div>
        </div>
        <div class="flex items-center gap-1.5 flex-shrink-0">
          <button data-yt-url="${track.ytMusicUrl || ('https://music.youtube.com/watch?v=' + track.youtubeId)}" class="yt-stream-direct-btn px-2 py-1 rounded-full bg-red-600/10 hover:bg-red-600 hover:text-white text-red-400 text-[10px] font-semibold transition-all flex items-center gap-0.5" title="Stream on YouTube Music" type="button">
            <span class="material-symbols-outlined text-xs">smart_display</span>
            <span class="hidden sm:inline">YT</span>
          </button>
          <button class="play-track-direct-btn w-7 h-7 rounded-full bg-primary/20 text-primary hover:bg-primary hover:text-on-primary flex items-center justify-center transition-all active:scale-95" type="button">
            <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">play_arrow</span>
          </button>
        </div>
      `;

      row.addEventListener('click', (e) => {
        if (e.target.closest('.yt-stream-direct-btn')) {
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


  renderVisualArchive() {
    const grid = document.getElementById('visualArchiveGrid');
    if (!grid) return;
    grid.innerHTML = '';

    this.visualArchive.forEach(item => {
      const tile = document.createElement('div');
      tile.className = 'flex flex-col bg-surface-container-low/70 border border-outline-variant/15 rounded-xl overflow-hidden group cursor-pointer hover:border-outline-variant/30 transition-all';
      tile.innerHTML = `
        <div class="relative w-full aspect-video bg-surface-container-high overflow-hidden">
          <img src="${item.cover}" alt="${item.title}" class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-300">
          <div class="absolute inset-0 bg-gradient-to-t from-surface-container-lowest/70 via-transparent to-transparent"></div>
          <div class="absolute top-1.5 right-1.5 px-1.5 py-0.5 rounded bg-black/85 text-white font-medium text-[9px] uppercase tracking-wide">
            ${item.quality}
          </div>
          <div class="absolute bottom-1.5 left-1.5 px-1.5 py-0.5 rounded bg-black/85 text-white text-[10px] font-code">
            ${item.duration}
          </div>
          <div class="absolute inset-0 flex items-center justify-center opacity-70 group-hover:opacity-100 transition-opacity">
            <div class="w-8 h-8 rounded-full bg-primary text-on-primary flex items-center justify-center group-hover:scale-110 transition-transform">
              <span class="material-symbols-outlined text-base" style="font-variation-settings: 'FILL' 1;">play_arrow</span>
            </div>
          </div>
        </div>
        <div class="p-2.5 flex flex-col min-w-0">
          <span class="text-xs font-semibold text-on-surface truncate">${item.title}</span>
          <div class="flex items-center justify-between text-on-surface-variant text-[11px] mt-1">
            <span>${item.type}</span>
            <span class="text-primary font-medium">${item.views}</span>
          </div>
        </div>
      `;

      tile.addEventListener('click', () => {
        this.openVideoModal(item);
      });

      grid.appendChild(tile);
    });
  }

  renderPlaylists() {
    const container = document.getElementById('curatedPlaylistsContainer');
    if (!container) return;
    container.innerHTML = '';

    this.curatedPlaylists.forEach(pl => {
      const row = document.createElement('div');
      row.className = 'flex items-center justify-between p-2.5 rounded-xl bg-surface-container-low/70 border border-outline-variant/15 hover:bg-surface-container-high/40 hover:border-outline-variant/30 transition-all group';
      row.innerHTML = `
        <div class="flex items-center gap-3 min-w-0 flex-1">
          <div class="w-11 h-11 rounded-lg overflow-hidden bg-surface-container-highest flex-shrink-0">
            <img src="${pl.cover}" alt="${pl.title}" class="w-full h-full object-cover">
          </div>
          <div class="flex flex-col min-w-0">
            <span class="text-xs sm:text-sm font-semibold text-on-surface truncate">${pl.title}</span>
            <span class="text-on-surface-variant flex items-center gap-1.5 text-[11px] truncate">
              <span class="text-primary font-medium">${pl.curator}</span> • ${pl.trackCount} tracks • ${pl.saves} saves
            </span>
          </div>
        </div>
        <button aria-label="Play ${pl.title}" class="w-8 h-8 rounded-full bg-surface-container-high/60 text-primary flex items-center justify-center flex-shrink-0 hover:bg-primary hover:text-on-primary transition-all active:scale-95 ml-2" type="button">
          <span class="material-symbols-outlined text-base" style="font-variation-settings: 'FILL' 1;">play_arrow</span>
        </button>
      `;

      row.querySelector('button').addEventListener('click', (e) => {
        e.stopPropagation();
        this.playPlaylist(pl);
      });

      row.addEventListener('click', () => {
        this.playPlaylist(pl);
      });

      container.appendChild(row);
    });
  }

  playPlaylist(playlist) {
    if (playlist.trackIds && playlist.trackIds.length > 0 && window.audioPlayer) {
      const firstId = playlist.trackIds[0];
      const idx = window.BTS_DATA.tracks.findIndex(t => t.id === firstId);
      if (idx !== -1) {
        window.audioPlayer.loadTrack(idx, true);
        if (window.app) {
          window.app.showToast(`Playing playlist: ${playlist.title}`);
        }
      }
    }
  }

  renderMilestones() {
    const container = document.getElementById('milestoneGoalsContainer');
    if (!container) return;
    container.innerHTML = '';

    this.milestones.forEach(m => {
      const card = document.createElement('div');
      card.className = 'p-3 rounded-xl bg-surface-container-high/30 border border-outline-variant/15 flex flex-col gap-1.5';
      card.innerHTML = `
        <div class="flex items-center justify-between text-xs font-semibold">
          <span class="text-on-surface truncate flex items-center gap-1">
            <span class="text-primary">💜</span> ${m.title}
          </span>
          <span class="text-primary font-code">${m.percent}%</span>
        </div>
        <div class="w-full bg-surface-container-highest/60 h-1.5 rounded-full overflow-hidden">
          <div class="bg-primary h-full rounded-full transition-all duration-700" style="width: ${m.percent}%;"></div>
        </div>
        <div class="flex items-center justify-between text-[11px] text-on-surface-variant font-code">
          <span>${m.current}${m.unit} / ${m.target}${m.unit}</span>
          <button class="text-primary font-medium hover:underline flex items-center gap-0.5 stream-goal-btn">
            Stream <span class="material-symbols-outlined text-xs">arrow_forward</span>
          </button>
        </div>
      `;

      card.querySelector('.stream-goal-btn').addEventListener('click', () => {
        if (window.audioPlayer) {
          window.audioPlayer.loadTrack(0, true);
          if (window.app) window.app.showToast(`Streaming track for chart goal! 📈`);
        }
      });

      container.appendChild(card);
    });
  }

  bindVideoModal() {
    const modal = document.getElementById('archiveVideoModal');
    const closeBtn = document.getElementById('closeArchiveVideoBtn');
    if (closeBtn && modal) {
      closeBtn.addEventListener('click', () => {
        this.closeVideoModal();
      });
    }
  }

  openVideoModal(item) {
    const modal = document.getElementById('archiveVideoModal');
    const titleEl = document.getElementById('archiveVideoTitle');
    const playerContainer = document.getElementById('archiveVideoPlayer');
    if (!modal || !playerContainer) return;

    if (titleEl) titleEl.textContent = `${item.title} (${item.quality})`;

    // Pause background audio
    if (window.audioPlayer && window.audioPlayer.isPlaying) {
      window.audioPlayer.pause();
    }

    playerContainer.innerHTML = `
      <iframe class="w-full h-full rounded-xl" src="https://www.youtube.com/embed/${item.youtubeId}?autoplay=1&rel=0&modestbranding=1" title="${item.title}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    `;

    modal.classList.remove('hidden');
  }

  closeVideoModal() {
    const modal = document.getElementById('archiveVideoModal');
    const playerContainer = document.getElementById('archiveVideoPlayer');
    if (playerContainer) playerContainer.innerHTML = '';
    if (modal) modal.classList.add('hidden');
  }
}

window.armyHubEngine = new ArmyHubEngine();
