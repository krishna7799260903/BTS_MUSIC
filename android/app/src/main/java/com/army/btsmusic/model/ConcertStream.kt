package com.army.btsmusic.model

enum class CameraAngle(val label: String, val description: String) {
    MAIN_STAGE("Main Stage", "Full stadium ultra-wide camera"),
    JUNGKOOK_CAM("Jung Kook Cam", "Center stage vocal & choreo angle"),
    V_CAM("V Cam", "Charisma & visual member focus"),
    JIMIN_CAM("Jimin Cam", "Fluid dance & high notes view"),
    RAPLINE_CAM("Rap Line Cam", "RM, SUGA & j-hope cypher cam"),
    ARMY_BOMB_OCEAN("ARMY Ocean", "Crowd & lightstick panoramic view")
}

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val senderName: String,
    val avatarColorHex: Long,
    val message: String,
    val timestampFormatted: String,
    val isSuperCheer: Boolean = false
)

data class ConcertStream(
    val id: String,
    val title: String,
    val subTitle: String,
    val activeAngle: CameraAngle = CameraAngle.MAIN_STAGE,
    val viewerCount: Int = 184520,
    val totalPurpleHearts: Long = 9482100L,
    val streamVideoUrl: String,
    val isLive: Boolean = true
)
