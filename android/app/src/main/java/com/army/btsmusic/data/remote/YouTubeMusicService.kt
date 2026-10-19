package com.army.btsmusic.data.remote

import com.army.btsmusic.data.BtsDiscographyCatalog
import com.army.btsmusic.model.Member
import com.army.btsmusic.model.Track
import com.army.btsmusic.model.UserAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class YouTubeMusicService {

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

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

    suspend fun signInWithGoogle(email: String): Result<UserAccount> = withContext(Dispatchers.IO) {
        _currentUserAccount.value = _currentUserAccount.value.copy(isSyncing = true)
        
        val totalCatalog = BtsDiscographyCatalog.sampleTracks.size
        val account = UserAccount(
            isSignedIn = true,
            channelTitle = "ARMY Borahae 💜",
            email = email.ifEmpty { "army.streamer@gmail.com" },
            avatarUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=150",
            youtubeMusicPlaylistCount = 12,
            syncedLikedSongsCount = totalCatalog + 140,
            isSyncing = false,
            lastSyncTime = "Synced just now"
        )
        _currentUserAccount.value = account
        Result.success(account)
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

    suspend fun syncLibrary(): Result<Int> = withContext(Dispatchers.IO) {
        if (!_currentUserAccount.value.isSignedIn) {
            return@withContext Result.failure(IllegalStateException("Not signed in"))
        }
        _currentUserAccount.value = _currentUserAccount.value.copy(isSyncing = true)
        
        val liveTracks = fetchLiveInnerTube("BTS Official Audio")
        val addedCount = liveTracks.size.coerceAtLeast(7)
        
        _currentUserAccount.value = _currentUserAccount.value.copy(
            isSyncing = false,
            youtubeMusicPlaylistCount = _currentUserAccount.value.youtubeMusicPlaylistCount + 1,
            syncedLikedSongsCount = _currentUserAccount.value.syncedLikedSongsCount + addedCount,
            lastSyncTime = "Synced just now"
        )
        Result.success(addedCount)
    }

    suspend fun searchYouTubeMusic(query: String): List<Track> = withContext(Dispatchers.IO) {
        val clean = query.trim()
        if (clean.isEmpty()) {
            return@withContext BtsDiscographyCatalog.sampleTracks
        }

        try {
            val liveResults = fetchLiveInnerTube(clean)
            if (liveResults.isNotEmpty()) {
                return@withContext liveResults
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val lower = clean.lowercase()
        return@withContext BtsDiscographyCatalog.sampleTracks.filter { track ->
            track.title.lowercase().contains(lower) ||
            track.artist.lowercase().contains(lower) ||
            track.album.lowercase().contains(lower) ||
            track.member.stageName.lowercase().contains(lower)
        }
    }

    private fun fetchLiveInnerTube(query: String): List<Track> {
        val targetedQuery = if (
            query.lowercase().contains("bts") || 
            query.lowercase().contains("bangtan") ||
            query.lowercase().contains("jungkook") ||
            query.lowercase().contains("jimin") ||
            query.lowercase().contains("suga") ||
            query.lowercase().contains("agust") ||
            query.lowercase().contains("namjoon") ||
            query.lowercase().contains("seokjin") ||
            query.lowercase().contains("hoseok") ||
            query.lowercase().contains("taehyung")
        ) {
            query
        } else {
            "BTS $query"
        }

        val jsonPayload = JSONObject().apply {
            put("context", JSONObject().apply {
                put("client", JSONObject().apply {
                    put("clientName", "WEB_REMIX")
                    put("clientVersion", "1.20240401.01.00")
                    put("hl", "en")
                    put("gl", "US")
                })
            })
            put("query", targetedQuery)
        }

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonPayload.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://music.youtube.com/youtubei/v1/search")
            .post(body)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
            .build()

        val response = httpClient.newCall(request).execute()
        val responseBody = response.body?.string() ?: return emptyList()
        val root = JSONObject(responseBody)

        val results = mutableListOf<Track>()
        val tabs = root.optJSONObject("contents")
            ?.optJSONObject("tabbedSearchResultsRenderer")
            ?.optJSONArray("tabs") ?: return emptyList()

        if (tabs.length() == 0) return emptyList()

        val tab = tabs.optJSONObject(0)
        val sections = tab?.optJSONObject("tabRenderer")
            ?.optJSONObject("content")
            ?.optJSONObject("sectionListRenderer")
            ?.optJSONArray("contents") ?: return emptyList()

        for (i in 0 until sections.length()) {
            val sec = sections.optJSONObject(i) ?: continue

            // 1. Top Card (musicCardShelfRenderer)
            val card = sec.optJSONObject("musicCardShelfRenderer")
            if (card != null) {
                val titleRuns = card.optJSONObject("title")?.optJSONArray("runs")
                val title = titleRuns?.optJSONObject(0)?.optString("text") ?: ""
                var videoId = titleRuns?.optJSONObject(0)?.optJSONObject("navigationEndpoint")
                    ?.optJSONObject("watchEndpoint")?.optString("videoId")

                if (videoId.isNullOrBlank()) {
                    val buttons = card.optJSONArray("buttons")
                    val btn = buttons?.optJSONObject(0)?.optJSONObject("musicPlayButtonRenderer")
                    videoId = btn?.optJSONObject("playNavigationEndpoint")
                        ?.optJSONObject("watchEndpoint")?.optString("videoId")
                }

                val thumbs = card.optJSONObject("thumbnail")
                    ?.optJSONObject("musicThumbnailRenderer")
                    ?.optJSONObject("thumbnail")
                    ?.optJSONArray("thumbnails")
                val thumbUrl = if (thumbs != null && thumbs.length() > 0) {
                    thumbs.optJSONObject(thumbs.length() - 1)?.optString("url") ?: ""
                } else ""

                if (title.isNotBlank() && !videoId.isNullOrBlank() && videoId.length == 11) {
                    val member = detectMember(title + " BTS")
                    results.add(
                        Track(
                            id = "yt-$videoId",
                            title = title,
                            artist = if (member == Member.OT7) "BTS" else "BTS (${member.stageName})",
                            member = member,
                            album = "YouTube Music Live",
                            durationSeconds = 210,
                            streamUrl = "https://www.youtube.com/watch?v=$videoId",
                            youtubeVideoId = videoId,
                            coverUrl = thumbUrl.ifEmpty { "https://upload.wikimedia.org/wikipedia/en/2/21/BTS_-_You_Never_Walk_Alone.jpg" },
                            releaseYear = 2024
                        )
                    )
                }
            }

            // 2. Shelf items (itemSectionRenderer or musicShelfRenderer)
            val itemList = sec.optJSONObject("itemSectionRenderer")?.optJSONArray("contents")
                ?: sec.optJSONObject("musicShelfRenderer")?.optJSONArray("contents")
                ?: continue

            for (j in 0 until itemList.length()) {
                val item = itemList.optJSONObject(j) ?: continue
                val r = item.optJSONObject("musicResponsiveListItemRenderer") ?: continue

                var videoId = r.optJSONObject("playlistItemData")?.optString("videoId")
                if (videoId.isNullOrBlank()) {
                    val overlay = r.optJSONObject("overlay")
                        ?.optJSONObject("musicItemThumbnailOverlayRenderer")
                    val playBtn = overlay?.optJSONObject("content")
                        ?.optJSONObject("musicPlayButtonRenderer")
                    videoId = playBtn?.optJSONObject("playNavigationEndpoint")
                        ?.optJSONObject("watchEndpoint")?.optString("videoId")
                }

                if (videoId.isNullOrBlank() || videoId.length != 11) continue

                val flexCols = r.optJSONArray("flexColumns") ?: continue
                var title = ""
                var artist = "BTS"
                var album = "BTS Official"

                if (flexCols.length() > 0) {
                    val runs0 = flexCols.optJSONObject(0)
                        ?.optJSONObject("musicResponsiveListItemFlexColumnRenderer")
                        ?.optJSONObject("text")?.optJSONArray("runs")
                    title = runs0?.optJSONObject(0)?.optString("text") ?: ""
                }

                if (flexCols.length() > 1) {
                    val runs1 = flexCols.optJSONObject(1)
                        ?.optJSONObject("musicResponsiveListItemFlexColumnRenderer")
                        ?.optJSONObject("text")?.optJSONArray("runs")
                    if (runs1 != null) {
                        val textList = mutableListOf<String>()
                        for (k in 0 until runs1.length()) {
                            val t = runs1.optJSONObject(k)?.optString("text") ?: ""
                            if (t.isNotBlank() && t != " • " && t != "•") {
                                textList.add(t)
                            }
                        }
                        val meaningful = textList.filter { it != "Song" && it != "Video" }
                        if (meaningful.isNotEmpty()) {
                            artist = meaningful[0]
                        }
                        if (meaningful.size > 1) {
                            album = meaningful[1]
                        }
                    }
                }

                val thumbs = r.optJSONObject("thumbnail")
                    ?.optJSONObject("musicThumbnailRenderer")
                    ?.optJSONObject("thumbnail")
                    ?.optJSONArray("thumbnails")
                val thumbUrl = if (thumbs != null && thumbs.length() > 0) {
                    thumbs.optJSONObject(thumbs.length() - 1)?.optString("url") ?: ""
                } else ""

                if (title.isNotBlank() && results.none { it.youtubeVideoId == videoId }) {
                    val member = detectMember("$title $artist")
                    results.add(
                        Track(
                            id = "yt-$videoId",
                            title = title,
                            artist = artist,
                            member = member,
                            album = album,
                            durationSeconds = 215,
                            streamUrl = "https://www.youtube.com/watch?v=$videoId",
                            youtubeVideoId = videoId,
                            coverUrl = thumbUrl.ifEmpty { "https://upload.wikimedia.org/wikipedia/en/2/21/BTS_-_You_Never_Walk_Alone.jpg" },
                            releaseYear = 2024
                        )
                    )
                }
            }
        }

        return results
    }

    private fun detectMember(text: String): Member {
        val lower = text.lowercase()
        return when {
            lower.contains("jungkook") || lower.contains("jung kook") -> Member.JUNG_KOOK
            lower.contains("jimin") -> Member.JIMIN
            lower.contains("suga") || lower.contains("agust d") || lower.contains("yoongi") -> Member.SUGA
            lower.contains("j-hope") || lower.contains("jhope") || lower.contains("hoseok") -> Member.J_HOPE
            lower.contains("jin") || lower.contains("seokjin") -> Member.JIN
            lower.contains("rm") || lower.contains("namjoon") -> Member.RM
            lower.contains("taehyung") || lower.contains(" v ") || lower.endsWith(" v") -> Member.V
            else -> Member.OT7
        }
    }

    suspend fun resolveStreamUrl(videoId: String): String = withContext(Dispatchers.IO) {
        val catalogTrack = BtsDiscographyCatalog.sampleTracks.firstOrNull { it.youtubeVideoId == videoId }
        return@withContext catalogTrack?.streamUrl ?: "https://www.youtube.com/watch?v=$videoId"
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
