// ==========================================================================
// BTS Music — Synchronized Lyrics Engine
// ==========================================================================

class LyricsEngine {
  constructor() {
    this.lyrics = [];
    this.currentLineIndex = -1;
    this.selectedLanguage = 'all'; // 'all' | 'hangul' | 'rom' | 'eng'
    this.container = null;
    this.isFullLyricsOpen = false;

    // Wait for DOM
    document.addEventListener('DOMContentLoaded', () => {
      this.init();
    });
  }

  init() {
    this.container = document.getElementById('lyricsContainer');
    this.bindLanguageTabs();
    this.bindFullLyricsToggle();
    if (window.audioPlayer && window.audioPlayer.currentTrack) {
      this.loadLyrics(window.audioPlayer.currentTrack.lyrics);
    }
  }

  bindLanguageTabs() {
    const tabs = document.querySelectorAll('.lang-tab');
    tabs.forEach(tab => {
      tab.addEventListener('click', () => {
        tabs.forEach(t => {
          t.classList.remove('bg-primary', 'text-on-primary', 'font-bold', 'shadow-sm');
          t.classList.add('text-on-surface-variant');
        });
        tab.classList.add('bg-primary', 'text-on-primary', 'font-bold', 'shadow-sm');
        tab.classList.remove('text-on-surface-variant');

        this.selectedLanguage = tab.getAttribute('data-lang') || 'all';
        this.renderLyrics();
      });
    });
  }

  bindFullLyricsToggle() {
    const fullLyricsBtn = document.getElementById('fullLyricsBtn');
    const fullLyricsModal = document.getElementById('fullLyricsModal');
    const closeFullLyricsBtn = document.getElementById('closeFullLyricsBtn');

    if (fullLyricsBtn && fullLyricsModal) {
      fullLyricsBtn.addEventListener('click', () => {
        this.openFullLyrics();
      });
    }
    if (closeFullLyricsBtn && fullLyricsModal) {
      closeFullLyricsBtn.addEventListener('click', () => {
        fullLyricsModal.classList.add('hidden');
      });
    }
  }

  openFullLyrics() {
    const modal = document.getElementById('fullLyricsModal');
    const modalContent = document.getElementById('fullLyricsContent');
    const modalTitle = document.getElementById('fullLyricsTrackTitle');
    if (!modal || !modalContent) return;

    const track = window.audioPlayer ? window.audioPlayer.currentTrack : null;
    if (modalTitle && track) {
      modalTitle.textContent = `${track.title} — Full Lyrics`;
    }

    modalContent.innerHTML = '';
    this.lyrics.forEach((line, idx) => {
      const lineDiv = document.createElement('div');
      lineDiv.className = `p-3 rounded-xl transition-all cursor-pointer hover:bg-surface-container-high ${idx === this.currentLineIndex ? 'bg-primary/10 border border-primary/30' : ''}`;
      lineDiv.addEventListener('click', () => {
        if (window.audioPlayer) {
          window.audioPlayer.seekTo(line.time);
          modal.classList.add('hidden');
        }
      });

      lineDiv.innerHTML = `
        <div class="flex items-center gap-2 mb-1">
          <span class="text-[11px] font-bold text-primary tracking-wider uppercase">${line.member}</span>
          <span class="text-[10px] text-on-surface-variant font-code">${window.audioPlayer.formatTime(line.time)}</span>
        </div>
        <p class="text-on-surface font-semibold text-base mb-0.5">${line.hangul}</p>
        <p class="text-on-surface-variant text-sm font-code mb-0.5">${line.rom}</p>
        <p class="text-primary/90 text-sm font-medium italic">${line.eng}</p>
      `;
      modalContent.appendChild(lineDiv);
    });

    modal.classList.remove('hidden');
  }

  loadLyrics(lyricsArray) {
    this.lyrics = lyricsArray || [];
    this.currentLineIndex = -1;
    this.renderLyrics();
  }

