package com.army.btsmusic.data.local

import com.army.btsmusic.model.Member
import com.army.btsmusic.model.Track

data class DownloadEntity(
    val id: String,
    val title: String,
    val artist: String,
    val memberStageName: String,
    val album: String,
    val durationSeconds: Int,
    val youtubeVideoId: String,
    val coverUrl: String,
    val localFilePath: String,
    val downloadedAt: Long = System.currentTimeMillis(),
    val fileSizeBytes: Long = 0L
) {
    fun toTrack(): Track {
        return Track(
            id = id,
            title = title,
            artist = artist,
            member = Member.fromString(memberStageName),
            album = album,
            durationSeconds = durationSeconds,
            streamUrl = localFilePath,
            youtubeVideoId = youtubeVideoId,
            coverUrl = coverUrl,
            isDownloaded = true,
            localFilePath = localFilePath
        )
    }

    companion object {
        fun fromTrack(track: Track, localPath: String, sizeBytes: Long): DownloadEntity {
            return DownloadEntity(
                id = track.id,
                title = track.title,
                artist = track.artist,
                memberStageName = track.member.stageName,
                album = track.album,
                durationSeconds = track.durationSeconds,
                youtubeVideoId = track.youtubeVideoId,
                coverUrl = track.coverUrl,
                localFilePath = localPath,
                fileSizeBytes = sizeBytes
            )
        }
    }
}
