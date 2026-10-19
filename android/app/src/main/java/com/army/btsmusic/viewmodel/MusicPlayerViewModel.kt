package com.army.btsmusic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.army.btsmusic.model.AudioEffectsState
import com.army.btsmusic.model.LyricLine
import com.army.btsmusic.model.Track
import com.army.btsmusic.player.AudioEngineController
import com.army.btsmusic.player.VisualizerProcessor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MusicPlayerViewModel(
    val audioEngineController: AudioEngineController
) : ViewModel() {

    val visualizerProcessor = VisualizerProcessor()

    val currentTrack: StateFlow<Track?> = audioEngineController.currentTrack
    val isPlaying: StateFlow<Boolean> = audioEngineController.isPlaying
    val currentPositionMs: StateFlow<Long> = audioEngineController.currentPositionMs
    val durationMs: StateFlow<Long> = audioEngineController.durationMs
    val isShuffleOn: StateFlow<Boolean> = audioEngineController.isShuffleOn
    val repeatMode: StateFlow<Int> = audioEngineController.repeatMode
    val audioEffectsState: StateFlow<AudioEffectsState> = audioEngineController.audioEffectsState
    val frequencies: StateFlow<List<Float>> = visualizerProcessor.frequencies

    // Full Player Modal Sheet visibility
    private val _isFullPlayerExpanded = MutableStateFlow(false)
    val isFullPlayerExpanded: StateFlow<Boolean> = _isFullPlayerExpanded.asStateFlow()

    // Lyrics view mode: Korean Hangul, Romanized, or English
    private val _lyricsMode = MutableStateFlow("ALL") // "ALL", "HANGUL", "ROMANIZED", "ENGLISH"
    val lyricsMode: StateFlow<String> = _lyricsMode.asStateFlow()

    // Active Lyric Line based on playback timestamp
    val currentLyricLine: StateFlow<LyricLine?> = combine(currentTrack, currentPositionMs) { track, position ->
        val lyrics = track?.lyrics ?: emptyList()
        lyrics.lastOrNull { it.timestampMs <= position }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        viewModelScope.launch {
            isPlaying.collect { playing ->
                visualizerProcessor.start(playing)
            }
        }
    }

    fun playTrack(track: Track) {
        audioEngineController.playTrack(track)
    }

    fun togglePlayPause() {
        audioEngineController.togglePlayPause()
    }

    fun seekTo(positionMs: Long) {
        audioEngineController.seekTo(positionMs)
    }

    fun toggleShuffle() {
        audioEngineController.toggleShuffle()
    }

    fun toggleRepeat() {
        audioEngineController.toggleRepeat()
    }

    fun expandFullPlayer() {
        _isFullPlayerExpanded.value = true
    }

    fun collapseFullPlayer() {
        _isFullPlayerExpanded.value = false
    }

    fun setLyricsMode(mode: String) {
        _lyricsMode.value = mode
    }

    // Audio Engine Controls
    fun setPlaybackSpeed(speed: Float) {
        audioEngineController.setPlaybackSpeed(speed)
    }

    fun setPitchSemitones(semitones: Float) {
        audioEngineController.setPitchSemitones(semitones)
    }

    fun setSkipSilence(enabled: Boolean) {
        audioEngineController.setSkipSilence(enabled)
    }

    fun setEqualizerPreset(presetName: String) {
        audioEngineController.setEqualizerPreset(presetName)
    }

    fun setBandLevel(bandIndex: Int, level: Int) {
        audioEngineController.setBandLevel(bandIndex, level)
    }

    fun setBassBoost(strength: Int) {
        audioEngineController.setBassBoost(strength)
    }

    fun setVirtualizer(strength: Int) {
        audioEngineController.setVirtualizer(strength)
    }

    override fun onCleared() {
        super.onCleared()
        visualizerProcessor.stop()
    }
}
