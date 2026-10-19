package com.army.btsmusic.data.repository

import com.army.btsmusic.data.BtsDiscographyCatalog
import com.army.btsmusic.data.remote.YouTubeMusicService
import com.army.btsmusic.model.Member
import com.army.btsmusic.model.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine

class MusicRepository(
    private val youTubeMusicService: YouTubeMusicService,
    private val downloadRepository: DownloadRepository
) {
    private val _allTracks = MutableStateFlow(BtsDiscographyCatalog.sampleTracks)
    val allTracks = _allTracks.asStateFlow()

    private val _selectedMember = MutableStateFlow(Member.OT7)
    val selectedMember = _selectedMember.asStateFlow()

    fun selectMember(member: Member) {
        _selectedMember.value = member
    }

    fun getFilteredTracksFlow(): Flow<List<Track>> {
        return combine(_allTracks, _selectedMember, downloadRepository.getAllDownloadedTracks()) { tracks, member, downloaded ->
            val downloadedIds = downloaded.map { it.id }.toSet()
            val filtered = if (member == Member.OT7) tracks else tracks.filter { it.member == member }
            filtered.map { track ->
                if (downloadedIds.contains(track.id)) {
                    val dl = downloaded.first { it.id == track.id }
                    track.copy(isDownloaded = true, localFilePath = dl.localFilePath)
                } else {
                    track.copy(isDownloaded = false, localFilePath = null)
                }
            }
        }
    }

    suspend fun search(query: String): List<Track> {
        return youTubeMusicService.searchYouTubeMusic(query)
    }

    fun getTrackById(id: String): Track? {
        return _allTracks.value.firstOrNull { it.id == id }
    }
}
