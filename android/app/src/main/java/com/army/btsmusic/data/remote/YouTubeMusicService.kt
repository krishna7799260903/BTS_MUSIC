package com.army.btsmusic.data.remote

import com.army.btsmusic.data.BtsDiscographyCatalog
import com.army.btsmusic.model.Member
import com.army.btsmusic.model.Track
import com.army.btsmusic.model.UserAccount
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class YouTubeMusicService {

    private val _currentUserAccount = MutableStateFlow(
        UserAccount(
            isSignedIn = false,
            channelTitle = "ARMY Guest",
            email = "army@weverse.io",
            youtubeMusicPlaylistCount = 0,
            syncedLikedSongsCount = 0
        )
    )
    val currentUserAccount: StateFlow<UserAccount> = _currentUserAccount.asStateFlow()

    suspend fun signInWithGoogle(email: String): Result<UserAccount> {
        _currentUserAccount.value = _currentUserAccount.value.copy(isSyncing = true)
        delay(1200) // Realistic OAuth handshake & library sync
        val account = UserAccount(
            isSignedIn = true,
            channelTitle = "ARMY Borahae 💜",
            email = email.ifEmpty { "army.streamer@gmail.com" },
            avatarUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=150",
            youtubeMusicPlaylistCount = 18,
            syncedLikedSongsCount = 340,
            isSyncing = false,
            lastSyncTime = "Synced just now"
        )
        _currentUserAccount.value = account
        return Result.success(account)
    }

    suspend fun signOut() {
        _currentUserAccount.value = UserAccount(
            isSignedIn = false,
            channelTitle = "ARMY Guest",
            email = "guest@weverse.io",
            youtubeMusicPlaylistCount = 0,
            syncedLikedSongsCount = 0
        )
    }

    suspend fun syncLibrary(): Result<Int> {
        if (!_currentUserAccount.value.isSignedIn) {
            return Result.failure(IllegalStateException("Not signed in"))
        }
        _currentUserAccount.value = _currentUserAccount.value.copy(isSyncing = true)
        delay(1500)
        _currentUserAccount.value = _currentUserAccount.value.copy(
            isSyncing = false,
            youtubeMusicPlaylistCount = _currentUserAccount.value.youtubeMusicPlaylistCount + 2,
            syncedLikedSongsCount = _currentUserAccount.value.syncedLikedSongsCount + 15,
            lastSyncTime = "Synced just now"
        )
        return Result.success(17)
    }

    suspend fun searchYouTubeMusic(query: String): List<Track> {
        delay(300)
        val clean = query.trim().lowercase()
        if (clean.isEmpty()) return BtsDiscographyCatalog.sampleTracks
        return BtsDiscographyCatalog.sampleTracks.filter { track ->
            track.title.lowercase().contains(clean) ||
            track.artist.lowercase().contains(clean) ||
            track.album.lowercase().contains(clean) ||
            track.member.stageName.lowercase().contains(clean)
        }
    }

    suspend fun resolveStreamUrl(videoId: String): String {
        delay(150)
        // In a production backend, this resolves via YouTube Music InnerTube or Piped proxy.
        // Fallback to high quality streamable preview audio
        return BtsDiscographyCatalog.sampleTracks.firstOrNull { it.youtubeVideoId == videoId }?.streamUrl
            ?: "https://actions.google.com/sounds/v1/ambiences/outdoor_evening_crickets.ogg"
    }

    fun getCuratedArmyPlaylists(): List<Pair<String, List<Track>>> {
        return listOf(
            "BTS Iconic Title Anthems" to BtsDiscographyCatalog.sampleTracks.filter { it.member == Member.OT7 },
            "Rapline Fire (RM x SUGA x j-hope)" to BtsDiscographyCatalog.sampleTracks.filter { 
                it.member == Member.RM || it.member == Member.SUGA || it.member == Member.J_HOPE 
            },
            "Vocal Line Serenade (Jin, Jimin, V, Jung Kook)" to BtsDiscographyCatalog.sampleTracks.filter { 
                it.member == Member.JIN || it.member == Member.JIMIN || it.member == Member.V || it.member == Member.JUNG_KOOK 
            },
            "Late Night HYYH Nostalgia" to BtsDiscographyCatalog.sampleTracks.take(4)
        )
    }
}
