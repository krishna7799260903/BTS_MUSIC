package com.army.btsmusic.model

data class AudioEffectsState(
    val isEqualizerEnabled: Boolean = true,
    val currentPreset: String = "Borahae Bass",
    // 5-band gain in millibels or normalized dB (-10 to +10 dB)
    val bandLevels: List<Int> = listOf(5, 3, 0, 2, 6), // 60Hz, 230Hz, 910Hz, 3.6kHz, 14kHz
    val bassBoostStrength: Int = 650, // 0 - 1000
    val virtualizerStrength: Int = 400, // 0 - 1000
    // Advanced Pitch and Tempo shifting
    val tempo: Float = 1.0f, // 0.5x to 2.0x playback speed
    val pitchSemitones: Float = 0.0f, // -6.0 to +6.0 semitones
    // Automatic silence skipping
    val skipSilenceEnabled: Boolean = false
) {
    companion object {
        val PRESETS = mapOf(
            "Borahae Bass" to listOf(6, 4, 1, 2, 5),
            "Vocal Clarity (Serendipity)" to listOf(-1, 2, 5, 4, 3),
            "Stadium Concert Live" to listOf(4, 2, 3, 5, 7),
            "Chill Lofi V" to listOf(3, 1, 0, -2, -1),
            "Rapline Hype (Agust D)" to listOf(7, 5, 2, 3, 4),
            "Flat / Natural" to listOf(0, 0, 0, 0, 0)
        )
    }
}
