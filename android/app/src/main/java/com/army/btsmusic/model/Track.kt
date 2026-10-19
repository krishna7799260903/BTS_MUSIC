package com.army.btsmusic.model

data class Track(
    val id: String,
    val title: String,
    val artist: String,
    val member: Member = Member.OT7,
    val album: String,
    val durationSeconds: Int,
    val streamUrl: String,
    val youtubeVideoId: String,
    val coverUrl: String,
    val isDownloaded: Boolean = false,
    val localFilePath: String? = null,
    val lyrics: List<LyricLine> = emptyList(),
    val releaseYear: Int = 2022,
    val playCount: Long = 0L
) {
    val durationFormatted: String
        get() {
            val minutes = durationSeconds / 60
            val seconds = durationSeconds % 60
            return "%d:%02d".format(minutes, seconds)
        }
}
