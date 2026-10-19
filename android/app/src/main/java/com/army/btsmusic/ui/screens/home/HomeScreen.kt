package com.army.btsmusic.ui.screens.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.army.btsmusic.model.Member
import com.army.btsmusic.model.Track
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.BorahaePurpleDeep
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant
import com.army.btsmusic.ui.components.ArmyBombWidget
import com.army.btsmusic.ui.components.TrackListItem
import com.army.btsmusic.viewmodel.ArmyBombViewModel
import com.army.btsmusic.viewmodel.LibraryViewModel
import com.army.btsmusic.viewmodel.MusicPlayerViewModel

@Composable
fun HomeScreen(
    playerViewModel: MusicPlayerViewModel,
    libraryViewModel: LibraryViewModel,
    armyBombViewModel: ArmyBombViewModel,
    onOpenMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentTrack by playerViewModel.currentTrack.collectAsState()
    val isPlaying by playerViewModel.isPlaying.collectAsState()
    val selectedMember by libraryViewModel.selectedMember.collectAsState()
    val tracks by libraryViewModel.filteredTracks.collectAsState()
    val downloadProgressMap by libraryViewModel.downloadProgressMap.collectAsState()

    // Lightstick State
    val connectionState by armyBombViewModel.connectionState.collectAsState()
    val deviceName by armyBombViewModel.connectedDeviceName.collectAsState()
    val lightColorHex by armyBombViewModel.currentLightColor.collectAsState()
    val glowIntensity by armyBombViewModel.lightGlowIntensity.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentPadding = PaddingValues(bottom = 120.dp)
    ) {
        // App Top Bar / Branding
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "BTS MUSIC",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            letterSpacing = (-0.5).sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "💜", fontSize = 20.sp)
                    }
                    Text(
                        text = "Official ARMY Audio-Visual Experience",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BorahaeLilac
                    )
                }

                // Three-Line Hamburger Menu Button
                IconButton(
                    onClick = onOpenMenu,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(DarkCard)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = BorahaeNeon,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }

        // Hero Featured Card (Permission to Dance / HYYH Era)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(BorahaePurpleDeep, Color(0xFF1E1035), Color(0xFF0D0714))
                        )
                    )
                    .clickable {
                        tracks.firstOrNull()?.let { playerViewModel.playTrack(it) }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(BorahaeNeon.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "FEATURED ERA • PROOF ANTHOLOGY",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = BorahaeNeon
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Spring Day & HYYH Classics",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            text = "10+ Years of Memories & Timeless Hits",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DarkOnSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(BorahaePurple),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Play",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Stream Now",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }

                    // Album Art
                    AsyncImage(
                        model = "https://upload.wikimedia.org/wikipedia/en/2/21/BTS_-_You_Never_Walk_Alone.jpg",
                        contentDescription = "Featured Cover",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(110.dp)
                            .shadow(12.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
        }

        // ARMY Bomb BLE & Simulator Widget
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                ArmyBombWidget(
                    connectionState = connectionState,
                    deviceName = deviceName,
                    currentColorHex = lightColorHex,
                    glowIntensity = glowIntensity,
                    colorPresets = armyBombViewModel.colorPresets,
                    onConnectBle = { armyBombViewModel.startScan() },
                    onStartSimulator = { armyBombViewModel.startSimulator() },
                    onDisconnect = { armyBombViewModel.disconnect() },
                    onSelectColor = { armyBombViewModel.selectColor(it) }
                )
            }
        }

        // Member Solo Filters (OT7, RM, Jin, SUGA, j-hope, Jimin, V, Jung Kook)
        item {
            Column(modifier = Modifier.padding(top = 10.dp, bottom = 6.dp)) {
                Text(
                    text = "DISCOGRAPHY & SOLO ERAS",
                    style = MaterialTheme.typography.labelLarge,
                    color = DarkOnSurfaceVariant,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(Member.entries.toTypedArray()) { member ->
                        val isSelected = selectedMember == member
                        val memberColor = Color(member.signatureColorHex)

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    if (isSelected) memberColor else DarkCard
                                )
                                .clickable { libraryViewModel.selectMember(member) }
                                .padding(horizontal = 14.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = member.stageName,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else Color.LightGray
                            )
                        }
                    }
                }
            }
        }

        // Tracklist Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${selectedMember.stageName} Tracks (${tracks.size})",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "YouTube Music Synced",
                    style = MaterialTheme.typography.labelSmall,
                    color = BorahaeLilac
                )
            }
        }

        // Tracks List
        items(tracks, key = { it.id }) { track ->
            TrackListItem(
                track = track,
                isCurrentTrack = currentTrack?.id == track.id,
                isPlaying = isPlaying,
                downloadProgress = downloadProgressMap[track.id],
                onTrackClick = { playerViewModel.playTrack(track) },
                onDownloadClick = { libraryViewModel.toggleDownload(track) },
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
    }
}
