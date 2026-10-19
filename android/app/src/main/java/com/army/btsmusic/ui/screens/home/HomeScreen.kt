package com.army.btsmusic.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.army.btsmusic.data.BtsDiscographyCatalog
import com.army.btsmusic.model.Member
import com.army.btsmusic.model.Track
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.BorahaePurpleDeep
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant
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

        // Top Albums of BTS (2x2 Form in place of previous boxed section)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "TOP BTS ALBUMS",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            letterSpacing = 0.8.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(BorahaeNeon.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "2×2 BESTSELLERS",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = BorahaeNeon
                            )
                        }
                    }
                    Text(
                        text = "Tap to Stream",
                        fontSize = 11.sp,
                        color = BorahaeLilac
                    )
                }

                // Row 1 of 2x2 Grid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TopAlbumCard(
                        album = topBtsAlbums[0],
                        isPlaying = currentTrack?.album?.equals(topBtsAlbums[0].albumMatch, ignoreCase = true) == true && isPlaying,
                        onClick = {
                            libraryViewModel.selectMember(Member.OT7)
                            val targetTrack = BtsDiscographyCatalog.sampleTracks.find {
                                it.album.equals(topBtsAlbums[0].albumMatch, ignoreCase = true)
                            } ?: BtsDiscographyCatalog.sampleTracks.find {
                                it.id == topBtsAlbums[0].leadTrackId
                            }
                            targetTrack?.let { playerViewModel.playTrack(it) }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    TopAlbumCard(
                        album = topBtsAlbums[1],
                        isPlaying = currentTrack?.album?.equals(topBtsAlbums[1].albumMatch, ignoreCase = true) == true && isPlaying,
                        onClick = {
                            libraryViewModel.selectMember(Member.OT7)
                            val targetTrack = BtsDiscographyCatalog.sampleTracks.find {
                                it.album.equals(topBtsAlbums[1].albumMatch, ignoreCase = true)
                            } ?: BtsDiscographyCatalog.sampleTracks.find {
                                it.id == topBtsAlbums[1].leadTrackId
                            }
                            targetTrack?.let { playerViewModel.playTrack(it) }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Row 2 of 2x2 Grid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TopAlbumCard(
                        album = topBtsAlbums[2],
                        isPlaying = currentTrack?.album?.equals(topBtsAlbums[2].albumMatch, ignoreCase = true) == true && isPlaying,
                        onClick = {
                            libraryViewModel.selectMember(Member.OT7)
                            val targetTrack = BtsDiscographyCatalog.sampleTracks.find {
                                it.album.equals(topBtsAlbums[2].albumMatch, ignoreCase = true)
                            } ?: BtsDiscographyCatalog.sampleTracks.find {
                                it.id == topBtsAlbums[2].leadTrackId
                            }
                            targetTrack?.let { playerViewModel.playTrack(it) }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    TopAlbumCard(
                        album = topBtsAlbums[3],
                        isPlaying = currentTrack?.album?.equals(topBtsAlbums[3].albumMatch, ignoreCase = true) == true && isPlaying,
                        onClick = {
                            libraryViewModel.selectMember(Member.OT7)
                            val targetTrack = BtsDiscographyCatalog.sampleTracks.find {
                                it.album.equals(topBtsAlbums[3].albumMatch, ignoreCase = true)
                            } ?: BtsDiscographyCatalog.sampleTracks.find {
                                it.id == topBtsAlbums[3].leadTrackId
                            }
                            targetTrack?.let { playerViewModel.playTrack(it) }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
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

data class TopBtsAlbum(
    val id: String,
    val title: String,
    val subtitle: String,
    val albumMatch: String,
    val leadTrackId: String,
    val coverUrl: String
)

private val topBtsAlbums = listOf(
    TopBtsAlbum(
        id = "mots7",
        title = "Map of the Soul: 7",
        subtitle = "2020 • 4.8M+ Sold",
        albumMatch = "MAP OF THE SOUL : 7",
        leadTrackId = "bts-on",
        coverUrl = "https://upload.wikimedia.org/wikipedia/en/2/21/BTS_-_Map_of_the_Soul_7.png"
    ),
    TopBtsAlbum(
        id = "ly_answer",
        title = "Love Yourself 結 'Answer'",
        subtitle = "2018 • Billboard 200 #1",
        albumMatch = "LOVE YOURSELF 結 'Answer'",
        leadTrackId = "bts-idol",
        coverUrl = "https://upload.wikimedia.org/wikipedia/en/9/9e/BTS_-_Love_Yourself_Answer_album_cover.jpg"
    ),
    TopBtsAlbum(
        id = "wings",
        title = "Wings",
        subtitle = "2016 • Landmark Era",
        albumMatch = "WINGS",
        leadTrackId = "bts-blood-sweat-tears",
        coverUrl = "https://upload.wikimedia.org/wikipedia/en/9/90/BTS_Wings_cover.jpg"
    ),
    TopBtsAlbum(
        id = "proof",
        title = "Proof",
        subtitle = "2022 • Anthology",
        albumMatch = "Proof",
        leadTrackId = "bts-run-bts",
        coverUrl = "https://upload.wikimedia.org/wikipedia/en/a/a2/BTS_-_Proof_album_cover.png"
    )
)

@Composable
private fun TopAlbumCard(
    album: TopBtsAlbum,
    isPlaying: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        if (isPlaying) Color(0xFF381B5E) else Color(0xFF1E1130),
                        Color(0xFF0F0719)
                    )
                )
            )
            .border(
                width = if (isPlaying) 1.5.dp else 1.dp,
                color = if (isPlaying) BorahaeNeon else BorahaePurple.copy(alpha = 0.35f),
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(onClick = onClick)
            .padding(10.dp)
    ) {
        Column {
            // Album Artwork with Floating Play Badge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF281440))
            ) {
                AsyncImage(
                    model = album.coverUrl,
                    contentDescription = album.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Play / Playing badge
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(6.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(if (isPlaying) BorahaeNeon else BorahaePurple),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.ElectricBolt else Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = if (isPlaying) Color.Black else Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = album.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Year & Sales stats
            Text(
                text = album.subtitle,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = if (isPlaying) BorahaeNeon else DarkOnSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

