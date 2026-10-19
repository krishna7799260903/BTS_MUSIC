package com.army.btsmusic.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Query("SELECT * FROM downloaded_tracks ORDER BY downloadedAt DESC")
    fun getAllDownloadedTracks(): Flow<List<DownloadEntity>>

    @Query("SELECT * FROM downloaded_tracks WHERE id = :trackId LIMIT 1")
    suspend fun getDownloadById(trackId: String): DownloadEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM downloaded_tracks WHERE id = :trackId)")
    fun isTrackDownloaded(trackId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadedTrack(track: DownloadEntity)

    @Query("DELETE FROM downloaded_tracks WHERE id = :trackId")
    suspend fun deleteDownloadedTrack(trackId: String)

    @Query("DELETE FROM downloaded_tracks")
    suspend fun clearAllDownloads()
}
