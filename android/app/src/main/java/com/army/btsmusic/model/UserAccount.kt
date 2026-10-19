package com.army.btsmusic.model

data class UserAccount(
    val isSignedIn: Boolean = false,
    val channelTitle: String = "ARMY Guest",
    val email: String = "army@bts.weverse",
    val avatarUrl: String? = null,
    val youtubeMusicPlaylistCount: Int = 12,
    val syncedLikedSongsCount: Int = 248,
    val isSyncing: Boolean = false,
    val lastSyncTime: String? = "Just now"
)
