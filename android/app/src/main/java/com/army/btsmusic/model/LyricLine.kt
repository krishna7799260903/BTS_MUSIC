package com.army.btsmusic.model

data class LyricLine(
    val timestampMs: Long,
    val hangul: String,
    val romanized: String,
    val english: String,
    val isFanchant: Boolean = false,
    val fanchantText: String? = null
)
