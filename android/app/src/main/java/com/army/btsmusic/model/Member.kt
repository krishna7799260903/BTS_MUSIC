package com.army.btsmusic.model

enum class Member(
    val stageName: String,
    val koreanName: String,
    val fullName: String,
    val role: String,
    val signatureColorHex: Long,
    val soloDebutAlbum: String
) {
    OT7(
        stageName = "BTS",
        koreanName = "방탄소년단",
        fullName = "Bangtan Sonyeondan",
        role = "Beyond The Scene (All 7 Members)",
        signatureColorHex = 0xFF8B5CF6,
        soloDebutAlbum = "Proof / Anthology"
    ),
    RM(
        stageName = "RM",
        koreanName = "김남준",
        fullName = "Kim Namjoon",
        role = "Leader & Main Rapper",
        signatureColorHex = 0xFF38BDF8, // Indigo Blue
        soloDebutAlbum = "Indigo / Right Place, Wrong Person"
    ),
    JIN(
        stageName = "Jin",
        koreanName = "김석진",
        fullName = "Kim Seokjin",
        role = "Sub Vocalist & Visual",
        signatureColorHex = 0xFFF472B6, // Warm Pink
        soloDebutAlbum = "Happy / The Astronaut"
    ),
    SUGA(
        stageName = "SUGA / Agust D",
        koreanName = "민윤기",
        fullName = "Min Yoongi",
        role = "Lead Rapper & Music Producer",
        signatureColorHex = 0xFFFB923C, // D-Day Ember Orange
        soloDebutAlbum = "D-DAY / Agust D"
    ),
    J_HOPE(
        stageName = "j-hope",
        koreanName = "정호석",
        fullName = "Jung Hoseok",
        role = "Main Dancer, Sub Rapper & Producer",
        signatureColorHex = 0xFFFACC15, // Hope Sunflower Gold
        soloDebutAlbum = "Jack In The Box / HOPE ON THE STREET"
    ),
    JIMIN(
        stageName = "Jimin",
        koreanName = "박지민",
        fullName = "Park Jimin",
        role = "Main Dancer & Lead Vocalist",
        signatureColorHex = 0xFFA78BFA, // Serendipity Lavender
        soloDebutAlbum = "FACE / MUSE"
    ),
    V(
        stageName = "V",
        koreanName = "김태형",
        fullName = "Kim Taehyung",
        role = "Lead Dancer, Sub Vocalist & Visual",
        signatureColorHex = 0xFF2DD4BF, // Winter Bear Emerald / Jazz Teal
        soloDebutAlbum = "Layover / Fri(end)s"
    ),
    JUNG_KOOK(
        stageName = "Jung Kook",
        koreanName = "전정국",
        fullName = "Jeon Jungkook",
        role = "Main Vocalist, Lead Dancer & Golden Maknae",
        signatureColorHex = 0xFFF43F5E, // Golden Crimson Red
        soloDebutAlbum = "GOLDEN / Seven"
    );

    companion object {
        fun fromString(name: String): Member {
            return entries.firstOrNull { 
                it.stageName.equals(name, ignoreCase = true) || 
                it.name.equals(name, ignoreCase = true) 
            } ?: OT7
        }
    }
}