  renderLyrics() {
    if (!this.container) return;
    this.container.innerHTML = '';

    if (!this.lyrics || this.lyrics.length === 0) {
      this.container.innerHTML = `
        <div class="py-8 text-center text-on-surface-variant/60 text-sm">
          <span class="material-symbols-outlined text-3xl mb-1 text-primary/40">queue_music</span>
          <p>Instrumental or Live audio previewing</p>
        </div>
      `;
      return;
    }

    this.lyrics.forEach((line, index) => {
      const lineEl = document.createElement('div');
      lineEl.id = `lyric-line-${index}`;
      lineEl.className = 'lyric-line py-1.5 flex flex-col gap-0.5 cursor-pointer rounded-lg px-2 transition-all duration-300';
      lineEl.addEventListener('click', () => {
        if (window.audioPlayer) {
          window.audioPlayer.seekTo(line.time);
        }
      });

      // Member Badge
      const memberHtml = `
        <div class="flex items-center gap-1.5 line-member">
          <span class="w-1.5 h-1.5 rounded-full bg-primary member-dot ${index === this.currentLineIndex ? 'animate-ping' : 'opacity-0'}"></span>
          <span class="text-label-sm font-label-sm font-bold text-primary tracking-widest uppercase">${line.member}</span>
        </div>
      `;

      // Main Text based on Language Selection
      let mainTextHtml = '';
      if (this.selectedLanguage === 'hangul') {
        mainTextHtml = `<p class="line-text font-bold text-base md:text-lg">${line.hangul}</p>`;
      } else if (this.selectedLanguage === 'rom') {
        mainTextHtml = `<p class="line-text font-medium text-sm md:text-base font-code">${line.rom}</p>`;
      } else if (this.selectedLanguage === 'eng') {
        mainTextHtml = `<p class="line-text font-bold text-base md:text-lg">${line.eng}</p>`;
      } else {
        // 'all' Mode
        mainTextHtml = `
          <p class="line-text font-bold text-base md:text-lg leading-snug">${line.hangul}</p>
          <span class="line-sub text-xs md:text-sm text-primary/80 font-code">${line.rom}</span>
          <span class="line-eng text-xs md:text-sm text-on-surface-variant/90 italic">${line.eng}</span>
        `;
      }

      lineEl.innerHTML = memberHtml + mainTextHtml;
      this.container.appendChild(lineEl);
    });

    this.updateLineStyles();
  }

  syncTime(currentTime) {
    if (!this.lyrics || this.lyrics.length === 0) return;

    let activeIndex = -1;
    for (let i = 0; i < this.lyrics.length; i++) {
      if (currentTime >= this.lyrics[i].time) {
        activeIndex = i;
      } else {
        break;
      }
    }

    if (activeIndex !== this.currentLineIndex) {
      this.currentLineIndex = activeIndex;
      this.updateLineStyles();
      this.scrollToActiveLine();
    }
  }

  updateLineStyles() {
    this.lyrics.forEach((_, idx) => {
      const lineEl = document.getElementById(`lyric-line-${idx}`);
      if (!lineEl) return;

      const memberDot = lineEl.querySelector('.member-dot');

      if (idx === this.currentLineIndex) {
        // Active Sung Line
        lineEl.className = 'lyric-line py-2 flex flex-col gap-1 cursor-pointer rounded-lg px-2.5 bg-primary/10 border-l-2 border-primary transition-all duration-200 active';
        const pTag = lineEl.querySelector('.line-text');
        if (pTag) {
          pTag.className = 'line-text font-bold text-base md:text-lg leading-snug';
        }
        if (memberDot) memberDot.classList.remove('opacity-0');
      } else if (idx < this.currentLineIndex) {
        // Past line
        lineEl.className = 'lyric-line py-1 flex flex-col gap-0.5 cursor-pointer rounded-lg px-2 opacity-40 hover:opacity-75 transition-opacity';
        const pTag = lineEl.querySelector('.line-text');
        if (pTag) {
          pTag.className = 'line-text font-normal text-sm md:text-base text-on-surface-variant';
        }
        if (memberDot) memberDot.classList.add('opacity-0');
      } else {
        // Upcoming line
        lineEl.className = 'lyric-line py-1 flex flex-col gap-0.5 cursor-pointer rounded-lg px-2 opacity-60 hover:opacity-90 transition-opacity';
        const pTag = lineEl.querySelector('.line-text');
        if (pTag) {
          pTag.className = 'line-text font-medium text-sm md:text-base text-on-surface-variant';
        }
        if (memberDot) memberDot.classList.add('opacity-0');
      }
    });
  }

  scrollToActiveLine() {
    if (this.currentLineIndex < 0 || !this.container) return;
    const activeEl = document.getElementById(`lyric-line-${this.currentLineIndex}`);
    if (activeEl) {
      activeEl.scrollIntoView({ behavior: 'smooth', block: 'center' });
    }
  }
}

window.lyricsEngine = new LyricsEngine();
