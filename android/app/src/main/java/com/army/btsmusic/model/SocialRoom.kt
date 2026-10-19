package com.army.btsmusic.model

data class RoomParticipant(
    val id: String,
    val name: String,
    val biasMember: Member,
    val avatarColorHex: Long,
    val isHost: Boolean = false
)

data class SocialRoom(
    val roomCode: String,
    val roomName: String,
    val hostName: String,
    val currentTrack: Track,
    val currentPositionMs: Long = 0L,
    val isPlaying: Boolean = true,
    val participants: List<RoomParticipant> = emptyList()
)
