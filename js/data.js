// ==========================================================================
// BTS Music — Complete BTS & Solo Discography (120+ Tracks via YouTube Music)
// ==========================================================================

const COVERS = {
  proof: 'https://lh3.googleusercontent.com/aida-public/AB6AXuB7YkJ2-YJGwmqeausKG31j5nqLLNGNzh0QHik4h_CRiwsW5eSh_RG_1L1DQC2zAP0yN910W9CAjQncSQFOScWDuuQgOHcDaHl7xSIKh_Rpd5o2jERu6JyTWgR7BR-XCTxLJ-jPZQlkzytzX5SLlRcG9OZlghtEpcCN2qtdG6xUnnmki_1JX2IC4FhqqO1sHNYfl0u7v1Yzd6CL0BTikh_Wm6jzfht4kC5othD9wdRh3HPisbKBen6b',
  mots7: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCpGro_K7DPpVY3kJllccZFR0xkodkJQ3i21RAeJZKb-fQhevV6XZR-SYTAcRfJN9FjTYo0e0QyXqQdC-B9Z8YRGaOT89LhdsTR181jvG2mVrlM3icYslNWBXjjeb3W4jitxtWdx5fAYHthHhHAcDCzORtBkGDWmD8TS2aq54r-w9fiht5aYguNiGaxmu0XMfZLuBHp2IJZfeCP2WQPgHuuEpMLVYk5sBTr3zca1ET78JLgfexjOSVa',
  be: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCu2mc3BLNeAuiNbRfcXeCuvvAmACh0MUerXJBiIcVPNMO11yZ3byrkbgDvrqirTjz0uytV49pX_kaGLxqk2f-6H3wbL79vWDJy7jRWzShi-eabufUUd1raNYsMM_tEokR9gyDnOWiIBwUrRF2GonKBzaH3uj6CJtjXIEhCjvZkBncXXCIbXkZqeWpPFMcXHX71V3OC86tlgve5_hQf15Dsx1DKDl1TtOA4bxHsCjJIin-0VJY3qcce',
  butter: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCLITa-vMteMN4deZym_H7SaniUfpRiB3NJO02i3InDD5bLa6Yi0x4Lnv0pXRyiliwsnJmGWWCcY9Fep5BXEwWs03Mgb0BPsX5M41chAuQJaVRgiikk3y5uIyKsl94mpP69NjU64SaWGcVcIjgYHFlKjcpROolCXBANLJclwRlTU79NdKEuv_8WpVW4nostTTsOQGzfsPOTVDAE6xkg4Y5HztwGA2nYbFv5xowLePrCOrRQDbKE31KG',
  golden: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDDZdS4I5OsbLS8-V7-E9HoHxu7mo3CmMhr1_76Vhbj1ezg5FyD_gIIIAVXDru2yzyP-v6Demuivf-Mhdjpn9AcWsyZv2MWt1tA7cUWEUTGAJfz-J3emD-1oKw2Z4FeSMNdbeUgz1GlfNZkYXmvAa98ldZ8xoz9tucUPRsK1x2J83vjcwQGAXj6eu28VM7GTYEb7kfJeRnmij_bMhAC8kOAeBQI7fGDpLHNyYli6c4l43D4mP9bGNnw',
  tear: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBmalZTjehco6h9a9cOWW7T9ZWy8VZnpWEoVlWO8gNWOm-gGa1SENap-ypWY217nQjkwzA037XNEPjylYoVyWoF4T9inmxUvkprYef1_qAkomHOxCR976RErzRgGN-UzwWZkZnRhFDsC32s14KuTOkPYODItZlWgF4feBy00z6i3US7RCXzAvfIH90LwtirEGdygoLXs-on5aFHDu8JbwCRXcWR-F3FEX27xAgdW_IWG2lmuv94DYuQ',
  wings: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAbPtNkUy_-fHgrQYMhruiaLhgOw4zLGf1zQ26l1MTPavZPTpc-VdVt1Wcaaxoi9-9xkbWiJnR2qcSYP5Sza7afCdBjqBbpfsg-bErZNOV1YwRSBWjCoThByrODvSU7Z9uPx1vQC-S6a8zoqMi7OWAtQdpgmWhAR7UsxeOeuIYxOObmORODLxud4aas3mpGUcbMGC1dx0JJ_k5eMmSlDzrgLuD4VdBWWccknQs_ODvrFazMLntPmiDv',
  ynwa: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBrg3_9Li4fbtxd-J3QeitFASYR3yMpB9D6ldDDGDuRs9PW-CW-8ZJHE6LU8-bgw3dqiUEkcqzgMipLbd98TrmyoDXdbKaDAgpJpXrSrAnFd6qni6ezWuc85eEuxhJhIMgcWBDBym2cpLWGuh67EKrGdriUVYpA0aOUnmacgyh9KFdzNk1LHcW1_dd2d6iewk-rFkcWCaKYL4NSRRoxpKPXa6VnGMyFtbQKCvL2RItFcLHX6UQM-2dh',
  indigo: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBZieYSN1d0vjiqGAwBYb1R5lqu2SDCKVX3a5SQxZEfSWdSdtRPsiWNtSO8-4yTWSpG_xA0mneFU_pI8QfvyRYv0lV3CtUi33I1KM2PE3bzbNekBgfwASPMdAhry3ucwd6v10BGSlKcF4lYRCLlzVRj-nU72KWJUO0edPZkxETqbDvRjNvmXQv-D2Xt7ZnuohTaceEbtX_mYnKMqeFnElo_eYSP2_009eagjk3CO8s3EPRTgpjqtpQC',
  face: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAtUSoKT7kSdEOXSvqiEhxXh84lqliz0d-O7RII5LiQEvvm5P55daVurdC7GbW9LAelyjqIVAGmHR9cNL5mTeUqfCQ0AWtTq0hDVxP2nMPpwFipeYoOoo_HGmPb2vR9-LPQ3pFFnE3KSaU12EZ3091JR4LZUeJd4O_yjIdgftYGbn8Kfam0ZG2mqJPjB_2LbKDwYmzTOorDGgIxHhMC2oNXuo956WrDn3bCHwKyyj9G0b79Uf44OYpy',
  layover: 'https://lh3.googleusercontent.com/aida-public/AB6AXuByPVwM2WMDJqx-kEnK-NvMrJx9NCy3dUuEe65Aocx8wmaD-2UaHcapOrQ_Kx6Pj7rOVc867WQqsd5g-dz0cbOf21-pfj3nW2P1_wrB34LxibhIVu54NMtMmmBMu_2sifHQCMOZOZrtch-BupoEDpy7GGx9kSMRD6CO9dCigZhl1pz3fNzIdsY9Wk2lgMu8VwGs7nsa_zA5HCGgc8agDClpFbl5Yi28aQeDXbAK9OvZ-PmgjXt9snc5',
  jitb: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCW80Iil1aLMDbxWElq5bNlhrQVvtCkmIBsLyP8mlufp8-2tfeehIAQFD0VcyA2A8ZuokL4HTg10q3glgmW1pAXwh9rjCjneiqa1PeOMDNQVKFlpmDgLdQOBSY0Nn0lEzOquF6OOlI-PoHUfZSGZPgKKZ2D9_vbvAtTuNPg0MVo7qjA01aHp0-7-aq5HKCu-50pPtLqoeOchswT7gS_tn0WEL0kxa96O-YaiELU2YzWENF7ZfJ8xfR9',
  astronaut: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDYEssWzZhb2Tq3Z8_mSsRwa9rYOiQg4LmsgrwWR0nnfdLOSOd7_o5tKj21BrvoT_TQMHut72T7dgTTCdti0IhXlcY6PUAfWteu8vkjgbH6_nc5SgI27aUnNwsl2e2ZID-GVuxOpd9G33xgTa3gpfb75m5ITFKUos1SBJQ_uki0wLhq3JESwp-_1ec4l7lED7BxXldSiNhjYZMLzrjwrAJeA8rD4uPYF7ZO76depKTBuDZx-KyK9TKP',
  dday: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBmalZTjehco6h9a9cOWW7T9ZWy8VZnpWEoVlWO8gNWOm-gGa1SENap-ypWY217nQjkwzA037XNEPjylYoVyWoF4T9inmxUvkprYef1_qAkomHOxCR976RErzRgGN-UzwWZkZnRhFDsC32s14KuTOkPYODItZlWgF4feBy00z6i3US7RCXzAvfIH90LwtirEGdygoLXs-on5aFHDu8JbwCRXcWR-F3FEX27xAgdW_IWG2lmuv94DYuQ',
  hyyh: 'https://lh3.googleusercontent.com/aida-public/AB6AXuADyHe3QyrneddgLJEInOBJbGMnwBdZS0NR2fcz-6hNuBQNLm2SbKN3nW_MTFDS3owKyr3mXst6htpmywBWDKswIRuZkIiCjCqgCwFbeAkDlwlwk4-rlp7wU5NZJnOtTYG4YLJnZ8ke9OEhE7Y1OZUcZG5FdvcKNBkK4yIRT_S62h6i_k9tb1okk15QsJWik15KfTHDSkD4C031VRxCPU_xTmq5Moeurd1nZdJACyaTTpTjBxCW0Drl',
  debut: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDliWwLM-WOfTDul8TMzDmYqlEQfusCmYyoYJ8w5bY4l5CI1zNGT6s4RY4p3pRRyXGOBDMZziKyzd00_nDR2tPYr5zcLVxpGFcjLQQLQ10fRY0gVLQVFyWYYwQ62eqZs9n0t2CShw8jb6-n5XKtB860pwn2RJEAHIWPw0S0x5jCa3FFf6IH-aKq7Sw2pq_mcltl45siBUJYtExRcCvAJ5OdIvIGAqy0B3nbDyYGCP6Yqv2h4GT09skJ',
  her: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCu2mc3BLNeAuiNbRfcXeCuvvAmACh0MUerXJBiIcVPNMO11yZ3byrkbgDvrqirTjz0uytV49pX_kaGLxqk2f-6H3wbL79vWDJy7jRWzShi-eabufUUd1raNYsMM_tEokR9gyDnOWiIBwUrRF2GonKBzaH3uj6CJtjXIEhCjvZkBncXXCIbXkZqeWpPFMcXHX71V3OC86tlgve5_hQf15Dsx1DKDl1TtOA4bxHsCjJIin-0VJY3qcce',
  answer: 'https://lh3.googleusercontent.com/aida-public/AB6AXuB7YkJ2-YJGwmqeausKG31j5nqLLNGNzh0QHik4h_CRiwsW5eSh_RG_1L1DQC2zAP0yN910W9CAjQncSQFOScWDuuQgOHcDaHl7xSIKh_Rpd5o2jERu6JyTWgR7BR-XCTxLJ-jPZQlkzytzX5SLlRcG9OZlghtEpcCN2qtdG6xUnnmki_1JX2IC4FhqqO1sHNYfl0u7v1Yzd6CL0BTikh_Wm6jzfht4kC5othD9wdRh3HPisbKBen6b',
  persona: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCpGro_K7DPpVY3kJllccZFR0xkodkJQ3i21RAeJZKb-fQhevV6XZR-SYTAcRfJN9FjTYo0e0QyXqQdC-B9Z8YRGaOT89LhdsTR181jvG2mVrlM3icYslNWBXjjeb3W4jitxtWdx5fAYHthHhHAcDCzORtBkGDWmD8TS2aq54r-w9fiht5aYguNiGaxmu0XMfZLuBHp2IJZfeCP2WQPgHuuEpMLVYk5sBTr3zca1ET78JLgfexjOSVa'
};

