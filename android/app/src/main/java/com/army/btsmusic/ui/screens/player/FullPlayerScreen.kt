package com.army.btsmusic.ui.screens.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lyrics
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.RepeatOne
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.Player
import coil.compose.AsyncImage
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant
import com.army.btsmusic.theme.FanchantGold
import com.army.btsmusic.ui.components.AudioVisualizer
import com.army.btsmusic.viewmodel.MusicPlayerViewModel

@Composable
fun FullPlayerScreen(
    viewModel: MusicPlayerViewModel,
    onNavigateToLyrics: () -> Unit,
    onNavigateToEqualizer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val track by viewModel.currentTrack.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val positionMs by viewModel.currentPositionMs.collectAsState()
    val durationMs by viewModel.durationMs.collectAsState()
    val isShuffleOn by viewModel.isShuffleOn.collectAsState()
    val repeatMode by viewModel.repeatMode.collectAsState()
    val frequencies by viewModel.frequencies.collectAsState()
    val currentLyricLine by viewModel.currentLyricLine.collectAsState()

    if (track == null) return

    val currentSec = (positionMs / 1000).toInt()
    val totalSec = if (durationMs > 0) (durationMs / 1000).toInt() else track!!.durationSeconds
    val progressFraction = if (totalSec > 0) (currentSec.toFloat() / totalSec.toFloat()).coerceIn(0f, 1f) else 0f

    var isSeeking by remember { androidx.compose.runtime.mutableStateOf(false) }
    var seekPosition by remember { mutableFloatStateOf(0f) }

    val memberColor = Color(track!!.member.signatureColorHex)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Glowing Ambient Backlight behind artwork
        Box(
            modifier = Modifier
                .size(340.dp)
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        listOf(memberColor.copy(alpha = 0.35f), Color.Transparent)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Bar: Collapse handle & Title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { viewModel.collapseFullPlayer() }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Collapse",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "PLAYING FROM BTS DISCOGRAPHY",
                        style = MaterialTheme.typography.labelSmall,
                        color = BorahaeLilac,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = track!!.album,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                IconButton(onClick = onNavigateToEqualizer) {
                    Icon(
                        imageVector = Icons.Default.Equalizer,
                        contentDescription = "Audio Engine Equalizer",
                        tint = BorahaeNeon,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            // High-Resolution Cover Art
            Box(
                modifier = Modifier
                    .size(280.dp)
                    .shadow(24.dp, RoundedCornerShape(24.dp), spotColor = memberColor)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF1E1035)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = track!!.coverUrl,
                    contentDescription = track!!.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Track Information & Member Badge
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = track!!.title,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(memberColor.copy(alpha = 0.3f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = track!!.member.stageName,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = memberColor
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = track!!.artist,
                        style = MaterialTheme.typography.titleMedium,
                        color = DarkOnSurfaceVariant
                    )
                }
            }

            // Animated Waveform Audio Visualizer
            AudioVisualizer(
                frequencies = frequencies,
                maxHeight = 36.dp,
                barWidth = 4.dp,
                barSpacing = 3.dp
            )

            // Live Synced Lyrics Highlight Preview
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(DarkCard)
                    .clickable { onNavigateToLyrics() }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Lyrics,
                                contentDescription = null,
                                tint = BorahaeNeon,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "LIVE SYNCED LYRICS",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = BorahaeNeon
                            )

                            if (currentLyricLine?.isFanchant == true) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(FanchantGold.copy(alpha = 0.25f))
                                        .padding(horizontal = 6.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = "FANCHANT: ${currentLyricLine?.fanchantText ?: "ARMY!"}",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Black,
                                        color = FanchantGold
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = currentLyricLine?.hangul ?: "Tap to open synced lyrics...",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        if (currentLyricLine != null) {
                            Text(
                                text = currentLyricLine!!.english,
                                style = MaterialTheme.typography.bodyMedium,
                                color = DarkOnSurfaceVariant
                            )
                        }
                    }

                    Text(
                        text = "Open >",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BorahaeLilac
                    )
                }
            }

            // Progress Slider & Timestamps
            Column(modifier = Modifier.fillMaxWidth()) {
                Slider(
                    value = if (isSeeking) seekPosition else progressFraction,
                    onValueChange = {
                        isSeeking = true
                        seekPosition = it
                    },
                    onValueChangeFinished = {
                        isSeeking = false
                        viewModel.seekTo((seekPosition * totalSec * 1000).toLong())
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = BorahaeNeon,
                        inactiveTrackColor = Color(0xFF2E1A47)
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val displaySec = if (isSeeking) (seekPosition * totalSec).toInt() else currentSec
                    Text(
                        text = "%d:%02d".format(displaySec / 60, displaySec % 60),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.LightGray
                    )
                    Text(
                        text = "%d:%02d".format(totalSec / 60, totalSec % 60),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.LightGray
                    )
                }
            }

            // Playback Controls Row: Shuffle, Prev, Play/Pause, Next, Repeat
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Shuffle
                IconButton(onClick = { viewModel.toggleShuffle() }) {
                    Icon(
                        imageVector = Icons.Default.Shuffle,
                        contentDescription = "Shuffle",
                        tint = if (isShuffleOn) BorahaeNeon else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Previous
                IconButton(onClick = { viewModel.seekTo(0) }) {
                    Icon(
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = "Previous",
                        tint = Color.White,
                        modifier = Modifier.size(34.dp)
                    )
                }

                // Giant Borahae Play / Pause Button
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .shadow(16.dp, CircleShape, spotColor = BorahaePurple)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(listOf(BorahaePurple, BorahaeNeon))
                        )
                        .clickable { viewModel.togglePlayPause() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                }

                // Next
                IconButton(onClick = { viewModel.seekTo(0) }) {
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = "Next",
                        tint = Color.White,
                        modifier = Modifier.size(34.dp)
                    )
                }

                // Repeat
                IconButton(onClick = { viewModel.toggleRepeat() }) {
                    Icon(
                        imageVector = when (repeatMode) {
                            Player.REPEAT_MODE_ONE -> Icons.Default.RepeatOne
                            else -> Icons.Default.Repeat
                        },
                        contentDescription = "Repeat",
                        tint = when (repeatMode) {
                            Player.REPEAT_MODE_OFF -> Color.Gray
                            else -> BorahaeNeon
                        },
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
