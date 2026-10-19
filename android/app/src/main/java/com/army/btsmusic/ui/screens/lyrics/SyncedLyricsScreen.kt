package com.army.btsmusic.ui.screens.lyrics

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant
import com.army.btsmusic.theme.FanchantGold
import com.army.btsmusic.viewmodel.MusicPlayerViewModel

@Composable
fun SyncedLyricsScreen(
    viewModel: MusicPlayerViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val track by viewModel.currentTrack.collectAsState()
    val positionMs by viewModel.currentPositionMs.collectAsState()
    val currentLyricLine by viewModel.currentLyricLine.collectAsState()
    val lyricsMode by viewModel.lyricsMode.collectAsState()

    val listState = rememberLazyListState()

    val lyrics = track?.lyrics ?: emptyList()
    val activeIndex = lyrics.indexOfLast { it.timestampMs <= positionMs }.coerceAtLeast(0)

    // Auto-scroll to active lyric line
    LaunchedEffect(activeIndex) {
        if (lyrics.isNotEmpty() && activeIndex in lyrics.indices) {
            listState.animateScrollToItem(index = activeIndex, scrollOffset = -220)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = track?.title ?: "Synced Lyrics",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${track?.artist} • Fanchant Practice Mode",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BorahaeLilac
                    )
                }
            }
        }

        // Script Mode Filter Chips: All, Hangul, Romanized, English
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val modes = listOf("ALL" to "All Scripts", "HANGUL" to "한국어 (Hangul)", "ROMANIZED" to "Romanized", "ENGLISH" to "English")
            modes.forEach { (modeKey, label) ->
                val isSelected = lyricsMode == modeKey
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) BorahaePurple else DarkCard)
                        .clickable { viewModel.setLyricsMode(modeKey) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) Color.White else Color.LightGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Synced Lyrics Lazy Column
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 160.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            itemsIndexed(lyrics) { index, line ->
                val isActive = index == activeIndex
                val animatedTextColor by animateColorAsState(
                    targetValue = if (isActive) Color.White else Color(0xFF6B5888),
                    label = "lyricColor"
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.seekTo(line.timestampMs) }
                ) {
                    // Fanchant Badge (If line has fanchant cue)
                    if (line.isFanchant) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isActive) FanchantGold else FanchantGold.copy(alpha = 0.3f))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.RecordVoiceOver,
                                        contentDescription = "Chant",
                                        tint = if (isActive) Color.Black else FanchantGold,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "ARMY FANCHANT: ${line.fanchantText ?: "BTS!"}",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Black,
                                        color = if (isActive) Color.Black else FanchantGold
                                    )
                                }
                            }
                        }
                    }

                    // Hangul Text
                    if (lyricsMode == "ALL" || lyricsMode == "HANGUL") {
                        Text(
                            text = line.hangul,
                            fontSize = if (isActive) 24.sp else 18.sp,
                            fontWeight = if (isActive) FontWeight.ExtraBold else FontWeight.Medium,
                            color = animatedTextColor,
                            lineHeight = if (isActive) 32.sp else 24.sp
                        )
                    }

                    // Romanized Text
                    if (lyricsMode == "ALL" || lyricsMode == "ROMANIZED") {
                        Text(
                            text = line.romanized,
                            fontSize = if (isActive) 16.sp else 14.sp,
                            fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
                            color = if (isActive) BorahaeNeon else DarkOnSurfaceVariant,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    // English Translation Text
                    if (lyricsMode == "ALL" || lyricsMode == "ENGLISH") {
                        Text(
                            text = line.english,
                            fontSize = if (isActive) 14.sp else 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = if (isActive) BorahaeLilac else Color(0xFF554470),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
