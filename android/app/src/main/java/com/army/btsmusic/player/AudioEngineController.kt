package com.army.btsmusic.player

import android.content.Context
import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer
import android.media.audiofx.Virtualizer
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.army.btsmusic.model.AudioEffectsState
import com.army.btsmusic.model.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AudioEngineController(private val context: Context) {

    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private var progressPollingJob: Job? = null

    val exoPlayer: ExoPlayer = ExoPlayer.Builder(context)
        .build()
        .apply {
            repeatMode = Player.REPEAT_MODE_ALL
        }

    private var equalizer: Equalizer? = null
    private var bassBoost: BassBoost? = null
    private var virtualizer: Virtualizer? = null

    // Playback State
    private val _currentTrack = MutableStateFlow<Track?>(null)
    val currentTrack: StateFlow<Track?> = _currentTrack.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPositionMs = MutableStateFlow(0L)
    val currentPositionMs: StateFlow<Long> = _currentPositionMs.asStateFlow()

    private val _durationMs = MutableStateFlow(0L)
    val durationMs: StateFlow<Long> = _durationMs.asStateFlow()

    private val _isShuffleOn = MutableStateFlow(false)
    val isShuffleOn: StateFlow<Boolean> = _isShuffleOn.asStateFlow()

    private val _repeatMode = MutableStateFlow(Player.REPEAT_MODE_ALL)
    val repeatMode: StateFlow<Int> = _repeatMode.asStateFlow()

    // Audio Effects State
    private val _audioEffectsState = MutableStateFlow(AudioEffectsState())
    val audioEffectsState: StateFlow<AudioEffectsState> = _audioEffectsState.asStateFlow()

    val youTubeAudioEngine = YouTubeAudioEngine(context)
    private var isYouTubeActive = false

    init {
        // Collect YouTube Audio Engine states
        scope.launch {
            youTubeAudioEngine.isPlaying.collect { playing ->
                if (isYouTubeActive) {
                    _isPlaying.value = playing
                }
            }
        }
        scope.launch {
            youTubeAudioEngine.currentPositionMs.collect { pos ->
                if (isYouTubeActive) {
                    _currentPositionMs.value = pos
                }
            }
        }
        scope.launch {
            youTubeAudioEngine.durationMs.collect { dur ->
                if (isYouTubeActive && dur > 0) {
                    _durationMs.value = dur
                }
            }
        }

        // Collect ExoPlayer states
        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(playing: Boolean) {
                if (!isYouTubeActive) {
                    _isPlaying.value = playing
                    if (playing) {
                        startProgressPolling()
                    } else {
                        stopProgressPolling()
                    }
                }
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                if (!isYouTubeActive && playbackState == Player.STATE_READY) {
                    _durationMs.value = exoPlayer.duration.coerceAtLeast(0L)
                    attachAudioEffects()
                }
            }
        })
    }

    private fun attachAudioEffects() {
        try {
            val audioSessionId = exoPlayer.audioSessionId
            if (audioSessionId != 0 && equalizer == null) {
                equalizer = Equalizer(0, audioSessionId).apply {
                    enabled = _audioEffectsState.value.isEqualizerEnabled
                }
                bassBoost = BassBoost(0, audioSessionId).apply {
                    enabled = _audioEffectsState.value.isEqualizerEnabled
                    setStrength(_audioEffectsState.value.bassBoostStrength.toShort())
                }
                virtualizer = Virtualizer(0, audioSessionId).apply {
                    enabled = _audioEffectsState.value.isEqualizerEnabled
                    setStrength(_audioEffectsState.value.virtualizerStrength.toShort())
                }
                applyBandLevels(_audioEffectsState.value.bandLevels)
            }
        } catch (e: Exception) {
            // Some devices or emulators restrict AudioEffect instantiation
        }
    }

    fun playTrack(track: Track) {
        _currentTrack.value = track
        _currentPositionMs.value = 0L
        _durationMs.value = (track.durationSeconds * 1000L)

        val hasLocalFile = track.isDownloaded && track.localFilePath != null && java.io.File(track.localFilePath).exists()
        if (hasLocalFile) {
            isYouTubeActive = false
            youTubeAudioEngine.pause()
            val mediaItem = MediaItem.fromUri(track.localFilePath!!)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
            applyPlaybackParameters()
        } else {
            isYouTubeActive = true
            exoPlayer.stop()
            val videoId = track.youtubeVideoId.ifEmpty {
                track.streamUrl.substringAfter("watch?v=", "")
            }
            if (videoId.isNotBlank()) {
                youTubeAudioEngine.playVideo(videoId)
                youTubeAudioEngine.setPlaybackRate(_audioEffectsState.value.tempo)
            }
        }
    }

    fun togglePlayPause() {
        if (isYouTubeActive) {
            if (_isPlaying.value) {
                youTubeAudioEngine.pause()
            } else {
                youTubeAudioEngine.resume()
            }
        } else {
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
        }
    }

    fun seekTo(positionMs: Long) {
        _currentPositionMs.value = positionMs
        if (isYouTubeActive) {
            youTubeAudioEngine.seekTo(positionMs)
        } else {
            exoPlayer.seekTo(positionMs)
        }
    }

    fun toggleShuffle() {
        _isShuffleOn.value = !_isShuffleOn.value
        exoPlayer.shuffleModeEnabled = _isShuffleOn.value
    }

    fun toggleRepeat() {
        val nextMode = when (_repeatMode.value) {
            Player.REPEAT_MODE_ALL -> Player.REPEAT_MODE_ONE
            Player.REPEAT_MODE_ONE -> Player.REPEAT_MODE_OFF
            else -> Player.REPEAT_MODE_ALL
        }
        _repeatMode.value = nextMode
        exoPlayer.repeatMode = nextMode
    }

    // Audio Engine: Pitch & Tempo shifting
    fun setPlaybackSpeed(speed: Float) {
        val current = _audioEffectsState.value
        val clampedSpeed = speed.coerceIn(0.5f, 2.0f)
        _audioEffectsState.value = current.copy(tempo = clampedSpeed)
        if (isYouTubeActive) {
            youTubeAudioEngine.setPlaybackRate(clampedSpeed)
        }
        applyPlaybackParameters()
    }

    fun setPitchSemitones(semitones: Float) {
        val current = _audioEffectsState.value
        val clampedPitch = semitones.coerceIn(-6.0f, 6.0f)
        _audioEffectsState.value = current.copy(pitchSemitones = clampedPitch)
        applyPlaybackParameters()
    }

    private fun applyPlaybackParameters() {
        val speed = _audioEffectsState.value.tempo
        // Convert semitones to pitch multiplier: 2^(semitones / 12)
        val pitchMultiplier = Math.pow(2.0, (_audioEffectsState.value.pitchSemitones / 12.0)).toFloat()
        exoPlayer.playbackParameters = PlaybackParameters(speed, pitchMultiplier)
    }

    // Audio Engine: Silence Skipping
    fun setSkipSilence(enabled: Boolean) {
        _audioEffectsState.value = _audioEffectsState.value.copy(skipSilenceEnabled = enabled)
        exoPlayer.skipSilenceEnabled = enabled
    }

    // Audio Engine: Equalizer Controls
    fun setEqualizerPreset(presetName: String) {
        val levels = AudioEffectsState.PRESETS[presetName] ?: return
        _audioEffectsState.value = _audioEffectsState.value.copy(
            currentPreset = presetName,
            bandLevels = levels
        )
        applyBandLevels(levels)
    }

    fun setBandLevel(bandIndex: Int, level: Int) {
        val newLevels = _audioEffectsState.value.bandLevels.toMutableList()
        if (bandIndex in newLevels.indices) {
            newLevels[bandIndex] = level
            _audioEffectsState.value = _audioEffectsState.value.copy(
                currentPreset = "Custom Borahae EQ",
                bandLevels = newLevels
            )
            applyBandLevels(newLevels)
        }
    }

    private fun applyBandLevels(levels: List<Int>) {
        equalizer?.let { eq ->
            try {
                val minLevel = eq.bandLevelRange[0]
                val maxLevel = eq.bandLevelRange[1]
                val range = maxLevel - minLevel
                for (i in 0 until eq.numberOfBands.coerceAtMost(levels.size.toShort())) {
                    val normalized = (levels[i] + 10).toFloat() / 20.0f
                    val bandLevel = (minLevel + (normalized * range)).toInt().toShort()
                    eq.setBandLevel(i.toShort(), bandLevel)
                }
            } catch (e: Exception) { }
        }
    }

    fun setBassBoost(strength: Int) {
        _audioEffectsState.value = _audioEffectsState.value.copy(bassBoostStrength = strength)
        bassBoost?.setStrength(strength.toShort())
    }

    fun setVirtualizer(strength: Int) {
        _audioEffectsState.value = _audioEffectsState.value.copy(virtualizerStrength = strength)
        virtualizer?.setStrength(strength.toShort())
    }

    private fun startProgressPolling() {
        stopProgressPolling()
        progressPollingJob = scope.launch {
            while (isActive) {
                _currentPositionMs.value = exoPlayer.currentPosition
                delay(200)
            }
        }
    }

    private fun stopProgressPolling() {
        progressPollingJob?.cancel()
        progressPollingJob = null
    }

    fun release() {
        stopProgressPolling()
        try {
            equalizer?.release()
            bassBoost?.release()
            virtualizer?.release()
            exoPlayer.release()
            youTubeAudioEngine.stop()
        } catch (e: Exception) { }
    }
}
