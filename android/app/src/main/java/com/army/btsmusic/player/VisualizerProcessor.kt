package com.army.btsmusic.player

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.sin
import kotlin.random.Random

class VisualizerProcessor {

    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private var visualizerJob: Job? = null

    private val barCount = 24
    private val _frequencies = MutableStateFlow(List(barCount) { 0.15f })
    val frequencies: StateFlow<List<Float>> = _frequencies.asStateFlow()

    fun start(isPlaying: Boolean) {
        visualizerJob?.cancel()
        visualizerJob = scope.launch {
            var step = 0f
            while (isActive) {
                if (isPlaying) {
                    step += 0.18f
                    val animatedBars = List(barCount) { index ->
                        val base = (sin(step + (index * 0.45f)) + 1f) / 2f
                        val noise = Random.nextFloat() * 0.35f
                        (base * 0.65f + noise).coerceIn(0.12f, 1.0f)
                    }
                    _frequencies.value = animatedBars
                } else {
                    // Decay smoothly to idle state
                    _frequencies.value = List(barCount) { 0.1f }
                }
                delay(40) // 25 FPS animation loop
            }
        }
    }

    fun stop() {
        visualizerJob?.cancel()
        _frequencies.value = List(barCount) { 0.1f }
    }
}