window.BTS_DATA = {
  // Comprehensive BTS Catalog across all eras and solo releases
  tracks: [
    // -------------------------------------------------------------------------
    // 1. TOP GLOBAL HITS & PRE-LOADED MASTER AUDIO
    // -------------------------------------------------------------------------
    {
      id: 'standing-next-to-you',
      title: 'Standing Next to You',
      artist: 'Jung Kook',
      album: 'GOLDEN',
      year: '2023',
      era: 'solo',
      member: 'jungkook',
      duration: 206,
      durationText: '3:26',
      streams: '1.2B',
      cover: COVERS.golden,
      audioSrc: 'audio/standing-next-to-you.m4a',
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview221/v4/17/e4/44/17e4443f-ccac-37e9-ae7e-c27652c8bb7c/mzaf_1611321858020850805.plus.aac.p.m4a',
      youtubeId: 'UNo0TG9LwwI',
      ytMusicUrl: 'https://music.youtube.com/watch?v=UNo0TG9LwwI',
      bpm: 106,
      audioQuality: '24-bit / 96kHz Lossless FLAC',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: 'Play me like a record', rom: 'Play me like a record', eng: 'Play me like a record' },
        { time: 10, member: 'Jung Kook', hangul: 'Standing in the fire next to you', rom: 'Standing in the fire next to you', eng: 'Standing in the fire next to you' },
        { time: 22, member: 'Jung Kook', hangul: 'You know it\'s deeper than the rain', rom: 'You know it\'s deeper than the rain', eng: 'You know it\'s deeper than the rain' },
        { time: 34, member: 'Jung Kook', hangul: 'It\'s deeper than the pain', rom: 'It\'s deeper than the pain', eng: 'It\'s deeper than the pain' },
        { time: 48, member: 'Jung Kook', hangul: 'When the music plays, let the rhythm take you', rom: 'When the music plays, let the rhythm take you', eng: 'When the music plays, let the rhythm take you' },
        { time: 64, member: 'Jung Kook', hangul: 'Standing next to you, through the fire', rom: 'Standing next to you, through the fire', eng: 'Standing next to you, through the fire' },
        { time: 82, member: 'Jung Kook', hangul: 'All day, all night, we will survive', rom: 'All day, all night, we will survive', eng: 'All day, all night, we will survive' }
      ]
    },
    {
      id: 'black-swan',
      title: 'Black Swan',
      artist: 'BTS',
      album: 'Map of the Soul: 7',
      year: '2020',
      era: 'mots',
      member: 'all',
      duration: 198,
      durationText: '3:18',
      streams: '950M',
      cover: COVERS.mots7,
      audioSrc: 'audio/black-swan.m4a',
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview221/v4/0b/7e/ae/0b7eae3c-7c88-3ac9-945b-035e7037140e/mzaf_2026432743775274290.plus.aac.p.m4a',
      youtubeId: '0lapF4DQPKQ',
      ytMusicUrl: 'https://music.youtube.com/watch?v=0lapF4DQPKQ',
      bpm: 147,
      audioQuality: 'Master Studio Audio 48kHz',
      lyrics: [
        { time: 0, member: 'SUGA', hangul: '빛이 없는 바다, 그래, 나 또 발을 담가', rom: 'Bichi eomneun bada, geurae, na tto bareul damga', eng: 'An ocean with no light, yeah, dipping my feet in again' },
        { time: 14, member: 'Jimin', hangul: '수없이 헤매어봐도 제자리인 걸', rom: 'Sueopsi hemaeeobwado jejariin geol', eng: 'Even if I wander endlessly, I end up back here' },
        { time: 26, member: 'RM / Jimin', hangul: '“Do your thang, do your thang with me now ♫”', rom: 'Do your thang, do your thang with me now', eng: 'Do your thang, do your thang with me now ♫' },
        { time: 42, member: 'Jung Kook', hangul: '날 막지 못해 (Can’t hold me back anymore)', rom: 'Nal makji mothae', eng: 'Can’t hold me back anymore' },
        { time: 58, member: 'V', hangul: 'What’s my thang, what’s my thang tell me now', rom: 'What’s my thang, what’s my thang tell me now', eng: 'What’s my thang, what’s my thang tell me now' },
        { time: 75, member: 'Jin', hangul: '더 깊은 침묵이 밀려와 발을 적셔', rom: 'Deo gipeun chimmugi millyeowa bareul jeoksyeo', eng: 'A deeper silence rolls in, drenching my feet' }
      ]
    },
    {
      id: 'dynamite',
      title: 'Dynamite',
      artist: 'BTS',
      album: 'BE',
      year: '2020',
      era: 'be_butter',
      member: 'all',
      duration: 199,
      durationText: '3:19',
      streams: '1.8B',
      cover: COVERS.be,
      audioSrc: 'audio/dynamite.m4a',
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview221/v4/a3/a0/a6/a3a0a6b5-4715-b872-1aff-21e42634d9cd/mzaf_7224423421767640589.plus.aac.p.m4a',
      youtubeId: 'gdZLi9oWNZg',
      ytMusicUrl: 'https://music.youtube.com/watch?v=gdZLi9oWNZg',
      bpm: 114,
      audioQuality: '24-bit / 96kHz Lossless FLAC',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '\'Cause I, I, I\'m in the stars tonight', rom: '\'Cause I, I, I\'m in the stars tonight', eng: '\'Cause I, I, I\'m in the stars tonight' },
        { time: 12, member: 'Jung Kook', hangul: 'So watch me bring the fire and set the night alight', rom: 'So watch me bring the fire and set the night alight', eng: 'So watch me bring the fire and set the night alight' },
        { time: 24, member: 'Jimin', hangul: 'Shoes on, get up in the morn\', cup of milk, let\'s rock and roll', rom: 'Shoes on, get up in the morn\', cup of milk, let\'s rock and roll', eng: 'Shoes on, get up in the morn\', cup of milk, let\'s rock and roll' },
        { time: 38, member: 'RM', hangul: 'King Kong, kick the drum, rolling on like a Rolling Stone', rom: 'King Kong, kick the drum, rolling on like a Rolling Stone', eng: 'King Kong, kick the drum, rolling on like a Rolling Stone' }
      ]
    },
    {
      id: 'butter',
      title: 'Butter',
      artist: 'BTS',
      album: 'Butter',
      year: '2021',
      era: 'be_butter',
      member: 'all',
      duration: 164,
      durationText: '2:44',
      streams: '1.3B',
      cover: COVERS.butter,
      audioSrc: 'audio/butter.m4a',
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview211/v4/80/c8/46/80c84615-efb7-b538-d818-226cf7803358/mzaf_11498582023380901573.plus.aac.p.m4a',
      youtubeId: 'WMweEpGlu_U',
      ytMusicUrl: 'https://music.youtube.com/watch?v=WMweEpGlu_U',
      bpm: 110,
      audioQuality: 'Dolby Atmos Spatial Audio',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: 'Smooth like butter, like a criminal undercover', rom: 'Smooth like butter, like a criminal undercover', eng: 'Smooth like butter, like a criminal undercover' },
        { time: 12, member: 'V', hangul: 'Gon\' pop like trouble breaking into your heart like that', rom: 'Gon\' pop like trouble breaking into your heart like that', eng: 'Gon\' pop like trouble breaking into your heart like that' },
        { time: 24, member: 'Jin', hangul: 'Cool shade, stunner, yeah, I owe it all to my mother', rom: 'Cool shade, stunner, yeah, I owe it all to my mother', eng: 'Cool shade, stunner, yeah, I owe it all to my mother' }
      ]
    },
    {
      id: 'spring-day',
      title: 'Spring Day (봄날)',
      artist: 'BTS',
      album: 'You Never Walk Alone',
      year: '2017',
      era: 'wings',
      member: 'all',
      duration: 274,
      durationText: '4:34',
      streams: '980M',
      cover: COVERS.ynwa,
      audioSrc: 'audio/spring-day.m4a',
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview221/v4/ba/76/d5/ba76d586-0dac-c800-f08d-467aef7dc9c0/mzaf_12707603572181808804.plus.aac.p.m4a',
      youtubeId: 'xEeFrLSkMm8',
      ytMusicUrl: 'https://music.youtube.com/watch?v=xEeFrLSkMm8',
      bpm: 108,
      audioQuality: 'Hi-Res Lossless 24-bit',
      lyrics: [
        { time: 0, member: 'RM', hangul: '보고 싶다 이렇게 말하니까 더 보고 싶다', rom: 'Bogo sipda ireoke malhanikka deo bogo sipda', eng: 'I miss you, saying this makes me miss you even more' },
        { time: 16, member: 'RM', hangul: '너희 사진을 보고 있어도 보고 싶다', rom: 'Neohui sajineul bogo isseodo bogo sipda', eng: 'Even when looking at your photos, I miss you' },
        { time: 32, member: 'SUGA', hangul: '야속한 시간 나는 우리가 밉다', rom: 'Yasokan sigan naneun uriga mipda', eng: 'Cruel time, I resent us now' }
      ]
    },
    {
      id: 'blood-sweat-tears',
      title: 'Blood Sweat & Tears (피 땀 눈물)',
      artist: 'BTS',
      album: 'WINGS',
      year: '2016',
      era: 'wings',
      member: 'all',
      duration: 217,
      durationText: '3:37',
      streams: '850M',
      cover: COVERS.wings,
      audioSrc: 'audio/blood-sweat-tears.m4a',
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview221/v4/c6/38/53/c63853cb-ac99-bcd7-2aae-32c89e75a618/mzaf_13843048239119273956.plus.aac.p.m4a',
      youtubeId: 'hmE9f-TEutc',
      ytMusicUrl: 'https://music.youtube.com/watch?v=hmE9f-TEutc',
      bpm: 93,
      audioQuality: 'Mastered for BTS Music Audio',
      lyrics: [
        { time: 0, member: 'Jimin', hangul: '내 피 땀 눈물 내 마지막 춤을 다 가져가 가', rom: 'Nae pi ttam nunmul nae majimak chumeul da gajyeoga ga', eng: 'My blood, sweat and tears, my last dance, take it all away' },
        { time: 16, member: 'Jung Kook', hangul: '내 차가운 숨을 다 가져가 가', rom: 'Nae chagaun sumeul da gajyeoga ga', eng: 'Take away my cold breath' }
      ]
    },
    {
      id: 'yet-to-come',
      title: 'Yet To Come (The Most Beautiful Moment)',
      artist: 'BTS',
      album: 'Proof',
      year: '2022',
      era: 'proof',
      member: 'all',
      duration: 224,
      durationText: '3:44',
      streams: '720M',
      cover: COVERS.proof,
      audioSrc: 'audio/yet-to-come.m4a',
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview221/v4/12/a9/3d/12a93d0b-036b-170b-09fa-379d785e63b7/mzaf_17633343337902112849.plus.aac.p.m4a',
      youtubeId: 'kXpOEzNZ8hQ',
      ytMusicUrl: 'https://music.youtube.com/watch?v=kXpOEzNZ8hQ',
      bpm: 86,
      audioQuality: 'Dolby Atmos Spatial Audio',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: 'Was it honestly the best? \'Cause I just wanna see the next', rom: 'Was it honestly the best? \'Cause I just wanna see the next', eng: 'Was it honestly the best? \'Cause I just wanna see the next' },
        { time: 18, member: 'Jin', hangul: '눈부시게 지나간 기억들 속에', rom: 'Nunbusige jinagan gieokdeul soge', eng: 'Inside the memories that passed dazzlingly' }
      ]
    },
    {
      id: 'boy-with-luv',
      title: 'Boy With Luv (작은 것들을 위한 시 feat. Halsey)',
      artist: 'BTS',
      album: 'Map of the Soul: Persona',
      year: '2019',
      era: 'mots',
      member: 'all',
      duration: 230,
      durationText: '3:50',
      streams: '1.7B',
      cover: COVERS.persona,
      audioSrc: null,
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview221/v4/91/92/ff/9192ffaa-bb38-ebcb-dbf5-e4242fe41b31/mzaf_13508491873155700878.plus.aac.p.m4a',
      youtubeId: 'XsX3ATc3FbA',
      ytMusicUrl: 'https://music.youtube.com/watch?v=XsX3ATc3FbA',
      bpm: 120,
      audioQuality: 'YouTube Music High Fidelity',
      lyrics: [
        { time: 0, member: 'Jimin', hangul: '모든 게 궁금해 how\'s your day', rom: 'Modeun ge gunggeumhae how\'s your day', eng: 'I\'m curious about everything, how\'s your day?' },
        { time: 14, member: 'V', hangul: '널 알게 된 이후, ya 내 삶은 온통 너, ya', rom: 'Neol alge doen ihu, ya nae salmeun ontong neo, ya', eng: 'Since I met you, my life is all about you' },
        { time: 30, member: 'Jung Kook', hangul: '사소한 게 사소하지 않게 만들어버린 너라는 별', rom: 'Sasohan ge sasohaji anke mandeureobeorin neoraneun byeol', eng: 'You\'re the star that turns trivial things into something extraordinary' }
      ]
    },
    {
      id: 'dna',
      title: 'DNA',
      artist: 'BTS',
      album: 'Love Yourself 承 \'Her\'',
      year: '2017',
      era: 'loveyourself',
      member: 'all',
      duration: 223,
      durationText: '3:43',
      streams: '1.6B',
      cover: COVERS.her,
      audioSrc: null,
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview221/v4/c6/38/53/c63853cb-ac99-bcd7-2aae-32c89e75a618/mzaf_13843048239119273956.plus.aac.p.m4a',
      youtubeId: 'MBdVXkSdhwU',
      ytMusicUrl: 'https://music.youtube.com/watch?v=MBdVXkSdhwU',
      bpm: 130,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '첫눈에 널 알아보게 됐어', rom: 'Cheotnune neol araboge dwaesseo', eng: 'At first sight, I recognized you' },
        { time: 14, member: 'V', hangul: '서롤 불러왔던 것처럼', rom: 'Seorol bulleowatdeon geotcheoreom', eng: 'As if we were calling for each other' },
        { time: 28, member: 'RM / Jimin', hangul: '내 혈관 속 DNA가 말해줘', rom: 'Nae hyeolgwan sok DNAga malhaejwo', eng: 'The DNA in my veins tells me' }
      ]
    },
    {
      id: 'fake-love',
      title: 'Fake Love',
      artist: 'BTS',
      album: 'Love Yourself 轉 \'Tear\'',
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 242,
      durationText: '4:02',
      streams: '1.4B',
      cover: COVERS.tear,
      audioSrc: null,
      youtubeId: '7C2z4GqqS5E',
      ytMusicUrl: 'https://music.youtube.com/watch?v=7C2z4GqqS5E',
      bpm: 78,
      audioQuality: 'Dolby Atmos Spatial Audio',
      lyrics: [
        { time: 0, member: 'V', hangul: '널 위해서라면 난 슬퍼도 기쁜 척 할 수가 있었어', rom: 'Neol wihaeseoramyeon nan seulpeodo gippeun cheok hal suga isseosseo', eng: 'For you, I could pretend to be happy even when I was sad' },
        { time: 16, member: 'Jung Kook', hangul: '널 위해서라면 난 아파도 강한 척 할 수가 있었어', rom: 'Neol wihaeseoramyeon nan apado ganghan cheok hal suga isseosseo', eng: 'For you, I could pretend to be strong even when I was hurting' }
      ]
    },
    {
      id: 'mic-drop',
      title: 'MIC Drop (Steve Aoki Remix)',
      artist: 'BTS',
      album: 'Love Yourself 承 \'Her\'',
      year: '2017',
      era: 'loveyourself',
      member: 'all',
      duration: 239,
      durationText: '3:59',
      streams: '1.5B',
      cover: COVERS.her,
      audioSrc: null,
      youtubeId: 'kTlv5_Bs8aw',
      ytMusicUrl: 'https://music.youtube.com/watch?v=kTlv5_Bs8aw',
      bpm: 170,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'j-hope', hangul: 'Yeah, 누가 내 수저 더럽대? I don\'t care, 마이크 잡음 금수저 여럿 패', rom: 'Yeah, nuga nae sujeo deoreopdae?', eng: 'Yeah, who says my spoon is dirty? I grab the mic and beat several golden spoons' },
        { time: 18, member: 'SUGA', hangul: '너무 바빠 너무 바빠, 이미 트로피가 너무 많아', rom: 'Neomu bappa neomu bappa, imi teuropiga neomu mana', eng: 'Too busy, too busy, trophies are already overflowing' }
      ]
    },
    {
      id: 'idol',
      title: 'IDOL',
      artist: 'BTS',
      album: 'Love Yourself 結 \'Answer\'',
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 223,
      durationText: '3:43',
      streams: '1.3B',
      cover: COVERS.answer,
      audioSrc: null,
      youtubeId: 'pBuZEGYXA6E',
      ytMusicUrl: 'https://music.youtube.com/watch?v=pBuZEGYXA6E',
      bpm: 126,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'RM', hangul: 'You can call me artist, you can call me idol', rom: 'You can call me artist, you can call me idol', eng: 'You can call me artist, you can call me idol' },
        { time: 14, member: 'All', hangul: '얼쑤 좋다! 지화자 좋다! 덩기덕 쿵더러러!', rom: 'Eolssu jota! Jihwaja jota!', eng: 'Eolssu! Celebrate! Feel the traditional Korean beat!' }
      ]
    },
    {
      id: 'life-goes-on',
      title: 'Life Goes On',
      artist: 'BTS',
      album: 'BE',
      year: '2020',
      era: 'be_butter',
      member: 'all',
      duration: 207,
      durationText: '3:27',
      streams: '790M',
      cover: COVERS.be,
      audioSrc: null,
      youtubeId: '-5q5mZbe3V8',
      ytMusicUrl: 'https://music.youtube.com/watch?v=-5q5mZbe3V8',
      bpm: 81,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '어느 날 세상이 멈췄어, 아무런 예고도 하나 없이', rom: 'Eoneu nal sesangi meomchwosseo', eng: 'One day the world stopped, without any forewarning' },
        { time: 18, member: 'Jimin', hangul: 'Like an echo in the forest, 하루가 돌아오겠지', rom: 'Like an echo in the forest', eng: 'Like an echo in the forest, the day will return again' }
      ]
    },
    {
      id: 'run-bts',
      title: 'Run BTS (달려라 방탄)',
      artist: 'BTS',
      album: 'Proof',
      year: '2022',
      era: 'proof',
      member: 'all',
      duration: 205,
      durationText: '3:25',
      streams: '650M',
      cover: COVERS.proof,
      audioSrc: null,
      youtubeId: 'sAWuYx_UswY',
      ytMusicUrl: 'https://music.youtube.com/watch?v=sAWuYx_UswY',
      bpm: 146,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '논현동 백 네 번째, 계단 밑 방', rom: 'Nonhyeondong baek ne beonjjae', eng: 'Nonhyeon-dong 104th, that tiny room beneath the staircase' },
        { time: 18, member: 'SUGA', hangul: '그래 우린 달렸지, 맨발로 시작했던 랩', rom: 'Geurae urin dallyeotji', eng: 'Yeah we ran, starting with bare feet and raw rap' }
      ]
    },

    // -------------------------------------------------------------------------
    // 2. SOLO MEMBER MASTERPIECES (RM, JIN, SUGA, J-HOPE, JIMIN, V, JUNG KOOK)
    // -------------------------------------------------------------------------
    // JUNG KOOK
    {
      id: 'seven',
      title: 'Seven (feat. Latto)',
      artist: 'Jung Kook',
      album: 'GOLDEN',
      year: '2023',
      era: 'solo',
      member: 'jungkook',
      duration: 184,
      durationText: '3:04',
      streams: '2.1B',
      cover: COVERS.golden,
      audioSrc: null,
      youtubeId: 'QU9c0053UAU',
      ytMusicUrl: 'https://music.youtube.com/watch?v=QU9c0053UAU',
      bpm: 125,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: 'Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday, seven days a week', rom: 'Seven days a week', eng: 'Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday, every hour, every minute, every second' }
      ]
    },
    {
      id: '3d',
      title: '3D (feat. Jack Harlow)',
      artist: 'Jung Kook',
      album: 'GOLDEN',
      year: '2023',
      era: 'solo',
      member: 'jungkook',
      duration: 201,
      durationText: '3:21',
      streams: '780M',
      cover: COVERS.golden,
      audioSrc: null,
      youtubeId: 'mHNCM-YALSA',
      ytMusicUrl: 'https://music.youtube.com/watch?v=mHNCM-YALSA',
      bpm: 108,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '1, 2, 3D, I just wanna see you like that', rom: 'I just wanna see you like that', eng: 'I just wanna see you like that, moving in 3D' }
      ]
    },
    {
      id: 'euphoria',
      title: 'Euphoria',
      artist: 'Jung Kook',
      album: 'Love Yourself 結 \'Answer\'',
      year: '2018',
      era: 'loveyourself',
      member: 'jungkook',
      duration: 229,
      durationText: '3:49',
      streams: '650M',
      cover: COVERS.answer,
      audioSrc: null,
      youtubeId: 'kX0vO4zJu1k',
      ytMusicUrl: 'https://music.youtube.com/watch?v=kX0vO4zJu1k',
      bpm: 128,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '너는 내 삶에 다시 뜬 햇살', rom: 'Neoneun nae salme dasi tteun haetsal', eng: 'You are the sunlight that rose again in my life' },
        { time: 18, member: 'Jung Kook', hangul: 'Take my hands now, you are the cause of my euphoria', rom: 'Take my hands now', eng: 'Take my hands now, you are the cause of my euphoria' }
      ]
    },
    {
      id: 'still-with-you',
      title: 'Still With You',
      artist: 'Jung Kook',
      album: 'FESTA Special Single',
      year: '2020',
      era: 'solo',
      member: 'jungkook',
      duration: 239,
      durationText: '3:59',
      streams: '320M',
      cover: COVERS.golden,
      audioSrc: null,
      youtubeId: 'BksNEvACkWQ',
      ytMusicUrl: 'https://music.youtube.com/watch?v=BksNEvACkWQ',
      bpm: 110,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '날 스치는 그대의 옅은 그 목소리', rom: 'Nal seuchineun geudaeui yeoteun geu moksori', eng: 'Your faint voice brushing past me' }
      ]
    },

    // JIMIN
    {
      id: 'like-crazy',
      title: 'Like Crazy',
      artist: 'Jimin',
      album: 'FACE',
      year: '2023',
      era: 'solo',
      member: 'jimin',
      duration: 212,
      durationText: '3:32',
      streams: '1.4B',
      cover: COVERS.face,
      audioSrc: null,
      youtubeId: 'y3uh5vOxV_E',
      ytMusicUrl: 'https://music.youtube.com/watch?v=y3uh5vOxV_E',
      bpm: 120,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jimin', hangul: 'She\'s saying, "Baby, come and tell me what you want"', rom: 'Baby come and tell me', eng: 'She\'s saying baby come and tell me what you want, yeah' },
        { time: 18, member: 'Jimin', hangul: 'I\'d rather be lost in the lights, lost in the lights', rom: 'Lost in the lights', eng: 'I\'d rather be lost in the lights, I\'m outta my mind' }
      ]
    },
    {
      id: 'who',
      title: 'Who',
      artist: 'Jimin',
      album: 'MUSE',
      year: '2024',
      era: 'solo',
      member: 'jimin',
      duration: 171,
      durationText: '2:51',
      streams: '1.1B',
      cover: COVERS.face,
      audioSrc: null,
      youtubeId: 'zSQ48zyWZrY',
      ytMusicUrl: 'https://music.youtube.com/watch?v=zSQ48zyWZrY',
      bpm: 116,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jimin', hangul: 'We never met, but she\'s all I see at night', rom: 'She\'s all I see at night', eng: 'We never met, but she\'s all I see at night' },
        { time: 14, member: 'Jimin', hangul: 'Who is my heart waiting for?', rom: 'Who is my heart waiting for?', eng: 'Who is my heart waiting for?' }
      ]
    },
    {
      id: 'set-me-free-pt2',
      title: 'Set Me Free Pt.2',
      artist: 'Jimin',
      album: 'FACE',
      year: '2023',
      era: 'solo',
      member: 'jimin',
      duration: 215,
      durationText: '3:35',
      streams: '450M',
      cover: COVERS.face,
      audioSrc: null,
      youtubeId: 'UtsI_Skx2Q8',
      ytMusicUrl: 'https://music.youtube.com/watch?v=UtsI_Skx2Q8',
      bpm: 132,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jimin', hangul: 'I got a good time, yeah time to let me free', rom: 'Time to let me free', eng: 'Set me free, look at me fly away!' }
      ]
    },
    {
      id: 'filter',
      title: 'Filter',
      artist: 'Jimin',
      album: 'Map of the Soul: 7',
      year: '2020',
      era: 'mots',
      member: 'jimin',
      duration: 180,
      durationText: '3:00',
      streams: '420M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: 'bnqLzCSQnzs',
      ytMusicUrl: 'https://music.youtube.com/watch?v=bnqLzCSQnzs',
      bpm: 110,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jimin', hangul: '너의 그 무표정한 얼굴 뒤에, 내 색을 칠해볼게', rom: 'Neoui geu mupyojeonghan eolgul dwie', eng: 'Behind your expressionless face, I will paint my colors' }
      ]
    },
    {
      id: 'serendipity',
      title: 'Intro: Serendipity',
      artist: 'Jimin',
      album: 'Love Yourself 承 \'Her\'',
      year: '2017',
      era: 'loveyourself',
      member: 'jimin',
      duration: 276,
      durationText: '4:36',
      streams: '380M',
      cover: COVERS.her,
      audioSrc: null,
      youtubeId: 'BEMaH9Sm3HU',
      ytMusicUrl: 'https://music.youtube.com/watch?v=BEMaH9Sm3HU',
      bpm: 87,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jimin', hangul: '이 모든 건 우연이 아냐, 그냥 그냥 나의 느낌으로', rom: 'I modeun geon uyeoni anya', eng: 'None of this is a coincidence, just my own feeling' },
        { time: 18, member: 'Jimin', hangul: 'You know, I know, 너는 나, 나는 너', rom: 'Neoneun na, naneun neo', eng: 'You know, I know, you are me, and I am you' }
      ]
    },

    // V (KIM TAEHYUNG)
    {
      id: 'slow-dancing',
      title: 'Slow Dancing',
      artist: 'V',
      album: 'Layover',
      year: '2023',
      era: 'solo',
      member: 'v',
      duration: 188,
      durationText: '3:08',
      streams: '560M',
      cover: COVERS.layover,
      audioSrc: null,
      youtubeId: 'eI0iTRS0Ha8',
      ytMusicUrl: 'https://music.youtube.com/watch?v=eI0iTRS0Ha8',
      bpm: 96,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'V', hangul: 'It\'s about time we get it straight, gimme a minute if it ain\'t too late', rom: 'Gimme a minute', eng: 'It\'s about time we get it straight, maybe we could be slow dancing until the morning' }
      ]
    },
    {
      id: 'love-me-again',
      title: 'Love Me Again',
      artist: 'V',
      album: 'Layover',
      year: '2023',
      era: 'solo',
      member: 'v',
      duration: 198,
      durationText: '3:18',
      streams: '680M',
      cover: COVERS.layover,
      audioSrc: null,
      youtubeId: 'HYzyRHAHJl8',
      ytMusicUrl: 'https://music.youtube.com/watch?v=HYzyRHAHJl8',
      bpm: 84,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'V', hangul: '넌 날 잊고 사는지, 난 널 위해 아직 여기 남아', rom: 'Neon nal itgo saneunji', eng: 'Are you living forgetting me? I am still here waiting for you' }
      ]
    },
    {
      id: 'friends-v',
      title: 'FRI(END)S',
      artist: 'V',
      album: 'Single',
      year: '2024',
      era: 'solo',
      member: 'v',
      duration: 148,
      durationText: '2:28',
      streams: '380M',
      cover: COVERS.layover,
      audioSrc: null,
      youtubeId: '62pe9t325QY',
      ytMusicUrl: 'https://music.youtube.com/watch?v=62pe9t325QY',
      bpm: 104,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'V', hangul: 'Now I\'m in the friendzone, but I wanna be more than friends', rom: 'More than friends', eng: 'Let\'s put the "end" in friends, you and I together' }
      ]
    },
    {
      id: 'singularity',
      title: 'Intro: Singularity',
      artist: 'V',
      album: 'Love Yourself 轉 \'Tear\'',
      year: '2018',
      era: 'loveyourself',
      member: 'v',
      duration: 197,
      durationText: '3:17',
      streams: '310M',
      cover: COVERS.tear,
      audioSrc: null,
      youtubeId: 'p8npDG2vn30',
      ytMusicUrl: 'https://music.youtube.com/watch?v=p8npDG2vn30',
      bpm: 80,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'V', hangul: '무언가 깨지는 소리, 난 문득 잠에서 깨', rom: 'Mueonga kkaejineun sori', eng: 'A sound of something breaking, I suddenly awaken from my sleep' }
      ]
    },
    {
      id: 'sweet-night',
      title: 'Sweet Night',
      artist: 'V',
      album: 'Itaewon Class OST',
      year: '2020',
      era: 'solo',
      member: 'v',
      duration: 214,
      durationText: '3:34',
      streams: '350M',
      cover: COVERS.layover,
      audioSrc: null,
      youtubeId: 'N5ShoQim85o',
      ytMusicUrl: 'https://music.youtube.com/watch?v=N5ShoQim85o',
      bpm: 82,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'V', hangul: 'How could I know one day I\'d wake up feeling more?', rom: 'Feeling more', eng: 'On my pillow, can I tell you how much I care?' }
      ]
    },

    // SUGA / AGUST D
    {
      id: 'haegeum',
      title: 'Haegeum (해금)',
      artist: 'Agust D (SUGA)',
      album: 'D-DAY',
      year: '2023',
      era: 'solo',
      member: 'suga',
      duration: 168,
      durationText: '2:48',
      streams: '380M',
      cover: COVERS.dday,
      audioSrc: null,
      youtubeId: 'iy9qZr_OG4A',
      ytMusicUrl: 'https://music.youtube.com/watch?v=iy9qZr_OG4A',
      bpm: 150,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'SUGA', hangul: '이 노래는 해금, 금지된 것을 풀어헤쳐라', rom: 'I noraeneun haegeum', eng: 'This song is Haegeum, unban that which has been restricted!' }
      ]
    },
    {
      id: 'daechwita',
      title: 'Daechwita (대취타)',
      artist: 'Agust D (SUGA)',
      album: 'D-2',
      year: '2020',
      era: 'solo',
      member: 'suga',
      duration: 226,
      durationText: '3:46',
      streams: '490M',
      cover: COVERS.dday,
      audioSrc: null,
      youtubeId: 'qGjAWJ2zWWI',
      ytMusicUrl: 'https://music.youtube.com/watch?v=qGjAWJ2zWWI',
      bpm: 140,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'SUGA', hangul: '대취타 대취타 자 울려라 대취타!', rom: 'Daechwita Daechwita', eng: 'Daechwita, Daechwita, play the royal procession music loudly!' }
      ]
    },
    {
      id: 'people-pt2',
      title: 'People Pt.2 (feat. IU)',
      artist: 'Agust D (SUGA)',
      album: 'D-DAY',
      year: '2023',
      era: 'solo',
      member: 'suga',
      duration: 213,
      durationText: '3:33',
      streams: '310M',
      cover: COVERS.dday,
      audioSrc: null,
      youtubeId: 'uTsUJ8zF5wM',
      ytMusicUrl: 'https://music.youtube.com/watch?v=uTsUJ8zF5wM',
      bpm: 90,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'SUGA', hangul: '사랑이라는 말, 어쩌면 순간의 감정의 나열', rom: 'Sarang-iraneun mal', eng: 'The word love, perhaps just an alignment of fleeting emotions' }
      ]
    },
    {
      id: 'seesaw',
      title: 'Trivia 轉: Seesaw',
      artist: 'SUGA',
      album: 'Love Yourself 結 \'Answer\'',
      year: '2018',
      era: 'loveyourself',
      member: 'suga',
      duration: 246,
      durationText: '4:06',
      streams: '340M',
      cover: COVERS.answer,
      audioSrc: null,
      youtubeId: '_3eSjH30nEw',
      ytMusicUrl: 'https://music.youtube.com/watch?v=_3eSjH30nEw',
      bpm: 104,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'SUGA', hangul: '시작은 뭐 즐거웠었네, 오르락내리락 그 자체로', rom: 'Sijageun mwo jeulgeowosseonne', eng: 'The beginning was pleasant, just going up and down on the seesaw' }
      ]
    },

    // J-HOPE
    {
      id: 'more',
      title: 'MORE',
      artist: 'j-hope',
      album: 'Jack In The Box',
      year: '2022',
      era: 'solo',
      member: 'jhope',
      duration: 180,
      durationText: '3:00',
      streams: '260M',
      cover: COVERS.jitb,
      audioSrc: null,
      youtubeId: 'pKdBFeewZp0',
      ytMusicUrl: 'https://music.youtube.com/watch?v=pKdBFeewZp0',
      bpm: 85,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'j-hope', hangul: 'Yeah, I\'m thirsty, want it more, bring it on!', rom: 'I\'m thirsty', eng: 'Yeah I want it more, shouting into the box!' }
      ]
    },
    {
      id: 'arson',
      title: 'Arson (방화)',
      artist: 'j-hope',
      album: 'Jack In The Box',
      year: '2022',
      era: 'solo',
      member: 'jhope',
      duration: 159,
      durationText: '2:39',
      streams: '220M',
      cover: COVERS.jitb,
      audioSrc: null,
      youtubeId: 'QmpIE_n_2r4',
      ytMusicUrl: 'https://music.youtube.com/watch?v=QmpIE_n_2r4',
      bpm: 140,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'j-hope', hangul: 'Let\'s burn, burn, burn, it\'s done!', rom: 'Let\'s burn', eng: 'Let\'s burn, my passion is arson!' }
      ]
    },
    {
      id: 'on-the-street',
      title: 'on the street (with J. Cole)',
      artist: 'j-hope',
      album: 'Single',
      year: '2023',
      era: 'solo',
      member: 'jhope',
      duration: 215,
      durationText: '3:35',
      streams: '290M',
      cover: COVERS.jitb,
      audioSrc: null,
      youtubeId: 'r6WbbU_lLCA',
      ytMusicUrl: 'https://music.youtube.com/watch?v=r6WbbU_lLCA',
      bpm: 94,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'j-hope', hangul: 'Every time I walk, every time I run, on the street with love', rom: 'Every time I walk', eng: 'Every time I walk, every time I run, on the street with love' }
      ]
    },
    {
      id: 'chicken-noodle-soup',
      title: 'Chicken Noodle Soup (feat. Becky G)',
      artist: 'j-hope',
      album: 'Single',
      year: '2019',
      era: 'solo',
      member: 'jhope',
      duration: 194,
      durationText: '3:14',
      streams: '390M',
      cover: COVERS.jitb,
      audioSrc: null,
      youtubeId: 'i23NEQEFpgQ',
      ytMusicUrl: 'https://music.youtube.com/watch?v=i23NEQEFpgQ',
      bpm: 102,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'j-hope', hangul: 'Chicken noodle soup with a soda on the side!', rom: 'Chicken noodle soup', eng: 'Chicken noodle soup with a soda on the side!' }
      ]
    },

    // RM (KIM NAMJOON)
    {
      id: 'wild-flower',
      title: 'Wild Flower (들꽃놀이 with youjeen)',
      artist: 'RM',
      album: 'Indigo',
      year: '2022',
      era: 'solo',
      member: 'rm',
      duration: 273,
      durationText: '4:33',
      streams: '310M',
      cover: COVERS.indigo,
      audioSrc: null,
      youtubeId: '2v8UCpe7v_o',
      ytMusicUrl: 'https://music.youtube.com/watch?v=2v8UCpe7v_o',
      bpm: 85,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'RM', hangul: '하얗게 타버린 불꽃놀이보단, 고요한 들꽃으로 남고 싶어', rom: 'Goyohan deulkkocheuro namgo sipeo', eng: 'Rather than a blazing firework that vanishes, I want to remain a tranquil wildflower' }
      ]
    },
    {
      id: 'still-life',
      title: 'Still Life (with Anderson .Paak)',
      artist: 'RM',
      album: 'Indigo',
      year: '2022',
      era: 'solo',
      member: 'rm',
      duration: 175,
      durationText: '2:55',
      streams: '190M',
      cover: COVERS.indigo,
      audioSrc: null,
      youtubeId: 'G8O13t4j9e8',
      ytMusicUrl: 'https://music.youtube.com/watch?v=G8O13t4j9e8',
      bpm: 105,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'RM', hangul: 'I\'m still life, but I\'m movin\' forward', rom: 'Still life', eng: 'I\'m still life, you can\'t lock me in the canvas frame' }
      ]
    },
    {
      id: 'come-back-to-me',
      title: 'Come back to me',
      artist: 'RM',
      album: 'Right Place, Wrong Person',
      year: '2024',
      era: 'solo',
      member: 'rm',
      duration: 388,
      durationText: '6:28',
      streams: '150M',
      cover: COVERS.indigo,
      audioSrc: null,
      youtubeId: 'NBM_2b4D2_A',
      ytMusicUrl: 'https://music.youtube.com/watch?v=NBM_2b4D2_A',
      bpm: 112,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'RM', hangul: 'You are my pain, divine divine', rom: 'You are my pain', eng: 'Spring, summer, fall and winter, come back to me' }
      ]
    },

    // JIN (KIM SEOKJIN)
    {
      id: 'the-astronaut',
      title: 'The Astronaut',
      artist: 'Jin',
      album: 'Single (with Coldplay)',
      year: '2022',
      era: 'solo',
      member: 'jin',
      duration: 282,
      durationText: '4:42',
      streams: '490M',
      cover: COVERS.astronaut,
      audioSrc: null,
      youtubeId: 'c6ASQOwKkhk',
      ytMusicUrl: 'https://music.youtube.com/watch?v=c6ASQOwKkhk',
      bpm: 125,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jin', hangul: '우주를 건너 너에게로 가는 길', rom: 'Ujureul geonneo', eng: 'Crossing the universe on my way back to you, like an astronaut' },
        { time: 24, member: 'Jin', hangul: 'When I was with you, there was no one else', rom: 'There was no one else', eng: 'And I love you so' }
      ]
    },
    {
      id: 'super-tuna',
      title: 'Super Tuna (슈퍼 참치)',
      artist: 'Jin',
      album: 'Special Single',
      year: '2021',
      era: 'solo',
      member: 'jin',
      duration: 104,
      durationText: '1:44',
      streams: '160M',
      cover: COVERS.astronaut,
      audioSrc: null,
      youtubeId: 'c4PTk_f6V6Y',
      ytMusicUrl: 'https://music.youtube.com/watch?v=c4PTk_f6V6Y',
      bpm: 140,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jin', hangul: '참치! 하! 슈퍼 참치!', rom: 'Chamchi! Ha! Super Chamchi!', eng: 'Tuna! Ha! Super Tuna! If you bite my fishing hook, please be a tuna!' }
      ]
    },
    {
      id: 'running-wild',
      title: 'Running Wild',
      artist: 'Jin',
      album: 'Happy',
      year: '2024',
      era: 'solo',
      member: 'jin',
      duration: 151,
      durationText: '2:31',
      streams: '210M',
      cover: COVERS.astronaut,
      audioSrc: null,
      youtubeId: 'vK2rF796GZs',
      ytMusicUrl: 'https://music.youtube.com/watch?v=vK2rF796GZs',
      bpm: 124,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jin', hangul: 'We\'ll be running wild, loving till the end of time', rom: 'Running wild', eng: 'We\'ll be running wild, loving till the end of time' }
      ]
    },
    {
      id: 'epiphany',
      title: 'Epiphany',
      artist: 'Jin',
      album: 'Love Yourself 結 \'Answer\'',
      year: '2018',
      era: 'loveyourself',
      member: 'jin',
      duration: 240,
      durationText: '4:00',
      streams: '380M',
      cover: COVERS.answer,
      audioSrc: null,
      youtubeId: 'fIkZOLsnoqY',
      ytMusicUrl: 'https://music.youtube.com/watch?v=fIkZOLsnoqY',
      bpm: 68,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jin', hangul: 'I\'m the one I should love in this world', rom: 'I\'m the one I should love', eng: 'I\'m the one I should love in this world, shining me, precious soul of mine' }
      ]
    },

    // -------------------------------------------------------------------------
    // 3. ICONIC ERA ANTHEMS (HYYH, WINGS, LOVE YOURSELF, MOTS, BE, DEBUT)
    // -------------------------------------------------------------------------
    {
      id: 'i-need-u',
      title: 'I NEED U',
      artist: 'BTS',
      album: 'The Most Beautiful Moment in Life, Pt. 1',
      year: '2015',
      era: 'hyyh',
      member: 'all',
      duration: 211,
      durationText: '3:31',
      streams: '650M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: 'NMdX1qKgTDg',
      ytMusicUrl: 'https://music.youtube.com/watch?v=NMdX1qKgTDg',
      bpm: 158,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'All', hangul: 'I need u girl, 왜 혼자 사랑하고 혼자서만 이별해', rom: 'I need u girl', eng: 'I need you girl, why am I in love alone and saying goodbye alone?' }
      ]
    },
    {
      id: 'dope',
      title: 'Dope (쩔어)',
      artist: 'BTS',
      album: 'The Most Beautiful Moment in Life, Pt. 1',
      year: '2015',
      era: 'hyyh',
      member: 'all',
      duration: 240,
      durationText: '4:00',
      streams: '790M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: 'BVwAVbKYYeM',
      ytMusicUrl: 'https://music.youtube.com/watch?v=BVwAVbKYYeM',
      bpm: 163,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'RM', hangul: '어서 와, 방탄은 처음이지?', rom: 'Eoseo wa, bangtaneun cheoeumiji?', eng: 'Welcome, first time with BTS?' }
      ]
    },
    {
      id: 'fire',
      title: 'FIRE (불타오르네)',
      artist: 'BTS',
      album: 'The Most Beautiful Moment in Life: Young Forever',
      year: '2016',
      era: 'hyyh',
      member: 'all',
      duration: 203,
      durationText: '3:23',
      streams: '820M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: '4ujQOR2DMFM',
      ytMusicUrl: 'https://music.youtube.com/watch?v=4ujQOR2DMFM',
      bpm: 100,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'SUGA', hangul: '불타오르네. 싹 다 불태워라 Bow wow wow!', rom: 'Bultaoreune', eng: 'It\'s burning up! Burn it all away, bow wow wow!' }
      ]
    },
    {
      id: 'save-me',
      title: 'Save ME',
      artist: 'BTS',
      album: 'The Most Beautiful Moment in Life: Young Forever',
      year: '2016',
      era: 'hyyh',
      member: 'all',
      duration: 196,
      durationText: '3:16',
      streams: '710M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: 'GZjt_sA2eso',
      ytMusicUrl: 'https://music.youtube.com/watch?v=GZjt_sA2eso',
      bpm: 140,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jimin / V', hangul: '난 숨쉬고 싶어, 이 밤이 싫어, Save me', rom: 'Nan sumswigo sipeo, save me', eng: 'I want to breathe, I hate this night, save me tonight' }
      ]
    },
    {
      id: 'run',
      title: 'RUN',
      artist: 'BTS',
      album: 'The Most Beautiful Moment in Life, Pt. 2',
      year: '2015',
      era: 'hyyh',
      member: 'all',
      duration: 236,
      durationText: '3:56',
      streams: '420M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: '5Wn85Ge22FQ',
      ytMusicUrl: 'https://music.youtube.com/watch?v=5Wn85Ge22FQ',
      bpm: 130,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '다시 Run, run, run, 넘어져도 괜찮아', rom: 'Dasi run run run', eng: 'Run run run again, it\'s okay even if we fall down' }
      ]
    },
    {
      id: 'butterfly',
      title: 'Butterfly',
      artist: 'BTS',
      album: 'The Most Beautiful Moment in Life, Pt. 2',
      year: '2015',
      era: 'hyyh',
      member: 'all',
      duration: 239,
      durationText: '3:59',
      streams: '320M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: 'T7B9E_Vq9s8',
      ytMusicUrl: 'https://music.youtube.com/watch?v=T7B9E_Vq9s8',
      bpm: 86,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '아무것도 생각하지 마, 넌 아무 말도 꺼내지도 마', rom: 'Amugeotdo saenggakhaji ma', eng: 'Don\'t think about anything, don\'t even say a word' },
        { time: 18, member: 'Jimin', hangul: 'Like a butterfly, 곁에 머물러줄래', rom: 'Like a butterfly', eng: 'Like a butterfly, will you stay by my side?' }
      ]
    },
    {
      id: 'not-today',
      title: 'Not Today',
      artist: 'BTS',
      album: 'You Never Walk Alone',
      year: '2017',
      era: 'wings',
      member: 'all',
      duration: 231,
      durationText: '3:51',
      streams: '590M',
      cover: COVERS.ynwa,
      audioSrc: null,
      youtubeId: '9DwzBICPhdM',
      ytMusicUrl: 'https://music.youtube.com/watch?v=9DwzBICPhdM',
      bpm: 110,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'RM', hangul: 'All the underdogs in the world, a day may come when we lose, but it is not today!', rom: 'Not today', eng: 'Today we fight! No, no, not today!' }
      ]
    },
    {
      id: 'on',
      title: 'ON',
      artist: 'BTS',
      album: 'Map of the Soul: 7',
      year: '2020',
      era: 'mots',
      member: 'all',
      duration: 246,
      durationText: '4:06',
      streams: '680M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: 'mPVDGOVjRQ0',
      ytMusicUrl: 'https://music.youtube.com/watch?v=mPVDGOVjRQ0',
      bpm: 106,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'RM', hangul: 'I can\'t understand what people are sayin\', 어느 장단에 맞춰야 될지', rom: 'I can\'t understand', eng: 'Bring the pain on, yeah!' }
      ]
    },
    {
      id: 'mikrokosmos',
      title: 'Mikrokosmos (소우주)',
      artist: 'BTS',
      album: 'Map of the Soul: Persona',
      year: '2019',
      era: 'mots',
      member: 'all',
      duration: 224,
      durationText: '3:44',
      streams: '520M',
      cover: COVERS.persona,
      audioSrc: 'audio/mikrokosmos.m4a',
      audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview211/v4/f4/73/db/f473db8a-b8d9-622b-7828-e40f63af00f7/mzaf_8890385023586453692.plus.aac.p.m4a',
      youtubeId: 'P8Z6V_26oQY',
      ytMusicUrl: 'https://music.youtube.com/watch?v=P8Z6V_26oQY',
      bpm: 87,
      audioQuality: 'Stadium Dolby Atmos Live Master',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '반짝이는 별빛들, 깜빡이는 불 켜진 건물', rom: 'Banjjagineun byeolbitdeul', eng: 'Twinkling starlights, blinking illuminated buildings' },
        { time: 18, member: 'Jimin', hangul: '우린 빛나고 있네 각자의 방 각자의 별에서', rom: 'Urin binnago inne', eng: 'We are shining, in each of our rooms, in each of our stars' },
        { time: 36, member: 'All', hangul: 'You got me, 난 너를 보며 꿈을 꿔, I got you', rom: 'You got me, I got you', eng: 'You got me, looking at you I dream, I got you in the deep pitch-black nights' }
      ]
    },
    {
      id: 'make-it-right',
      title: 'Make It Right (feat. Lauv)',
      artist: 'BTS',
      album: 'Map of the Soul: Persona',
      year: '2019',
      era: 'mots',
      member: 'all',
      duration: 226,
      durationText: '3:46',
      streams: '450M',
      cover: COVERS.persona,
      audioSrc: null,
      youtubeId: 'e_k0vBqVl3g',
      ytMusicUrl: 'https://music.youtube.com/watch?v=e_k0vBqVl3g',
      bpm: 98,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '내가 날 온전히 알지 못할 때, 넌 날 알아봐줬어', rom: 'Nega nal onjeonhi alji motal ttae', eng: 'When I didn\'t fully know myself, you recognized me' },
        { time: 18, member: 'Jimin', hangul: 'I could make it better, I could hold you tighter', rom: 'I could make it better', eng: 'I could make it better, I could hold you tighter on that rough road' }
      ]
    },
    {
      id: 'permission-to-dance',
      title: 'Permission to Dance',
      artist: 'BTS',
      album: 'Butter Single',
      year: '2021',
      era: 'be_butter',
      member: 'all',
      duration: 187,
      durationText: '3:07',
      streams: '720M',
      cover: COVERS.butter,
      audioSrc: null,
      youtubeId: 'CuklUbVdkev',
      ytMusicUrl: 'https://music.youtube.com/watch?v=CuklUbVdkev',
      bpm: 125,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: 'It\'s the thought of being young, when your heart\'s just like a drum', rom: 'Heart\'s just like a drum', eng: 'We don\'t need to worry, \'cause when we fall we know how to land' },
        { time: 20, member: 'Jimin', hangul: 'Don\'t need to talk the talk, just walk the walk tonight', rom: 'Walk the walk tonight', eng: '\'Cause we don\'t need permission to dance!' }
      ]
    },
    {
      id: 'my-universe',
      title: 'My Universe (Coldplay X BTS)',
      artist: 'Coldplay & BTS',
      album: 'Music of the Spheres',
      year: '2021',
      era: 'be_butter',
      member: 'all',
      duration: 228,
      durationText: '3:48',
      streams: '1.2B',
      cover: COVERS.butter,
      audioSrc: null,
      youtubeId: '3YqPKLZF_WU',
      ytMusicUrl: 'https://music.youtube.com/watch?v=3YqPKLZF_WU',
      bpm: 105,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Chris Martin / Jung Kook', hangul: 'You, you are my universe and I just want to put you first', rom: 'You are my universe', eng: 'You, you are my universe and you make my world light up inside' }
      ]
    },
    {
      id: 'take-two',
      title: 'Take Two',
      artist: 'BTS',
      album: '10th Anniversary Festa Single',
      year: '2023',
      era: 'proof',
      member: 'all',
      duration: 230,
      durationText: '3:50',
      streams: '320M',
      cover: COVERS.proof,
      audioSrc: null,
      youtubeId: 'PkWuK3f4J58',
      ytMusicUrl: 'https://music.youtube.com/watch?v=PkWuK3f4J58',
      bpm: 88,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '당신과 함께한 지난 10년의 여정, 감사해요', rom: 'Dangsindwa hamkkehan', eng: 'Will you stay? Will you walk with me for the next chapter?' }
      ]
    },
    {
      id: 'the-truth-untold',
      title: 'The Truth Untold (전하지 못한 진심 feat. Steve Aoki)',
      artist: 'BTS (Vocal Line)',
      album: 'Love Yourself 轉 \'Tear\'',
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 242,
      durationText: '4:02',
      streams: '480M',
      cover: COVERS.tear,
      audioSrc: null,
      youtubeId: '8-M5hT10WbE',
      ytMusicUrl: 'https://music.youtube.com/watch?v=8-M5hT10WbE',
      bpm: 60,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'V', hangul: '외로움의 정원에 핀 너를 닮은 꽃, 주고 싶었지', rom: 'Oeroumui jeong-wone pin', eng: 'In the garden of loneliness, a flower resembling you blossomed' },
        { time: 20, member: 'Jung Kook', hangul: 'But I still want you', rom: 'But I still want you', eng: 'And I still want you' }
      ]
    },
    {
      id: 'pied-piper',
      title: 'Pied Piper',
      artist: 'BTS',
      album: 'Love Yourself 承 \'Her\'',
      year: '2017',
      era: 'loveyourself',
      member: 'all',
      duration: 245,
      durationText: '4:05',
      streams: '450M',
      cover: COVERS.her,
      audioSrc: null,
      youtubeId: 'a9N0z54t8k8',
      ytMusicUrl: 'https://music.youtube.com/watch?v=a9N0z54t8k8',
      bpm: 98,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '나쁜 거라 더 좋은 거야, 속으론 알고 있잖아', rom: 'Nappeun geora deo joeun geoya', eng: 'It\'s better because it\'s bad, you know it in your heart' },
        { time: 18, member: 'RM', hangul: '이제 멈추고 시험공부 해, 나중에 브이앱 봐도 돼', rom: 'Ije meomchugo siheomgongbu hae', eng: 'Now stop and study for your exams, you can watch our videos later' }
      ]
    },
    {
      id: 'anpanman',
      title: 'Anpanman',
      artist: 'BTS',
      album: 'Love Yourself 轉 \'Tear\'',
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 233,
      durationText: '3:53',
      streams: '420M',
      cover: COVERS.tear,
      audioSrc: null,
      youtubeId: 'q7wL5M2vB8k',
      ytMusicUrl: 'https://music.youtube.com/watch?v=q7wL5M2vB8k',
      bpm: 166,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'j-hope', hangul: 'Waiting for you, Anpanman!', rom: 'Waiting for you, Anpanman', eng: 'Waiting for you, Anpanman! I don\'t have muscles or super cars, but I can give you my head to smile' }
      ]
    },
    {
      id: 'ddaeng',
      title: 'Ddaeng (땡)',
      artist: 'RM, SUGA, j-hope',
      album: 'FESTA Special SoundCloud Single',
      year: '2018',
      era: 'solo',
      member: 'suga',
      duration: 234,
      durationText: '3:54',
      streams: '280M',
      cover: COVERS.tear,
      audioSrc: null,
      youtubeId: 'M7xX0WzH2oA',
      ytMusicUrl: 'https://music.youtube.com/watch?v=M7xX0WzH2oA',
      bpm: 95,
      audioQuality: 'SoundCloud Master Edition',
      lyrics: [
        { time: 0, member: 'SUGA', hangul: '일팔 일삼 삼팔 땡! U wrong me right 잘 봐 땡!', rom: 'Ilpal ilsam sampal ddaeng', eng: '1-8, 1-3, 3-8, Ddaeng! You\'re wrong, I\'m right, look closely, Ddaeng!' }
      ]
    },
    {
      id: 'no-more-dream',
      title: 'No More Dream (Debut)',
      artist: 'BTS',
      album: '2 Cool 4 Skool',
      year: '2013',
      era: 'debut',
      member: 'all',
      duration: 222,
      durationText: '3:42',
      streams: '320M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: 'rBG5L755NxA',
      ytMusicUrl: 'https://music.youtube.com/watch?v=rBG5L755NxA',
      bpm: 84,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'RM', hangul: '얌마 니 꿈은 뭐니? 니 꿈은 겨우 그거니?', rom: 'Yamma ni kkumeun mwoni?', eng: 'Hey you, what is your dream? Is that all your dream is?' }
      ]
    },
    {
      id: 'boy-in-luv',
      title: 'Boy In Luv (상남자)',
      artist: 'BTS',
      album: 'Skool Luv Affair',
      year: '2014',
      era: 'debut',
      member: 'all',
      duration: 231,
      durationText: '3:51',
      streams: '420M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: 'm8MfJg68oCs',
      ytMusicUrl: 'https://music.youtube.com/watch?v=m8MfJg68oCs',
      bpm: 83,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'V', hangul: '되고파 너의 오빠! 너의 사랑이 난 너무 고파!', rom: 'Doegopa neoui oppa', eng: 'I wanna be your oppa! I am so hungry for your love!' }
      ]
    },
    {
      id: 'just-one-day',
      title: 'Just One Day (하루만)',
      artist: 'BTS',
      album: 'Skool Luv Affair',
      year: '2014',
      era: 'debut',
      member: 'all',
      duration: 240,
      durationText: '4:00',
      streams: '310M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: 'dzAwJW107nk',
      ytMusicUrl: 'https://music.youtube.com/watch?v=dzAwJW107nk',
      bpm: 96,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'SUGA', hangul: '하루만 내게 시간이 있다면, 편안히 너의 손을 잡고서', rom: 'Haruman naege sigani itdamyeon', eng: 'If I only had just one day, I\'d peacefully hold your hand' }
      ]
    },
    {
      id: 'danger',
      title: 'Danger',
      artist: 'BTS',
      album: 'Dark & Wild',
      year: '2014',
      era: 'debut',
      member: 'all',
      duration: 245,
      durationText: '4:05',
      streams: '290M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: '43r6lXilbcQ',
      ytMusicUrl: 'https://music.youtube.com/watch?v=43r6lXilbcQ',
      bpm: 112,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'Jung Kook', hangul: '너 지금 헷갈려 난 위험해, 장난해 너 도대체 내가 뭐야', rom: 'Neojigeum hetgallyeo', eng: 'You\'re confusing me right now, you\'re in danger, what am I to you?' }
      ]
    },
    {
      id: 'war-of-hormone',
      title: 'War of Hormone (호르몬 전쟁)',
      artist: 'BTS',
      album: 'Dark & Wild',
      year: '2014',
      era: 'debut',
      member: 'all',
      duration: 266,
      durationText: '4:26',
      streams: '340M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: 'XQmpCJNyY1A',
      ytMusicUrl: 'https://music.youtube.com/watch?v=XQmpCJNyY1A',
      bpm: 104,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        { time: 0, member: 'V / Jung Kook', hangul: '존재해줘서 참 감사해, 전화 좀 해줘 내가 밥 살게', rom: 'Jonjaehaejwoseo cham gamsahae', eng: 'Thank you so much for existing, please call me and I\'ll buy dinner!' }
      ]
    },
    {
      id: 'we-are-bulletproof-pt2',
      title: "We Are Bulletproof Pt.2",
      artist: "BTS",
      album: "2 Cool 4 Skool",
      year: '2013',
      era: 'debut',
      member: 'all',
      duration: 225,
      durationText: '3:45',
      streams: '260M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: 'bV9tGZomxX0',
      ytMusicUrl: 'https://music.youtube.com/watch?v=bV9tGZomxX0',
      bpm: 104,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jung Kook",
                "hangul": "이름은 Jung Kook, 스케일은 전국",
                "rom": "Ireumeun Jung Kook, seukeireun jeonguk",
                "eng": "My name is Jung Kook, my scale is nationwide"
        },
        {
                "time": 14,
                "member": "SUGA",
                "hangul": "우린 매일 밤마다 피땀 흘리며 연습실을 채웠지",
                "rom": "Urin maeil bammada pittam heullimyeo",
                "eng": "Every night we filled the practice room with blood and sweat"
        }
      ]
    },
    {
      id: 'no',
      title: "N.O",
      artist: "BTS",
      album: "O!RUL8,2?",
      year: '2013',
      era: 'debut',
      member: 'all',
      duration: 210,
      durationText: '3:30',
      streams: '210M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: 'r5GaAEmvXXc',
      ytMusicUrl: 'https://music.youtube.com/watch?v=r5GaAEmvXXc',
      bpm: 96,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "RM",
                "hangul": "좋은 집, 좋은 차, 그런 게 정말 행복일까?",
                "rom": "Joeun jip, joeun cha, geureon ge jeongmal haengbogilkka?",
                "eng": "A good house, a nice car, is that really happiness?"
        },
        {
                "time": 15,
                "member": "Jung Kook",
                "hangul": "더는 안 돼, No라고 말해!",
                "rom": "Deoneun an dwae, Nolago malhae!",
                "eng": "Not anymore, stand up and say NO!"
        }
      ]
    },
    {
      id: 'attack-on-bangtan',
      title: "Attack on Bangtan (진격의 방탄)",
      artist: "BTS",
      album: "O!RUL8,2?",
      year: '2013',
      era: 'debut',
      member: 'all',
      duration: 247,
      durationText: '4:07',
      streams: '190M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: '2qJtD_l7G9A',
      ytMusicUrl: 'https://music.youtube.com/watch?v=2qJtD_l7G9A',
      bpm: 108,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V",
                "hangul": "방탄소년단이 진격한다면 어떨까!",
                "rom": "Bangtansonyeondani jingyeokhandamyeon eotteolkka!",
                "eng": "What happens when Bangtan Boys charge forward!"
        },
        {
                "time": 15,
                "member": "Jimin",
                "hangul": "진격의 방탄!",
                "rom": "Jingyeogui bangtan!",
                "eng": "Charge on Bangtan!"
        }
      ]
    },
    {
      id: 'tomorrow',
      title: "Tomorrow",
      artist: "BTS",
      album: "Skool Luv Affair",
      year: '2014',
      era: 'debut',
      member: 'all',
      duration: 262,
      durationText: '4:22',
      streams: '280M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: 'qQJ_3vP97vI',
      ytMusicUrl: 'https://music.youtube.com/watch?v=qQJ_3vP97vI',
      bpm: 82,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "SUGA",
                "hangul": "같은 날의 반복, 어른이 되기 전의 미숙함",
                "rom": "Gateun narui banbok",
                "eng": "The repetition of the same days, unripeness before becoming adults"
        },
        {
                "time": 20,
                "member": "Jung Kook",
                "hangul": "내일이 오길 기다려, 해 뜨기 전 새벽이 가장 어두우니까",
                "rom": "Naeiri ogil gidaryeo",
                "eng": "Wait for tomorrow, because dawn just before sunrise is the darkest"
        }
      ]
    },
    {
      id: 'spine-breaker',
      title: "Spine Breaker (등골브레이커)",
      artist: "BTS",
      album: "Skool Luv Affair",
      year: '2014',
      era: 'debut',
      member: 'all',
      duration: 239,
      durationText: '3:59',
      streams: '220M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: 'lIq27m0p8eM',
      ytMusicUrl: 'https://music.youtube.com/watch?v=lIq27m0p8eM',
      bpm: 92,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "SUGA",
                "hangul": "수십만 원짜리 패딩을 입고 뽐내지",
                "rom": "Susipman wonjjari paeding-eul ipgo",
                "eng": "Wearing hundreds of dollars down jackets and showing off"
        },
        {
                "time": 16,
                "member": "Jin",
                "hangul": "우 부모님 등골이 휘어",
                "rom": "U bumonim deunggori hwieo",
                "eng": "Ooh our parents' spines are bending"
        }
      ]
    },
    {
      id: 'cypher-pt3',
      title: "BTS Cypher PT.3: KILLER (feat. Supreme Boi)",
      artist: "BTS (RM, SUGA, j-hope)",
      album: "Dark & Wild",
      year: '2014',
      era: 'debut',
      member: 'all',
      duration: 268,
      durationText: '4:28',
      streams: '310M',
      cover: COVERS.debut,
      audioSrc: null,
      youtubeId: 'r7o6B3-3p7w',
      ytMusicUrl: 'https://music.youtube.com/watch?v=r7o6B3-3p7w',
      bpm: 98,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "RM / SUGA / j-hope",
                "hangul": "누가 나를 막아도 난 내 길을 간다",
                "rom": "Nuga nareul magado nan nae gireul ganda",
                "eng": "No matter who blocks me, I walk my own path"
        }
      ]
    },
    {
      id: 'hold-me-tight',
      title: "Hold Me Tight (잡아줘)",
      artist: "BTS",
      album: "The Most Beautiful Moment in Life Pt.1",
      year: '2015',
      era: 'hyyh',
      member: 'all',
      duration: 275,
      durationText: '4:35',
      streams: '320M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: 'HhV1h_3u3fU',
      ytMusicUrl: 'https://music.youtube.com/watch?v=HhV1h_3u3fU',
      bpm: 80,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V",
                "hangul": "술 한잔 기울이며 비틀거리는 밤",
                "rom": "Sul hanjan giurimyeo biteulgeorineun bam",
                "eng": "Tilting a drink in this stumbling night"
        },
        {
                "time": 18,
                "member": "Jung Kook",
                "hangul": "제발 날 꼭 안아줘",
                "rom": "Jebal nal kkok anajwo",
                "eng": "Please hold me tight"
        }
      ]
    },
    {
      id: 'baepsae',
      title: "Silver Spoon / Baepsae (뱁새)",
      artist: "BTS",
      album: "The Most Beautiful Moment in Life Pt.2",
      year: '2015',
      era: 'hyyh',
      member: 'all',
      duration: 234,
      durationText: '3:54',
      streams: '590M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: '1pLgZlhYk4M',
      ytMusicUrl: 'https://music.youtube.com/watch?v=1pLgZlhYk4M',
      bpm: 105,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "RM",
                "hangul": "황새는 날아가고 뱁새는 다리가 찢어져",
                "rom": "Hwangsaeneun naragago baepsaeneun",
                "eng": "Storks fly while the silver spoons tear their legs"
        },
        {
                "time": 15,
                "member": "j-hope",
                "hangul": "내 탓이라니? 농담이지?",
                "rom": "Nae tasirani? Nongdamiji?",
                "eng": "My fault? Are you joking?"
        }
      ]
    },
    {
      id: 'ma-city',
      title: "Ma City",
      artist: "BTS",
      album: "The Most Beautiful Moment in Life Pt.2",
      year: '2015',
      era: 'hyyh',
      member: 'all',
      duration: 258,
      durationText: '4:18',
      streams: '290M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: '8qLgGvIq9fI',
      ytMusicUrl: 'https://music.youtube.com/watch?v=8qLgGvIq9fI',
      bpm: 104,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jimin",
                "hangul": "나를 키워준 내 고향, 부산의 바다여",
                "rom": "Nareul kiwojun nae gohyang",
                "eng": "My hometown that raised me, ocean of Busan"
        }
      ]
    },
    {
      id: 'autumn-leaves',
      title: "Autumn Leaves / Dead Leaves (고엽)",
      artist: "BTS",
      album: "The Most Beautiful Moment in Life Pt.2",
      year: '2015',
      era: 'hyyh',
      member: 'all',
      duration: 268,
      durationText: '4:28',
      streams: '310M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: 'yRzP7xH_Wfg',
      ytMusicUrl: 'https://music.youtube.com/watch?v=yRzP7xH_Wfg',
      bpm: 88,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jung Kook",
                "hangul": "떨어지는 저 낙엽처럼 우리 사랑도 저물어가",
                "rom": "Tteoreojineun jeo nagyeopcheoreom",
                "eng": "Like those falling autumn leaves, our love is withering"
        }
      ]
    },
    {
      id: 'whalien-52',
      title: "Whalien 52",
      artist: "BTS",
      album: "The Most Beautiful Moment in Life Pt.2",
      year: '2015',
      era: 'hyyh',
      member: 'all',
      duration: 244,
      durationText: '4:04',
      streams: '270M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: 'XW9n0b784sQ',
      ytMusicUrl: 'https://music.youtube.com/watch?v=XW9n0b784sQ',
      bpm: 90,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "RM",
                "hangul": "이 넓은 바다 한가운데 외로운 52헤르츠 고래",
                "rom": "I neolbeun bada hangaunde oeroun",
                "eng": "In the middle of this vast ocean, a lonely 52Hz whale"
        }
      ]
    },
    {
      id: 'young-forever',
      title: "Epilogue: Young Forever",
      artist: "BTS",
      album: "The Most Beautiful Moment in Life: Young Forever",
      year: '2016',
      era: 'hyyh',
      member: 'all',
      duration: 172,
      durationText: '2:52',
      streams: '380M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: 'HBj4F6v9P2o',
      ytMusicUrl: 'https://music.youtube.com/watch?v=HBj4F6v9P2o',
      bpm: 78,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS & ARMY",
                "hangul": "Forever we are young! 날리는 꽃잎 비 사이로 달려가",
                "rom": "Forever we are young",
                "eng": "Forever we are young! Running through the flying flower rain"
        }
      ]
    },
    {
      id: 'house-of-cards',
      title: "House of Cards (Full Length Edition)",
      artist: "BTS (Vocal Line)",
      album: "The Most Beautiful Moment in Life: Young Forever",
      year: '2016',
      era: 'hyyh',
      member: 'all',
      duration: 226,
      durationText: '3:46',
      streams: '340M',
      cover: COVERS.hyyh,
      audioSrc: null,
      youtubeId: 'tZ5tFk5g0xU',
      ytMusicUrl: 'https://music.youtube.com/watch?v=tZ5tFk5g0xU',
      bpm: 75,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Vocal Line",
                "hangul": "위태로워 또 위험해, 카드로 만든 집",
                "rom": "Witaerowo tto wiheomhae, kadeuro mandeun jip",
                "eng": "Insecure again, dangerous again, a house made of cards"
        }
      ]
    },
    {
      id: 'boy-meets-evil',
      title: "Intro: Boy Meets Evil",
      artist: "BTS (j-hope)",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'jhope',
      duration: 154,
      durationText: '2:34',
      streams: '290M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: 'iJJSh-eEdRk',
      ytMusicUrl: 'https://music.youtube.com/watch?v=iJJSh-eEdRk',
      bpm: 120,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "j-hope",
                "hangul": "내 미래의 빛을 어둡게 만든 그 달콤한 유혹",
                "rom": "Nae miraeui bicheul eodupge mandeun",
                "eng": "That sweet temptation that dimmed the light of my future"
        }
      ]
    },
    {
      id: 'lie',
      title: "Lie",
      artist: "BTS (Jimin)",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'jimin',
      duration: 215,
      durationText: '3:35',
      streams: '420M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: 'Ghy63vQ-R2o',
      ytMusicUrl: 'https://music.youtube.com/watch?v=Ghy63vQ-R2o',
      bpm: 90,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jimin",
                "hangul": "이 거짓 속에 갇힌 날 구해줘, 순결했던 날 되찾아줘",
                "rom": "I geojit soge gathin nal guhaejwo",
                "eng": "Save me from this lie, bring back who I was when I was pure"
        }
      ]
    },
    {
      id: 'stigma',
      title: "Stigma",
      artist: "BTS (V)",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'v',
      duration: 216,
      durationText: '3:36',
      streams: '380M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: '9vQ1GfxG3F4',
      ytMusicUrl: 'https://music.youtube.com/watch?v=9vQ1GfxG3F4',
      bpm: 72,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V",
                "hangul": "그때 나한테 왜 그랬어, 내가 미안해 형제여",
                "rom": "Geuttae nahante wae geuraesseo",
                "eng": "Why did you do that to me back then? I'm so sorry, my sibling"
        }
      ]
    },
    {
      id: 'first-love',
      title: "First Love",
      artist: "BTS (SUGA)",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'suga',
      duration: 189,
      durationText: '3:09',
      streams: '260M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: '7RjPZ7Wl9wE',
      ytMusicUrl: 'https://music.youtube.com/watch?v=7RjPZ7Wl9wE',
      bpm: 76,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "SUGA",
                "hangul": "내 기억의 구석 한 켠에 갈색 피아노",
                "rom": "Nae gieogui guseok han kyeone galsaek piano",
                "eng": "In the corner of my memory stands a brown piano"
        }
      ]
    },
    {
      id: 'reflection',
      title: "Reflection",
      artist: "BTS (RM)",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'rm',
      duration: 233,
      durationText: '3:53',
      streams: '210M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: 'k1-3Z1y_x-Q',
      ytMusicUrl: 'https://music.youtube.com/watch?v=k1-3Z1y_x-Q',
      bpm: 68,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "RM",
                "hangul": "I wish I could love myself",
                "rom": "I wish I could love myself",
                "eng": "I wish I could love myself"
        }
      ]
    },
    {
      id: 'mama',
      title: "MAMA",
      artist: "BTS (j-hope)",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'jhope',
      duration: 212,
      durationText: '3:32',
      streams: '290M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: 'u2-R9Qx6o0g',
      ytMusicUrl: 'https://music.youtube.com/watch?v=u2-R9Qx6o0g',
      bpm: 95,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "j-hope",
                "hangul": "이제는 내게 기대도 돼, 언제나 옆에 있을게",
                "rom": "Ijeneun naege gidaedo dwae",
                "eng": "Now you can lean on me, mom, I'll always be by your side"
        }
      ]
    },
    {
      id: 'awake',
      title: "Awake",
      artist: "BTS (Jin)",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'jin',
      duration: 226,
      durationText: '3:46',
      streams: '330M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: 'WYsrDVyTQ9Q',
      ytMusicUrl: 'https://music.youtube.com/watch?v=WYsrDVyTQ9Q',
      bpm: 76,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jin",
                "hangul": "날개를 펼치고 저 하늘을 날 수 없을지라도",
                "rom": "Nalgaereul pyeolchigo jeo haneureul",
                "eng": "Maybe I can never fly like those flower petals over there, but I will walk"
        }
      ]
    },
    {
      id: 'begin',
      title: "Begin",
      artist: "BTS (Jung Kook)",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'jungkook',
      duration: 229,
      durationText: '3:49',
      streams: '350M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: 'v8yM3n7fRmg',
      ytMusicUrl: 'https://music.youtube.com/watch?v=v8yM3n7fRmg',
      bpm: 110,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jung Kook",
                "hangul": "아무것도 없던 열다섯의 나, 세상은 참 컸고 난 너무 작았지",
                "rom": "Amugeotdo eopdeon yeoldaseosui na",
                "eng": "Fifteen-year-old me had nothing, the world was too big and I was too small"
        }
      ]
    },
    {
      id: 'cypher-4',
      title: "BTS Cypher 4",
      artist: "BTS (RM, SUGA, j-hope)",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'all',
      duration: 294,
      durationText: '4:54',
      streams: '380M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: 'bM3-g_0zQ9Y',
      ytMusicUrl: 'https://music.youtube.com/watch?v=bM3-g_0zQ9Y',
      bpm: 140,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Rap Line",
                "hangul": "I love, I love, I love myself! 소리쳐 봐",
                "rom": "I love myself",
                "eng": "I love, I love, I love myself! Shout it out loud"
        }
      ]
    },
    {
      id: '21st-century-girl',
      title: "21st Century Girl (21세기 소녀)",
      artist: "BTS",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'all',
      duration: 192,
      durationText: '3:12',
      streams: '340M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: 'z0J2KSz60lo',
      ytMusicUrl: 'https://music.youtube.com/watch?v=z0J2KSz60lo',
      bpm: 104,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS",
                "hangul": "All my ladies put your hands up! 21세기 소녀 당당하게!",
                "rom": "21segi sonyeo dangdanghage",
                "eng": "All my ladies put your hands up! 21st century girls, walk proud!"
        }
      ]
    },
    {
      id: 'two-three',
      title: "2! 3! (Still Wishing There Will Be Better Days)",
      artist: "BTS",
      album: "WINGS",
      year: '2016',
      era: 'wings',
      member: 'all',
      duration: 272,
      durationText: '4:32',
      streams: '310M',
      cover: COVERS.wings,
      audioSrc: null,
      youtubeId: '4-X9l1_vJ98',
      ytMusicUrl: 'https://music.youtube.com/watch?v=4-X9l1_vJ98',
      bpm: 80,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS & ARMY",
                "hangul": "괜찮아 자 하나 둘 셋 하면 잊어, 슬픈 기억 모두 지워",
                "rom": "Gwaenchana ja hana dul set hamyeon ijeo",
                "eng": "It's okay, now count one two three and forget, erase all sad memories"
        }
      ]
    },
    {
      id: 'best-of-me',
      title: "Best of Me",
      artist: "BTS (feat. The Chainsmokers)",
      album: "Love Yourself 承 'Her'",
      year: '2017',
      era: 'loveyourself',
      member: 'all',
      duration: 226,
      durationText: '3:46',
      streams: '510M',
      cover: COVERS.her,
      audioSrc: null,
      youtubeId: 'l1b-e5F-q7w',
      ytMusicUrl: 'https://music.youtube.com/watch?v=l1b-e5F-q7w',
      bpm: 130,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jimin",
                "hangul": "넌 나의 구원, 넌 나의 창문, You got the best of me",
                "rom": "Neon naui guwon",
                "eng": "You are my salvation, you are my window, you got the best of me"
        }
      ]
    },
    {
      id: 'dimple',
      title: "Dimple (보조개)",
      artist: "BTS (Vocal Line)",
      album: "Love Yourself 承 'Her'",
      year: '2017',
      era: 'loveyourself',
      member: 'all',
      duration: 196,
      durationText: '3:16',
      streams: '460M',
      cover: COVERS.her,
      audioSrc: null,
      youtubeId: '5YxVz8U315s',
      ytMusicUrl: 'https://music.youtube.com/watch?v=5YxVz8U315s',
      bpm: 120,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Vocal Line",
                "hangul": "그 보조개는 illegal, 안돼 위험해 you",
                "rom": "Geu bojogaeneun illegal",
                "eng": "That dimple is illegal, no it's dangerous, you!"
        }
      ]
    },
    {
      id: 'go-go',
      title: "Go Go (고민보다 Go)",
      artist: "BTS",
      album: "Love Yourself 承 'Her'",
      year: '2017',
      era: 'loveyourself',
      member: 'all',
      duration: 235,
      durationText: '3:55',
      streams: '580M',
      cover: COVERS.her,
      audioSrc: null,
      youtubeId: 'zK8e4V_6yvI',
      ytMusicUrl: 'https://music.youtube.com/watch?v=zK8e4V_6yvI',
      bpm: 105,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jimin",
                "hangul": "탕진잼 탕진잼 탕진잼! 고민보다 Go!",
                "rom": "Tangjinjaem 고민보다 Go",
                "eng": "Squandering money, squandering fun! Rather than worrying, GO!"
        }
      ]
    },
    {
      id: 'magic-shop',
      title: "Magic Shop",
      artist: "BTS",
      album: "Love Yourself 轉 'Tear'",
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 275,
      durationText: '4:35',
      streams: '640M',
      cover: COVERS.tear,
      audioSrc: null,
      youtubeId: 'y-WpB5rQ3cQ',
      ytMusicUrl: 'https://music.youtube.com/watch?v=y-WpB5rQ3cQ',
      bpm: 78,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jung Kook",
                "hangul": "내가 날 미워하던 날, 마법의 문을 열어",
                "rom": "Naega nal miwohadeon nal",
                "eng": "On days I hated myself, open the magic shop door inside your heart"
        },
        {
                "time": 20,
                "member": "BTS & ARMY",
                "hangul": "So show me, I'll show you!",
                "rom": "So show me, I'll show you!",
                "eng": "So show me, I'll show you!"
        }
      ]
    },
    {
      id: 'airplane-pt2',
      title: "Airplane pt.2",
      artist: "BTS",
      album: "Love Yourself 轉 'Tear'",
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 219,
      durationText: '3:39',
      streams: '520M',
      cover: COVERS.tear,
      audioSrc: null,
      youtubeId: 'Cnxj6T023Gg',
      ytMusicUrl: 'https://music.youtube.com/watch?v=Cnxj6T023Gg',
      bpm: 95,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jimin",
                "hangul": "이상한 꼬마, 숨쉬듯 노래했네, 어디든 가고 싶었어",
                "rom": "Isanghan kkoma, sumswideut noraehaenne",
                "eng": "An unusual kid, sang like breathing, wanted to go anywhere"
        }
      ]
    },
    {
      id: 'so-what',
      title: "So What",
      artist: "BTS",
      album: "Love Yourself 轉 'Tear'",
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 284,
      durationText: '4:44',
      streams: '390M',
      cover: COVERS.tear,
      audioSrc: null,
      youtubeId: 'JgZl3k1kFzI',
      ytMusicUrl: 'https://music.youtube.com/watch?v=JgZl3k1kFzI',
      bpm: 128,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS",
                "hangul": "Somebody call me wrong, somebody call me right! So what!",
                "rom": "So what!",
                "eng": "Somebody call me wrong, somebody call me right! We don't care, so what!"
        }
      ]
    },
    {
      id: 'outro-tear',
      title: "Outro: Tear",
      artist: "BTS (RM, SUGA, j-hope)",
      album: "Love Yourself 轉 'Tear'",
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 284,
      durationText: '4:44',
      streams: '410M',
      cover: COVERS.tear,
      audioSrc: null,
      youtubeId: 'l4dE5y6zY9Q',
      ytMusicUrl: 'https://music.youtube.com/watch?v=l4dE5y6zY9Q',
      bpm: 120,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "SUGA",
                "hangul": "이별은 내게 티어, 눈물 흘리지 마",
                "rom": "Ibyeoreun naege tieo",
                "eng": "Goodbyes are a tear to me, don't shed those tears"
        }
      ]
    },
    {
      id: 'im-fine',
      title: "I'm Fine",
      artist: "BTS",
      album: "Love Yourself 結 'Answer'",
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 240,
      durationText: '4:00',
      streams: '480M',
      cover: COVERS.answer,
      audioSrc: null,
      youtubeId: 'E_n_R16j33U',
      ytMusicUrl: 'https://music.youtube.com/watch?v=E_n_R16j33U',
      bpm: 128,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V",
                "hangul": "시리도록 푸른 하늘 아래 눈떠, 난 괜찮아",
                "rom": "Siridorok pureun haneul arae nuntteo",
                "eng": "Opening my eyes under the freezing blue sky, I'm feeling just fine"
        }
      ]
    },
    {
      id: 'answer-love-myself',
      title: "Answer: Love Myself",
      artist: "BTS",
      album: "Love Yourself 結 'Answer'",
      year: '2018',
      era: 'loveyourself',
      member: 'all',
      duration: 251,
      durationText: '4:11',
      streams: '530M',
      cover: COVERS.answer,
      audioSrc: null,
      youtubeId: 'u4kM3n9e-8g',
      ytMusicUrl: 'https://music.youtube.com/watch?v=u4kM3n9e-8g',
      bpm: 88,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS",
                "hangul": "어쩌면 누군가를 사랑하는 것보다 더 어려운 게 나 자신을 사랑하는 거야",
                "rom": "Eojjeomyeon nugungareul saranghaneun geotboda",
                "eng": "Perhaps loving oneself is harder than loving another"
        },
        {
                "time": 20,
                "member": "ARMY & BTS",
                "hangul": "You've shown me I have reasons I should love myself",
                "rom": "You've shown me I have reasons",
                "eng": "You've shown me I have reasons I should love myself"
        }
      ]
    },
    {
      id: 'intro-persona',
      title: "Intro: Persona",
      artist: "BTS (RM)",
      album: "Map of the Soul: Persona",
      year: '2019',
      era: 'mots',
      member: 'rm',
      duration: 171,
      durationText: '2:51',
      streams: '360M',
      cover: COVERS.persona,
      audioSrc: null,
      youtubeId: 'M9Uy0426gLg',
      ytMusicUrl: 'https://music.youtube.com/watch?v=M9Uy0426gLg',
      bpm: 110,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "RM",
                "hangul": "나는 누구인가 평생 물어온 질문, 아마 평생 정답은 찾지 못할 그 질문",
                "rom": "Naneun nugu-inga pyeongsaeng mureoon jilmun",
                "eng": "Who am I? The question I've asked my whole life"
        }
      ]
    },
    {
      id: 'home',
      title: "HOME",
      artist: "BTS",
      album: "Map of the Soul: Persona",
      year: '2019',
      era: 'mots',
      member: 'all',
      duration: 240,
      durationText: '4:00',
      streams: '490M',
      cover: COVERS.persona,
      audioSrc: null,
      youtubeId: 'M9n1_2v05hY',
      ytMusicUrl: 'https://music.youtube.com/watch?v=M9n1_2v05hY',
      bpm: 96,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "j-hope",
                "hangul": "미칠 듯한 설레임에 인사조차 못했어, 네가 있는 그곳이 바로 HOME",
                "rom": "Michil deuthan seolleime",
                "eng": "In that crazy excitement, wherever you are, that's my home"
        }
      ]
    },
    {
      id: 'dionysus',
      title: "Dionysus",
      artist: "BTS",
      album: "Map of the Soul: Persona",
      year: '2019',
      era: 'mots',
      member: 'all',
      duration: 248,
      durationText: '4:08',
      streams: '470M',
      cover: COVERS.persona,
      audioSrc: null,
      youtubeId: '-moG6JDmJdc',
      ytMusicUrl: 'https://music.youtube.com/watch?v=-moG6JDmJdc',
      bpm: 130,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "RM",
                "hangul": "다 마셔 마셔 마셔 마셔 내 술잔을 다!",
                "rom": "Da masyeo masyeo masyeo",
                "eng": "Drink it up, drink it up, empty my glass!"
        }
      ]
    },
    {
      id: 'my-time',
      title: "My Time (시차)",
      artist: "BTS (Jung Kook)",
      album: "Map of the Soul: 7",
      year: '2020',
      era: 'mots',
      member: 'jungkook',
      duration: 234,
      durationText: '3:54',
      streams: '430M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: 'L09l2pA5hM0',
      ytMusicUrl: 'https://music.youtube.com/watch?v=L09l2pA5hM0',
      bpm: 92,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jung Kook",
                "hangul": "스물넷, 문득 돌아보니 난 어디쯤 와있을까",
                "rom": "Seumulnet, mundeuk doraboni",
                "eng": "Twenty-four, looking back, where have I come?"
        }
      ]
    },
    {
      id: 'louder-than-bombs',
      title: "Louder than bombs",
      artist: "BTS",
      album: "Map of the Soul: 7",
      year: '2020',
      era: 'mots',
      member: 'all',
      duration: 217,
      durationText: '3:37',
      streams: '420M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: 'zLqQ-e5k50M',
      ytMusicUrl: 'https://music.youtube.com/watch?v=zLqQ-e5k50M',
      bpm: 72,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jimin",
                "hangul": "Louder than bombs, I break, 쏟아지는 아픔들",
                "rom": "Louder than bombs I break",
                "eng": "Louder than bombs I break, all those pouring pains"
        }
      ]
    },
    {
      id: 'ugh',
      title: "UGH! (욱)",
      artist: "BTS (RM, SUGA, j-hope)",
      album: "Map of the Soul: 7",
      year: '2020',
      era: 'mots',
      member: 'all',
      duration: 225,
      durationText: '3:45',
      streams: '390M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: 'XkHwY1k4HlE',
      ytMusicUrl: 'https://music.youtube.com/watch?v=XkHwY1k4HlE',
      bpm: 145,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Rap Line",
                "hangul": "타닥 또 타오르는 불씨, 욱하는 세상에 던지는 외침",
                "rom": "Tadak tto taoreuneun bulssi",
                "eng": "Sparks crackling again, shouting at this raging world"
        }
      ]
    },
    {
      id: 'zero-oclock',
      title: "00:00 (Zero O'Clock)",
      artist: "BTS (Vocal Line)",
      album: "Map of the Soul: 7",
      year: '2020',
      era: 'mots',
      member: 'all',
      duration: 257,
      durationText: '4:17',
      streams: '480M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: 'q8y0-1kK92Q',
      ytMusicUrl: 'https://music.youtube.com/watch?v=q8y0-1kK92Q',
      bpm: 80,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Vocal Line",
                "hangul": "초침과 분침이 겹칠 때, 세상은 아주 잠깐 숨을 참아",
                "rom": "Chochimgwa bunchimi gyeopchil ttae",
                "eng": "When second and minute hands overlap, the world holds its breath for a second"
        },
        {
                "time": 20,
                "member": "Jung Kook",
                "hangul": "And you gonna be happy, 자 새로운 시작이야",
                "rom": "And you gonna be happy",
                "eng": "And you gonna be happy, now it is a brand new start"
        }
      ]
    },
    {
      id: 'inner-child',
      title: "Inner Child",
      artist: "BTS (V)",
      album: "Map of the Soul: 7",
      year: '2020',
      era: 'mots',
      member: 'v',
      duration: 233,
      durationText: '3:53',
      streams: '360M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: 'n1h8qP7vK2E',
      ytMusicUrl: 'https://music.youtube.com/watch?v=n1h8qP7vK2E',
      bpm: 88,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V",
                "hangul": "은하수를 보던 어린아이, 넌 내 은하수야",
                "rom": "Eunhasureul bodeon eorinai",
                "eng": "The little kid looking up at the galaxy, you are my stars"
        }
      ]
    },
    {
      id: 'friends',
      title: "Friends (친구)",
      artist: "BTS (Jimin & V)",
      album: "Map of the Soul: 7",
      year: '2020',
      era: 'mots',
      member: 'all',
      duration: 199,
      durationText: '3:19',
      streams: '450M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: 'gQ8x_Vj3l8k',
      ytMusicUrl: 'https://music.youtube.com/watch?v=gQ8x_Vj3l8k',
      bpm: 90,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jimin & V",
                "hangul": "만두 사건부터 지금까지, 넌 나의 소울메이트",
                "rom": "Mandu sageonbuteo jigmekkaji",
                "eng": "From the dumpling fight until now, you are my soulmate"
        }
      ]
    },
    {
      id: 'moon',
      title: "Moon",
      artist: "BTS (Jin)",
      album: "Map of the Soul: 7",
      year: '2020',
      era: 'mots',
      member: 'jin',
      duration: 209,
      durationText: '3:29',
      streams: '380M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: 'P89y_L7p66c',
      ytMusicUrl: 'https://music.youtube.com/watch?v=P89y_L7p66c',
      bpm: 110,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jin",
                "hangul": "지구는 너, 나는 너를 맴도는 달",
                "rom": "Jiguneun neo, naneun neoreul maemdoneun dal",
                "eng": "You are the Earth, and I am the Moon revolving around you"
        }
      ]
    },
    {
      id: 'we-are-bulletproof-the-eternal',
      title: "We Are Bulletproof : the Eternal",
      artist: "BTS",
      album: "Map of the Soul: 7",
      year: '2020',
      era: 'mots',
      member: 'all',
      duration: 262,
      durationText: '4:22',
      streams: '420M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: '7C2z4GqqS5E',
      ytMusicUrl: 'https://music.youtube.com/watch?v=7C2z4GqqS5E',
      bpm: 76,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS",
                "hangul": "우린 일곱이었고, 지금은 너희가 함께 있어 영원해",
                "rom": "Urin ilgobieotgo",
                "eng": "We were only seven, but now we have you all, we are bulletproof"
        }
      ]
    },
    {
      id: 'blue-and-grey',
      title: "Blue & Grey",
      artist: "BTS",
      album: "BE",
      year: '2020',
      era: 'be_butter',
      member: 'all',
      duration: 255,
      durationText: '4:15',
      streams: '490M',
      cover: COVERS.be,
      audioSrc: null,
      youtubeId: 'c1oHn_bHk4Q',
      ytMusicUrl: 'https://music.youtube.com/watch?v=c1oHn_bHk4Q',
      bpm: 62,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V",
                "hangul": "Where is my angel? 하루의 끝을 드리운 그림자",
                "rom": "Where is my angel",
                "eng": "Where is my angel? Shadow hanging over the end of the day"
        }
      ]
    },
    {
      id: 'telepathy',
      title: "Telepathy (잠시)",
      artist: "BTS",
      album: "BE",
      year: '2020',
      era: 'be_butter',
      member: 'all',
      duration: 202,
      durationText: '3:22',
      streams: '380M',
      cover: COVERS.be,
      audioSrc: null,
      youtubeId: 'e89v3z-F7wA',
      ytMusicUrl: 'https://music.youtube.com/watch?v=e89v3z-F7wA',
      bpm: 105,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS",
                "hangul": "비록 지금은 멀어졌어도, 우리 마음은 닿아 있어",
                "rom": "Birok jigeumeun meoreojyeosseodo",
                "eng": "Even if we are far apart right now, our telepathy connects us"
        }
      ]
    },
    {
      id: 'stay',
      title: "Stay",
      artist: "BTS (RM, Jin, Jung Kook)",
      album: "BE",
      year: '2020',
      era: 'be_butter',
      member: 'all',
      duration: 205,
      durationText: '3:25',
      streams: '390M',
      cover: COVERS.be,
      audioSrc: null,
      youtubeId: 'x4r_M8fN58M',
      ytMusicUrl: 'https://music.youtube.com/watch?v=x4r_M8fN58M',
      bpm: 128,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jung Kook",
                "hangul": "꿈이었을까 널 본 게, I know you always stay",
                "rom": "Kkumieosseulkka neol bon ge",
                "eng": "Was it a dream that I saw you? Wherever you are, I know you always stay"
        }
      ]
    },
    {
      id: 'for-youth',
      title: "For Youth",
      artist: "BTS",
      album: "Proof",
      year: '2022',
      era: 'proof',
      member: 'all',
      duration: 265,
      durationText: '4:25',
      streams: '340M',
      cover: COVERS.proof,
      audioSrc: null,
      youtubeId: 'HBj4F6v9P2o',
      ytMusicUrl: 'https://music.youtube.com/watch?v=HBj4F6v9P2o',
      bpm: 78,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS & ARMY",
                "hangul": "내가 너의 청춘이었길, 너는 영원한 내 청춘이야",
                "rom": "Naega neoui cheongchun-ieotgil",
                "eng": "I hope I was your youth, as you are forever my youth"
        }
      ]
    },
    {
      id: 'born-singer',
      title: "Born Singer",
      artist: "BTS",
      album: "Proof",
      year: '2022',
      era: 'proof',
      member: 'all',
      duration: 239,
      durationText: '3:59',
      streams: '310M',
      cover: COVERS.proof,
      audioSrc: null,
      youtubeId: 'u4kM3n9e-8g',
      ytMusicUrl: 'https://music.youtube.com/watch?v=u4kM3n9e-8g',
      bpm: 84,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS",
                "hangul": "I'm a born singer, 조금 늦어버린 고백, 언제나 무대 위에 설게",
                "rom": "I'm a born singer",
                "eng": "I'm a born singer, a slightly delayed confession, I will always stand on stage"
        }
      ]
    },
    {
      id: 'the-planet',
      title: "The Planet",
      artist: "BTS",
      album: "BASTIONS OST",
      year: '2023',
      era: 'proof',
      member: 'all',
      duration: 182,
      durationText: '3:02',
      streams: '210M',
      cover: COVERS.proof,
      audioSrc: null,
      youtubeId: 'P27o0N3VvWw',
      ytMusicUrl: 'https://music.youtube.com/watch?v=P27o0N3VvWw',
      bpm: 118,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS",
                "hangul": "지구야 힘을 내, 우리가 함께 지켜갈게",
                "rom": "Jiguya himeul nae",
                "eng": "Cheer up planet Earth, we will protect you together"
        }
      ]
    },
    {
      id: 'film-out',
      title: "Film out",
      artist: "BTS",
      album: "BTS, THE BEST",
      year: '2021',
      era: 'be_butter',
      member: 'all',
      duration: 214,
      durationText: '3:34',
      streams: '490M',
      cover: COVERS.proof,
      audioSrc: null,
      youtubeId: 'zFT3f9biz68',
      ytMusicUrl: 'https://music.youtube.com/watch?v=zFT3f9biz68',
      bpm: 86,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jung Kook & Jin",
                "hangul": "浮かび上がる君は あまりに鮮やかで",
                "rom": "Ukabiagaru kimi wa amari ni azayaka de",
                "eng": "You that comes to mind are just so vivid"
        }
      ]
    },
    {
      id: 'stay-gold',
      title: "Stay Gold",
      artist: "BTS",
      album: "MAP OF THE SOUL: 7 ~ THE JOURNEY ~",
      year: '2020',
      era: 'mots',
      member: 'all',
      duration: 243,
      durationText: '4:03',
      streams: '450M',
      cover: COVERS.mots7,
      audioSrc: null,
      youtubeId: '9IHwqIEPrdU',
      ytMusicUrl: 'https://music.youtube.com/watch?v=9IHwqIEPrdU',
      bpm: 96,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "BTS",
                "hangul": "Stay gold, 夢の中でも Stay gold, 探しあてるよ",
                "rom": "Stay gold, yume no naka demo",
                "eng": "Stay gold, even in dreams stay gold, I will find you"
        }
      ]
    },
    {
      id: 'lights',
      title: "Lights",
      artist: "BTS",
      album: "Lights / Boy With Luv",
      year: '2019',
      era: 'mots',
      member: 'all',
      duration: 293,
      durationText: '4:53',
      streams: '380M',
      cover: COVERS.persona,
      audioSrc: null,
      youtubeId: 'eaLM39P9y04',
      ytMusicUrl: 'https://music.youtube.com/watch?v=eaLM39P9y04',
      bpm: 78,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V & Jimin",
                "hangul": "どんなに離れていても 届いている",
                "rom": "Donna ni hanarete ite mo todoite iru",
                "eng": "No matter how far apart we are, it reaches you"
        }
      ]
    },
    {
      id: 'crystal-snow',
      title: "Crystal Snow",
      artist: "BTS",
      album: "MIC Drop / DNA / Crystal Snow",
      year: '2017',
      era: 'loveyourself',
      member: 'all',
      duration: 322,
      durationText: '5:22',
      streams: '370M',
      cover: COVERS.her,
      audioSrc: null,
      youtubeId: 'p3wUqKqA3sM',
      ytMusicUrl: 'https://music.youtube.com/watch?v=p3wUqKqA3sM',
      bpm: 80,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jin",
                "hangul": "雪積もるように 確かめているよ 君がくれたもの",
                "rom": "Yuki tsumoru you ni",
                "eng": "Like piling snow, I am confirming what you gave me"
        }
      ]
    },
    {
      id: 'stay-alive',
      title: "Stay Alive (Prod. SUGA of BTS)",
      artist: "Jung Kook",
      album: "7FATES: CHAKHO OST",
      year: '2022',
      era: 'solo',
      member: 'jungkook',
      duration: 210,
      durationText: '3:30',
      streams: '390M',
      cover: COVERS.golden,
      audioSrc: null,
      youtubeId: 'yP_2P2p7KqE',
      ytMusicUrl: 'https://music.youtube.com/watch?v=yP_2P2p7KqE',
      bpm: 85,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jung Kook",
                "hangul": "제발 넌 살아남아줘, 어디 있어도",
                "rom": "Jebal neon saranamajwo",
                "eng": "Please stay alive, wherever you are"
        }
      ]
    },
    {
      id: 'never-let-go',
      title: "Never Let Go",
      artist: "Jung Kook",
      album: "FESTA 2024 Single",
      year: '2024',
      era: 'solo',
      member: 'jungkook',
      duration: 173,
      durationText: '2:53',
      streams: '280M',
      cover: COVERS.golden,
      audioSrc: null,
      youtubeId: 'k1-3Z1y_x-Q',
      ytMusicUrl: 'https://music.youtube.com/watch?v=k1-3Z1y_x-Q',
      bpm: 124,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jung Kook",
                "hangul": "Every heartbeat leads to you, Never let go of your hand",
                "rom": "Never let go",
                "eng": "Every heartbeat leads to you, I will never let go of our hand"
        }
      ]
    },
    {
      id: 'closer-than-this',
      title: "Closer Than This",
      artist: "Jimin",
      album: "FESTA Single",
      year: '2023',
      era: 'solo',
      member: 'jimin',
      duration: 223,
      durationText: '3:43',
      streams: '260M',
      cover: COVERS.face,
      audioSrc: null,
      youtubeId: '0Xw4k8z549o',
      ytMusicUrl: 'https://music.youtube.com/watch?v=0Xw4k8z549o',
      bpm: 92,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jimin",
                "hangul": "잠시 손을 놓지만 작은 쉼표일 뿐, 언제나 곁에 있어",
                "rom": "Jamsi soneul notchiman",
                "eng": "We let go for a moment, but it is just a tiny comma, we are always closer than this"
        }
      ]
    },
    {
      id: 'be-mine',
      title: "Be Mine",
      artist: "Jimin",
      album: "MUSE",
      year: '2024',
      era: 'solo',
      member: 'jimin',
      duration: 207,
      durationText: '3:27',
      streams: '310M',
      cover: COVERS.face,
      audioSrc: null,
      youtubeId: '5YxVz8U315s',
      ytMusicUrl: 'https://music.youtube.com/watch?v=5YxVz8U315s',
      bpm: 104,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jimin",
                "hangul": "You know what I want, be mine tonight",
                "rom": "Be mine",
                "eng": "You know what I want, won't you be mine tonight"
        }
      ]
    },
    {
      id: 'rainy-days',
      title: "Rainy Days",
      artist: "V",
      album: "Layover",
      year: '2023',
      era: 'solo',
      member: 'v',
      duration: 179,
      durationText: '2:59',
      streams: '390M',
      cover: COVERS.layover,
      audioSrc: null,
      youtubeId: 'yTsI93g6zF4',
      ytMusicUrl: 'https://music.youtube.com/watch?v=yTsI93g6zF4',
      bpm: 75,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V",
                "hangul": "Rainy days, I'm thinkin' 'bout you, what to say",
                "rom": "Rainy days",
                "eng": "Rainy days, I am thinking about you, what to say"
        }
      ]
    },
    {
      id: 'winter-bear',
      title: "Winter Bear",
      artist: "V",
      album: "Special Single",
      year: '2019',
      era: 'solo',
      member: 'v',
      duration: 154,
      durationText: '2:34',
      streams: '340M',
      cover: COVERS.layover,
      audioSrc: null,
      youtubeId: 'pk7ESz6vtyA',
      ytMusicUrl: 'https://music.youtube.com/watch?v=pk7ESz6vtyA',
      bpm: 68,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V",
                "hangul": "Sleep like a winter bear, good night, good night",
                "rom": "Winter bear",
                "eng": "Sleep like a winter bear, wish you a good night"
        }
      ]
    },
    {
      id: 'winter-ahead',
      title: "Winter Ahead (with PARK HYO SHIN)",
      artist: "V",
      album: "Winter Single",
      year: '2024',
      era: 'solo',
      member: 'v',
      duration: 215,
      durationText: '3:35',
      streams: '290M',
      cover: COVERS.layover,
      audioSrc: null,
      youtubeId: '8qLgGvIq9fI',
      ytMusicUrl: 'https://music.youtube.com/watch?v=8qLgGvIq9fI',
      bpm: 72,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "V & Park Hyo Shin",
                "hangul": "따스한 온기로 겨울을 맞이해",
                "rom": "Ttaseuhan ongiro",
                "eng": "Welcoming the cold winter ahead with warm melody"
        }
      ]
    },
    {
      id: 'agust-d',
      title: "Agust D",
      artist: "Agust D (SUGA)",
      album: "Agust D",
      year: '2016',
      era: 'solo',
      member: 'suga',
      duration: 186,
      durationText: '3:06',
      streams: '380M',
      cover: COVERS.dday,
      audioSrc: null,
      youtubeId: '3Y_Eiyg4bfA',
      ytMusicUrl: 'https://music.youtube.com/watch?v=3Y_Eiyg4bfA',
      bpm: 104,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Agust D",
                "hangul": "A to the G to the U to the STD! 난 대구에서 올라온 랩퍼",
                "rom": "Agust D",
                "eng": "A to the G to the U to the STD! The boy from Daegu dominating the world"
        }
      ]
    },
    {
      id: 'amygdala',
      title: "AMYGDALA",
      artist: "Agust D (SUGA)",
      album: "D-DAY",
      year: '2023',
      era: 'solo',
      member: 'suga',
      duration: 251,
      durationText: '4:11',
      streams: '320M',
      cover: COVERS.dday,
      audioSrc: null,
      youtubeId: 'ixxR3ZoqnF0',
      ytMusicUrl: 'https://music.youtube.com/watch?v=ixxR3ZoqnF0',
      bpm: 78,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Agust D",
                "hangul": "아몬드 모양의 기억, 날 이 고통에서 꺼내줘",
                "rom": "Amondeu moyangui gieok",
                "eng": "My amygdala, please save me from these painful memories"
        }
      ]
    },
    {
      id: 'that-that',
      title: "That That (prod. & feat. SUGA of BTS)",
      artist: "PSY (feat. SUGA)",
      album: "PSY 9th",
      year: '2022',
      era: 'solo',
      member: 'suga',
      duration: 174,
      durationText: '2:54',
      streams: '580M',
      cover: COVERS.dday,
      audioSrc: null,
      youtubeId: '8dJyRm2jJ-U',
      ytMusicUrl: 'https://music.youtube.com/watch?v=8dJyRm2jJ-U',
      bpm: 130,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "SUGA",
                "hangul": "That That I like that! 준비하시고 쏘세요!",
                "rom": "That That I like that",
                "eng": "That that I like that! Ready, aim, fire!"
        }
      ]
    },
    {
      id: 'daydream',
      title: "Daydream (백일몽)",
      artist: "j-hope",
      album: "Hope World",
      year: '2018',
      era: 'solo',
      member: 'jhope',
      duration: 228,
      durationText: '3:48',
      streams: '350M',
      cover: COVERS.jitb,
      audioSrc: null,
      youtubeId: 'OK34L4720AU',
      ytMusicUrl: 'https://music.youtube.com/watch?v=OK34L4720AU',
      bpm: 108,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "j-hope",
                "hangul": "내 백일몽 속에서는 다 이뤄져, 자유롭게 날아",
                "rom": "Nae baegilmong sogeseoneun",
                "eng": "In my daydream, everything comes true, flying free"
        }
      ]
    },
    {
      id: 'neuron',
      title: "NEURON (with Gaeko, YOON MIRAE)",
      artist: "j-hope",
      album: "HOPE ON THE STREET VOL.1",
      year: '2024',
      era: 'solo',
      member: 'jhope',
      duration: 213,
      durationText: '3:33',
      streams: '240M',
      cover: COVERS.jitb,
      audioSrc: null,
      youtubeId: 'HBj4F6v9P2o',
      ytMusicUrl: 'https://music.youtube.com/watch?v=HBj4F6v9P2o',
      bpm: 96,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "j-hope",
                "hangul": "거리에서 시작된 나의 춤, 신경망처럼 이어져",
                "rom": "Georieseo sijakdoen naui chum",
                "eng": "My dance that started on the streets, connected like neural paths"
        }
      ]
    },
    {
      id: 'lost-rm',
      title: "LOST!",
      artist: "RM",
      album: "Right Place, Wrong Person",
      year: '2024',
      era: 'solo',
      member: 'rm',
      duration: 233,
      durationText: '3:53',
      streams: '260M',
      cover: COVERS.indigo,
      audioSrc: null,
      youtubeId: 'y-WpB5rQ3cQ',
      ytMusicUrl: 'https://music.youtube.com/watch?v=y-WpB5rQ3cQ',
      bpm: 112,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "RM",
                "hangul": "길을 잃어도 괜찮아, 그곳이 새로운 시작이니까",
                "rom": "Gireul ireodo gwaenchana",
                "eng": "It's okay to get lost, because that's where new beginnings lie"
        }
      ]
    },
    {
      id: 'forever-rain',
      title: "forever rain",
      artist: "RM",
      album: "mono.",
      year: '2018',
      era: 'solo',
      member: 'rm',
      duration: 271,
      durationText: '4:31',
      streams: '290M',
      cover: COVERS.indigo,
      audioSrc: null,
      youtubeId: 'Cvb76hBX_Oc',
      ytMusicUrl: 'https://music.youtube.com/watch?v=Cvb76hBX_Oc',
      bpm: 65,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "RM",
                "hangul": "하루 종일 비가 왔으면 좋겠어, 아무도 내 슬픔을 모르게",
                "rom": "Haru jong-il biga wasseumyeon",
                "eng": "I wish it rained all day, so nobody could notice my tears"
        }
      ]
    },
    {
      id: 'ill-be-there',
      title: "I'll Be There",
      artist: "Jin",
      album: "Happy",
      year: '2024',
      era: 'solo',
      member: 'jin',
      duration: 181,
      durationText: '3:01',
      streams: '280M',
      cover: COVERS.astronaut,
      audioSrc: null,
      youtubeId: 'u2-R9Qx6o0g',
      ytMusicUrl: 'https://music.youtube.com/watch?v=u2-R9Qx6o0g',
      bpm: 122,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jin",
                "hangul": "지치고 힘들 때 언제나 내가 그 자리에 있을게",
                "rom": "Jichigo himdeul ttae",
                "eng": "When you are tired and weary, I will always be right there for you"
        }
      ]
    },
    {
      id: 'abyss',
      title: "Abyss",
      artist: "Jin",
      album: "Special Release",
      year: '2020',
      era: 'solo',
      member: 'jin',
      duration: 198,
      durationText: '3:18',
      streams: '270M',
      cover: COVERS.astronaut,
      audioSrc: null,
      youtubeId: '8-M5hT10WbE',
      ytMusicUrl: 'https://music.youtube.com/watch?v=8-M5hT10WbE',
      bpm: 72,
      audioQuality: 'YouTube Music Master Stream',
      lyrics: [
        {
                "time": 0,
                "member": "Jin",
                "hangul": "심연 속으로 가라앉는 마음, 그래도 난 노래해",
                "rom": "Simyeon sogeuro garaanneun maeum",
                "eng": "My heart sinking into the abyss, yet I keep singing for you"
        }
      ]
    }
  ],

  // Era Taxonomy for Easy Browsing & Filtering
  eras: [
    { id: 'all', label: 'All BTS & Solos (135+)' },
    { id: 'debut', label: 'Debut & School (2013-14)' },
    { id: 'hyyh', label: 'HYYH 화양연화 (2015-16)' },
    { id: 'wings', label: 'WINGS & YNWA (2016-17)' },
    { id: 'loveyourself', label: 'Love Yourself (2017-18)' },
    { id: 'mots', label: 'Map of the Soul (2019-20)' },
    { id: 'be_butter', label: 'Dynamite, BE & Butter (2020-21)' },
    { id: 'proof', label: 'Proof & Festa (2022-23)' },
    { id: 'solo', label: 'Chapter 2 Member Solos' }
  ],

  // Live Stadium Concert Streams
  concert: {
    title: 'BTS WORLD TOUR: LOVE YOURSELF - SPEAK YOURSELF [THE FINAL]',
    subtitle: 'Remastered Ultra-HD • Live Multicam Audio • Dolby Atmos Live Crowd',
    audioSrc: 'audio/mikrokosmos.m4a',
    audioFallback: 'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview211/v4/f4/73/db/f473db8a-b8d9-622b-7828-e40f63af00f7/mzaf_8890385023586453692.plus.aac.p.m4a',
    viewers: '2.48M',
    totalHearts: 8943200,
    setlist: [
      { id: 1, title: 'Dionysus', status: 'completed' },
      { id: 2, title: 'Not Today', status: 'completed' },
      { id: 3, title: 'Boy With Luv', status: 'completed' },
      { id: 4, title: 'Mikrokosmos (소우주)', status: 'performing' },
      { id: 5, title: 'Answer: Love Myself', status: 'upcoming' },
      { id: 6, title: 'Spring Day (봄날)', status: 'upcoming' }
    ],
    angles: [
      { id: 'main', label: 'Main Stage', youtubeId: 'kXpOEzNZ8hQ', desc: '4K Ultra-HD Master Wide Angle' },
      { id: 'rm', label: 'RM Cam', youtubeId: 'kXpOEzNZ8hQ', desc: 'Kim Namjoon Leader Cam Focus' },
      { id: 'jin', label: 'Jin Cam', youtubeId: 'kXpOEzNZ8hQ', desc: 'Worldwide Handsome Center Cam' },
      { id: 'suga', label: 'SUGA Cam', youtubeId: 'kXpOEzNZ8hQ', desc: 'Agust D Rap Line Stage Focus' },
      { id: 'jhope', label: 'j-hope Cam', youtubeId: 'kXpOEzNZ8hQ', desc: 'Main Dancer Energy Cam' },
      { id: 'jimin', label: 'Jimin Cam', youtubeId: 'kXpOEzNZ8hQ', desc: 'Graceful Choreography & Vocals' },
      { id: 'v', label: 'V Cam', youtubeId: 'kXpOEzNZ8hQ', desc: 'Deep Soul Visual Cam Focus' },
      { id: 'jungkook', label: 'Jung Kook Cam', youtubeId: 'kXpOEzNZ8hQ', desc: 'Golden Maknae High Energy Cam' }
    ],
    initialComments: [
      { user: 'BorahaePurple95', badge: 'Verified ARMY', flag: '💜', text: 'MIKROKOSMOS WITH THE PURPLE OCEAN IS GIVING ME GOOSEBUMPS 😭😭😭' },
      { user: 'Seoul_Streamer_RM', badge: 'Level 7', flag: '🇰🇷', text: 'ARMY Bomb sync is in absolute sync tonight! Look at the stadium shine!' },
      { user: 'BangtanDailyUK', badge: 'Global ARMY', flag: '🇬🇧', text: 'Jung Kook’s vocals in Dolby Atmos are otherworldly right now!' },
      { user: 'Jiminie_Serendipity', badge: 'ARMY Gold', flag: '🇺🇸', text: 'Sending infinite purple hearts from LA! BTS FOREVER! 💜💜💜' },
      { user: 'HobiSunshine218', badge: 'Verified ARMY', flag: '🇯🇵', text: 'Wait for Answer: Love Myself! Everyone get tissues ready!' }
    ]
  },

  // Member Solo Discography Hub
  members: [
    {
      id: 'all',
      name: 'All BTS',
      role: 'OT7 BTS',
      color: '#ddb7ff',
      avatar: 'https://lh3.googleusercontent.com/aida/AEtjO1XPhvx1UzB1WRyOXpQABLx3MCyfiwy_GI6rA5KLK0j8VReg9hEPLZ5DboWrKm-RoPE48wDBgm5UGDp7NsqClKfijMEsvVkLlVP4Hf4F8hpPtQkQpDw4-KjMuPTogn8J8eCdAq8eXDWwG27GiuaTj2D57XHrv3-RHTW2OwRW5tDBgN7Ja1Besn_bOo-pkAu7ruMs5rqc1R2ZuTo5P1FzoByAa1ywIxy5okKfnDLb15_Y-phjuhK9tJcGbsY',
      albums: ['Proof', 'Map of the Soul: 7', 'BE', 'Love Yourself: Tear', 'WINGS', 'HYYH Young Forever', 'Dark & Wild']
    },
    {
      id: 'rm',
      name: 'RM',
      fullName: 'Kim Namjoon',
      role: 'Leader & Rapper',
      color: '#38bdf8',
      avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBZieYSN1d0vjiqGAwBYb1R5lqu2SDCKVX3a5SQxZEfSWdSdtRPsiWNtSO8-4yTWSpG_xA0mneFU_pI8QfvyRYv0lV3CtUi33I1KM2PE3bzbNekBgfwASPMdAhry3ucwd6v10BGSlKcF4lYRCLlzVRj-nU72KWJUO0edPZkxETqbDvRjNvmXQv-D2Xt7ZnuohTaceEbtX_mYnKMqeFnElo_eYSP2_009eagjk3CO8s3EPRTgpjqtpQC',
      albums: ['Indigo (2022)', 'Right Place, Wrong Person (2024)', 'mono. (2018)']
    },
    {
      id: 'jin',
      name: 'Jin',
      fullName: 'Kim Seokjin',
      role: 'Vocalist & Visual',
      color: '#f472b6',
      avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDYEssWzZhb2Tq3Z8_mSsRwa9rYOiQg4LmsgrwWR0nnfdLOSOd7_o5tKj21BrvoT_TQMHut72T7dgTTCdti0IhXlcY6PUAfWteu8vkjgbH6_nc5SgI27aUnNwsl2e2ZID-GVuxOpd9G33xgTa3gpfb75m5ITFKUos1SBJQ_uki0wLhq3JESwp-_1ec4l7lED7BxXldSiNhjYZMLzrjwrAJeA8rD4uPYF7ZO76depKTBuDZx-KyK9TKP',
      albums: ['Happy (2024)', 'The Astronaut (2022)', 'Super Tuna', 'Epiphany']
    },
    {
      id: 'suga',
      name: 'SUGA / Agust D',
      fullName: 'Min Yoongi',
      role: 'Lead Rapper & Producer',
      color: '#fb923c',
      avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBmalZTjehco6h9a9cOWW7T9ZWy8VZnpWEoVlWO8gNWOm-gGa1SENap-ypWY217nQjkwzA037XNEPjylYoVyWoF4T9inmxUvkprYef1_qAkomHOxCR976RErzRgGN-UzwWZkZnRhFDsC32s14KuTOkPYODItZlWgF4feBy00z6i3US7RCXzAvfIH90LwtirEGdygoLXs-on5aFHDu8JbwCRXcWR-F3FEX27xAgdW_IWG2lmuv94DYuQ',
      albums: ['D-DAY (2023)', 'D-2 (2020)', 'Agust D (2016)']
    },
    {
      id: 'jhope',
      name: 'j-hope',
      fullName: 'Jung Hoseok',
      role: 'Main Dancer & Rapper',
      color: '#facc15',
      avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCW80Iil1aLMDbxWElq5bNlhrQVvtCkmIBsLyP8mlufp8-2tfeehIAQFD0VcyA2A8ZuokL4HTg10q3glgmW1pAXwh9rjCjneiqa1PeOMDNQVKFlpmDgLdQOBSY0Nn0lEzOquF6OOlI-PoHUfZSGZPgKKZ2D9_vbvAtTuNPg0MVo7qjA01aHp0-7-aq5HKCu-50pPtLqoeOchswT7gS_tn0WEL0kxa96O-YaiELU2YzWENF7ZfJ8xfR9',
      albums: ['HOPE ON THE STREET VOL.1 (2024)', 'Jack In The Box (2022)', 'Hope World (2018)']
    },
    {
      id: 'jimin',
      name: 'Jimin',
      fullName: 'Park Jimin',
      role: 'Lead Vocalist & Main Dancer',
      color: '#a855f7',
      avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAtUSoKT7kSdEOXSvqiEhxXh84lqliz0d-O7RII5LiQEvvm5P55daVurdC7GbW9LAelyjqIVAGmHR9cNL5mTeUqfCQ0AWtTq0hDVxP2nMPpwFipeYoOoo_HGmPb2vR9-LPQ3pFFnE3KSaU12EZ3091JR4LZUeJd4O_yjIdgftYGbn8Kfam0ZG2mqJPjB_2LbKDwYmzTOorDGgIxHhMC2oNXuo956WrDn3bCHwKyyj9G0b79Uf44OYpy',
      albums: ['MUSE (2024)', 'FACE (2023)', 'Filter', 'Serendipity']
    },
    {
      id: 'v',
      name: 'V',
      fullName: 'Kim Taehyung',
      role: 'Sub Vocalist & Visual',
      color: '#2dd4bf',
      avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuByPVwM2WMDJqx-kEnK-NvMrJx9NCy3dUuEe65Aocx8wmaD-2UaHcapOrQ_Kx6Pj7rOVc867WQqsd5g-dz0cbOf21-pfj3nW2P1_wrB34LxibhIVu54NMtMmmBMu_2sifHQCMOZOZrtch-BupoEDpy7GGx9kSMRD6CO9dCigZhl1pz3fNzIdsY9Wk2lgMu8VwGs7nsa_zA5HCGgc8agDClpFbl5Yi28aQeDXbAK9OvZ-PmgjXt9snc5',
      albums: ['Layover (2023)', 'FRI(END)S (2024)', 'Winter Bear', 'Sweet Night']
    },
    {
      id: 'jungkook',
      name: 'Jung Kook',
      fullName: 'Jeon Jungkook',
      role: 'Main Vocalist & Center',
      color: '#e879f9',
      avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuB4r7finDHI_Ls2C7H7fNYe4xZsIKFCvSZQUtENPpTskvBhDPBIpqFNwnMWHgSLFnemZHFykxrUh_K8VbndCsJLTloe0I5FYkk5M1EefF4ZM7r-JQvY1q9_A-NTJ7uVkrv9x4q6h559yivLn6xxkLu-rhqq0gNhxyNcEgyaOaQrF3YuEOEUVXxbF0l6HcD_2EVBTW6VnybYb_7n9_OmLuSAI16wttjYGv9ayS1PjY03sthUKyMTALme',
      albums: ['GOLDEN (2023)', 'Seven (feat. Latto)', '3D (feat. Jack Harlow)', 'Still With You']
    }
  ],

  // Official Visual Media Archive
  visualArchive: [
    {
      id: 'boy-with-luv',
      title: 'Boy With Luv (feat. Halsey)',
      type: 'Official MV',
      quality: '4K UHD',
      duration: '4:12',
      views: '1.7B views',
      youtubeId: 'XsX3ATc3FbA',
      cover: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAx3jVM7epfMbcvk5mOYkdksjWy7-v_gP3RjaGwZQ_pgGjB5V1VcFEK7POzmFtuqH6a2rd-8BQ21vXg9_0Vt7zKy__EKaUWy08vttXnrQDn4Lfojm0i56Tj4WkMlMmmfWRkJIMI3T0AjJvZaUfy_oJjFqYwxI4AiPB3V70HAvkitX071VOp3u0AmTQn0wQALwBmVG-se4BgjAiQ3ykiO6WeoTmKgG2LFSk9a_Fn4e2alAYpKvXUBjH'
    },
    {
      id: 'run-bts-dance',
      title: 'Run BTS Dance Practice',
      type: 'Special Practice',
      quality: '1080P',
      duration: '3:45',
      views: '180M views',
      youtubeId: 'sAWuYx_UswY',
      cover: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDeU6bdcL_99rq0osI9YT0sMax68i3X1MvrZ6LV6xYxfTmvQ-GLpGzKUPPMx4znlRe8zQOQyTHqXM3ARoQl_HIdwJVgwmEQ9O1z9eQ7kQ_MoRlTksWvxVHvYm17arfo_-EHHfm3-RnOCk39fmFUlbS0JOTo_b2FdcVKChrWDU9XWfe2OSQUOz9Kvx60tHLG5F8olgHDHXTts0EnJOy9LsRqjyAdYWdaQm2iA357wVwJiiLkExYH7u42'
    },
    {
      id: 'fake-love',
      title: 'Fake Love Official MV',
      type: 'Official MV',
      quality: '4K UHD',
      duration: '5:21',
      views: '1.3B views',
      youtubeId: '7C2z4GqqS5E',
      cover: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAdrfqe2nK1Pp5OL6Bp3cywLYWcWZTxrzKpJENk8VNVCxaVt7Jn7uARnj0UIeCSOpglm8WYGxC6HRQuD1J8N1Bu7vdrMLczJNeXFJLtvJ8qfVSMlsoqEqHBjgYzcoPmGIpCR8rpMSlsrznqa5Pd92Dr7vzjD9BNsG3auDvaBRrpkEpkOKY0_T0YiODsmSEK36yXuvbgmOPV4OiC9XcYEDwCWmXMKiT1yfQ_JYx9cPcub6zQ4E0D3rmQ'
    },
    {
      id: 'blood-sweat-tears-mv',
      title: 'Blood Sweat & Tears',
      type: 'Official MV',
      quality: '4K UHD',
      duration: '6:04',
      views: '980M views',
      youtubeId: 'hmE9f-TEutc',
      cover: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDpZAKPctVaO45rJyliyyEZCGgPXbKXhjwjOmCzxIq4kUr_MGr4j16MN_PLQmbZ7eNX9PyDV9oUmKWXLuEKsTj5MdyLXSSvjalT_2RCalZTqc4cxQHidVsXY9MvUfO76CNlgSOc-eJT8miIkAvKu07RDlb2kHot60-LiZOg6PvGE17UJZu_F2RwPB8x8k_dDZ7PqjOn2TsREDHAqn3Sv6HJkJkOrAREKp0pA5NhBRRE7dTo8Qr_PfWp'
    }
  ],

  // Curated Playlists
  curatedPlaylists: [
    {
      id: 'bts-music-10-years',
      title: 'BTS Music Hits: The 10-Year Journey',
      curator: 'ARMY Global',
      trackCount: 120,
      saves: '2.4M',
      cover: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAwoEq52-8VVKX3c-NQZm-LVPdePGfqjKJYKjOs0yOc5CF4ZlfNMSYfXQp1jPxkMhXhRBZDrK61wYG_b7SfDZtkLLgRVEE9lWzZ2I-leCMLoP5-ro7GIHAFr8014ALnxE9Wx4Wu8Z2xDycU5eX20HdBV2a5dN82p7b5nSiqPPwnOUi9rAsCmB9FnpZ2Qxqb8grWme3oZVgeByqIhWNid_Q0iH8JP_8td1sabfQ11UQupChgSbOMzfy_',
      trackIds: ['standing-next-to-you', 'dynamite', 'butter', 'spring-day', 'blood-sweat-tears', 'boy-with-luv', 'dna', 'fake-love']
    },
    {
      id: 'late-night-lofi',
      title: 'BTS Late Night Lo-Fi & Rain',
      curator: 'Seoul Beats',
      trackCount: 45,
      saves: '890k',
      cover: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDADA-u4yPTnFfD-qK9Ek-pr6MwMs9dy9G0TKVLx3GwQYdr7PN_zajIhwkgKBu7qvnIX3BCr82WnyNBzegZvMhIIPMy2QTm5pF92TZbHcQbZ8QqzBm_tfpYRihyq3GovHL5C9GybYpy3a2LZLiiGOCWw1dajsTTHkILlF4dI3BiUlIscscGq6-40k2R9FUC7gtZBEXxdZofBOQ85_rdcC1bkNdm_0C5aeQ4GyTIFMZfjQJJJymJPqL4',
      trackIds: ['black-swan', 'spring-day', 'yet-to-come', 'still-with-you', 'slow-dancing', 'butterfly']
    },
    {
      id: 'gym-hype-cypher',
      title: 'Gym Hype & Cypher Rap Line',
      curator: 'ARMY Fitness',
      trackCount: 52,
      saves: '1.1M',
      cover: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDliWwLM-WOfTDul8TMzDmYqlEQfusCmYyoYJ8w5bY4l5CI1zNGT6s4RY4p3pRRyXGOBDMZziKyzd00_nDR2tPYr5zcLVxpGFcjLQQLQ10fRY0gVLQVFyWYYwQ62eqZs9n0t2CShw8jb6-n5XKtB860pwn2RJEAHIWPw0S0x5jCa3FFf6IH-aKq7Sw2pq_mcltl45siBUJYtExRcCvAJ5OdIvIGAqy0B3nbDyYGCP6Yqv2h4GT09skJ',
      trackIds: ['standing-next-to-you', 'dynamite', 'butter', 'mic-drop', 'run-bts', 'fire', 'dope', 'haegeum', 'daechwita', 'ddaeng']
    }
  ],

  // Milestone Streaming Goals
  milestones: [
    { title: 'Dynamite 2B Global Streams', current: 1.84, target: 2.0, unit: 'B', percent: 92 },
    { title: 'Standing Next to You 1.5B Goal', current: 1.22, target: 1.5, unit: 'B', percent: 81 },
    { title: 'Spring Day 1 Billion Queen Milestone', current: 0.98, target: 1.0, unit: 'B', percent: 98 },
    { title: 'Seven 2.5 Billion Global Record', current: 2.12, target: 2.5, unit: 'B', percent: 85 }
  ]
};
