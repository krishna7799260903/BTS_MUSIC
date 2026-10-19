package com.army.btsmusic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.army.btsmusic.data.remote.YouTubeMusicService
import com.army.btsmusic.data.repository.DownloadRepository
import com.army.btsmusic.data.repository.MusicRepository
import com.army.btsmusic.model.Member
import com.army.btsmusic.model.Track
import com.army.btsmusic.model.UserAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LibraryViewModel(
    private val musicRepository: MusicRepository,
    private val downloadRepository: DownloadRepository,
    private val youTubeMusicService: YouTubeMusicService
) : ViewModel() {

    val selectedMember: StateFlow<Member> = musicRepository.selectedMember
    val filteredTracks: StateFlow<List<Track>> = musicRepository.getFilteredTracksFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val downloadedTracks: StateFlow<List<Track>> = downloadRepository.getAllDownloadedTracks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userAccount: StateFlow<UserAccount> = youTubeMusicService.currentUserAccount
    val downloadProgressMap: StateFlow<Map<String, Float>> = downloadRepository.downloadProgressMap

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _favoriteTrackIds = MutableStateFlow<Set<String>>(
        setOf("bts-spring-day", "jk-standing-next-to-you", "bts-mikrokosmos", "jimin-like-crazy")
    )
    val favoriteTrackIds: StateFlow<Set<String>> = _favoriteTrackIds.asStateFlow()

    fun toggleFavorite(trackId: String) {
        val current = _favoriteTrackIds.value
        _favoriteTrackIds.value = if (current.contains(trackId)) {
            current - trackId
        } else {
            current + trackId
        }
    }

    private val _searchResults = MutableStateFlow<List<Track>>(emptyList())
    val searchResults: StateFlow<List<Track>> = _searchResults.asStateFlow()

    fun selectMember(member: Member) {
        musicRepository.selectMember(member)
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _searchResults.value = musicRepository.search(query)
        }
    }

    fun toggleDownload(track: Track) {
        viewModelScope.launch {
            if (track.isDownloaded) {
                downloadRepository.removeDownload(track.id)
            } else {
                downloadRepository.downloadTrack(track)
            }
        }
    }

    fun signInWithYouTube(email: String) {
        viewModelScope.launch {
            youTubeMusicService.signInWithGoogle(email)
        }
    }

    fun syncYouTubeLibrary() {
        viewModelScope.launch {
            youTubeMusicService.syncLibrary()
        }
    }

    fun signOutYouTube() {
        viewModelScope.launch {
            youTubeMusicService.signOut()
        }
    }
}
