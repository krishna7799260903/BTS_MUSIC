package com.army.btsmusic.data.local

import kotlinx.coroutines.flow.Flow

interface TrackDao {
    fun getAllDownloadedTracks(): Flow<List<DownloadEntity>>
    suspend fun getDownloadById(trackId: String): DownloadEntity?
    fun isTrackDownloaded(trackId: String): Flow<Boolean>
    suspend fun insertDownloadedTrack(track: DownloadEntity)
    suspend fun deleteDownloadedTrack(trackId: String)
    suspend fun clearAllDownloads()
}
