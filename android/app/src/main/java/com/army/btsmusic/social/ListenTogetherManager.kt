package com.army.btsmusic.social

import com.army.btsmusic.data.BtsDiscographyCatalog
import com.army.btsmusic.model.Member
import com.army.btsmusic.model.RoomParticipant
import com.army.btsmusic.model.SocialRoom
import com.army.btsmusic.model.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListenTogetherManager {

    private val _activeRoom = MutableStateFlow<SocialRoom?>(null)
    val activeRoom: StateFlow<SocialRoom?> = _activeRoom.asStateFlow()

    fun createRoom(hostName: String, initialTrack: Track): SocialRoom {
        val randomDigits = (100..999).random()
        val roomCode = "ARMY-$randomDigits"
        val host = RoomParticipant(
            id = "host-1",
            name = hostName.ifEmpty { "ARMY Leader" },
            biasMember = Member.OT7,
            avatarColorHex = 0xFF8B5CF6,
            isHost = true
        )
        val guest1 = RoomParticipant(
            id = "guest-1",
            name = "PurpleGalaxy97",
            biasMember = Member.JUNG_KOOK,
            avatarColorHex = 0xFFF43F5E
        )
        val guest2 = RoomParticipant(
            id = "guest-2",
            name = "Whalien_52",
            biasMember = Member.JIMIN,
            avatarColorHex = 0xFFA78BFA
        )

        val room = SocialRoom(
            roomCode = roomCode,
            roomName = "💜 ARMY Global Stream Room #$randomDigits",
            hostName = host.name,
            currentTrack = initialTrack,
            currentPositionMs = 0L,
            isPlaying = true,
            participants = listOf(host, guest1, guest2)
        )
        _activeRoom.value = room
        return room
    }

    fun joinRoom(code: String, userName: String, bias: Member): Result<SocialRoom> {
        val currentTrack = BtsDiscographyCatalog.sampleTracks.first()
        val newParticipant = RoomParticipant(
            id = "user-${System.currentTimeMillis()}",
            name = userName.ifEmpty { "ARMY Bestie" },
            biasMember = bias,
            avatarColorHex = bias.signatureColorHex
        )
        val dummyHost = RoomParticipant(
            id = "host-99",
            name = "BorahaeStreamTeam",
            biasMember = Member.OT7,
            avatarColorHex = 0xFF8B5CF6,
            isHost = true
        )

        val room = SocialRoom(
            roomCode = code.uppercase(),
            roomName = "💜 Live ARMY Listening Room (${code.uppercase()})",
            hostName = "BorahaeStreamTeam",
            currentTrack = currentTrack,
            currentPositionMs = 34000L,
            isPlaying = true,
            participants = listOf(dummyHost, newParticipant)
        )
        _activeRoom.value = room
        return Result.success(room)
    }

    fun leaveRoom() {
        _activeRoom.value = null
    }

    fun syncTrack(track: Track, positionMs: Long, isPlaying: Boolean) {
        _activeRoom.value?.let { room ->
            _activeRoom.value = room.copy(
                currentTrack = track,
                currentPositionMs = positionMs,
                isPlaying = isPlaying
            )
        }
    }
}
