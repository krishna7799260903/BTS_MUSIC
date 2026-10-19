package com.army.btsmusic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.army.btsmusic.model.CameraAngle
import com.army.btsmusic.model.ChatMessage
import com.army.btsmusic.model.ConcertStream
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class FloatingHeart(
    val id: String = java.util.UUID.randomUUID().toString(),
    val startXRatio: Float,
    val colorHex: Long = 0xFF8B5CF6
)

class BoraLiveViewModel : ViewModel() {

    private val _concertStream = MutableStateFlow(
        ConcertStream(
            id = "bora-live-hyyh-forever",
            title = "BTS PERMISSION TO DANCE ON STAGE — LIVE",
            subTitle = "Live from Allegiant Stadium • 4K UHD Multi-Angle",
            activeAngle = CameraAngle.MAIN_STAGE,
            viewerCount = 284190,
            totalPurpleHearts = 12480210L,
            streamVideoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        )
    )
    val concertStream: StateFlow<ConcertStream> = _concertStream.asStateFlow()

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(senderName = "Borahae_Kim", avatarColorHex = 0xFF8B5CF6, message = "THE OPENING WAS INSANE OMG 💜💜", timestampFormatted = "19:04"),
            ChatMessage(senderName = "GoldenKookie_97", avatarColorHex = 0xFFF43F5E, message = "Jungkook's high note literally gave me chills!", timestampFormatted = "19:05"),
            ChatMessage(senderName = "MinHollyFan", avatarColorHex = 0xFFFB923C, message = "Yoongi smile on big screen!!", timestampFormatted = "19:05"),
            ChatMessage(senderName = "WinterBearV", avatarColorHex = 0xFF2DD4BF, message = "Singing Spring Day right now 😭🌸", timestampFormatted = "19:06")
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _floatingHearts = MutableStateFlow<List<FloatingHeart>>(emptyList())
    val floatingHearts: StateFlow<List<FloatingHeart>> = _floatingHearts.asStateFlow()

    init {
        // Continuous live chat simulation
        viewModelScope.launch {
            val sampleComments = listOf(
                "KIM NAMJOON KIM SEOKJIN MIN YOONGI JUNG HOSEOK PARK JIMIN KIM TAEHYUNG JEON JUNGKOOK BTS!",
                "ARMY Bomb ocean turning purple!! Look at the lights 💜",
                "Stream to 100M views let's go global chart!",
                "The choreo is so clean after all these years",
                "I'm crying in my room listening to Mikrokosmos",
                "BORAHAE FOREVER 💜💜💜",
                "Switched to Jung Kook cam and the vocals are insane!",
                "Jimin dance break was unreal!!"
            )
            var index = 0
            while (isActive) {
                delay(3000)
                val comment = sampleComments[index % sampleComments.size]
                index++
                val newMessage = ChatMessage(
                    senderName = "ARMY_${(100..999).random()}",
                    avatarColorHex = listOf(0xFF8B5CF6, 0xFFC084FC, 0xFFF472B6, 0xFF38BDF8).random(),
                    message = comment,
                    timestampFormatted = "Live"
                )
                _chatMessages.value = (_chatMessages.value.takeLast(25) + newMessage)
            }
        }
    }

    fun switchCameraAngle(angle: CameraAngle) {
        _concertStream.value = _concertStream.value.copy(activeAngle = angle)
    }

    fun sendChatMessage(text: String) {
        if (text.isBlank()) return
        val userMsg = ChatMessage(
            senderName = "You (ARMY)",
            avatarColorHex = 0xFFC084FC,
            message = text.trim(),
            timestampFormatted = "Just now",
            isSuperCheer = text.contains("💜")
        )
        _chatMessages.value = _chatMessages.value + userMsg
        triggerHeartCheer()
    }

    fun triggerHeartCheer() {
        val stream = _concertStream.value
        _concertStream.value = stream.copy(
            totalPurpleHearts = stream.totalPurpleHearts + 1
        )

        val colors = listOf(0xFF8B5CF6, 0xFFC084FC, 0xFFE879F9, 0xFFF472B6, 0xFFFFFFFF)
        val heart = FloatingHeart(
            startXRatio = (20..80).random() / 100f,
            colorHex = colors.random()
        )
        _floatingHearts.value = _floatingHearts.value + heart

        // Remove heart after animation duration
        viewModelScope.launch {
            delay(2000)
            _floatingHearts.value = _floatingHearts.value.filter { it.id != heart.id }
        }
    }
}
