package com.army.btsmusic.ui.screens.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
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
import androidx.compose.material.icons.filled.Schedule
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
                                .background(Color(0xFF331757))
                                .padding(horizontal = 7.dp, vertical = 2.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "2×2",
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFD8B4FE),
                                    lineHeight = 9.sp
                                )
                                Text(
                                    text = "BESTSELLERS",
                                    fontSize = 7.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFD8B4FE),
                                    lineHeight = 8.sp
                                )
                            }
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

        if (selectedMember == Member.OT7) {
            // ==========================================
            // QUICK PICKS SECTION (From Design Mockup)
            // ==========================================
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    // Quick Picks Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "QUICK PICKS",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    letterSpacing = 0.5.sp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0xFF331757))
                                        .padding(horizontal = 7.dp, vertical = 2.dp)
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = "ARMY",
                                            fontSize = 8.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFD8B4FE),
                                            lineHeight = 9.sp
                                        )
                                        Text(
                                            text = "PICKS",
                                            fontSize = 7.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFD8B4FE),
                                            lineHeight = 8.sp
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Curated for you • Start a radio",
                                fontSize = 11.sp,
                                color = Color(0xFF9CA3AF)
                            )
                        }

                        // Play All Button
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    val firstTrack = BtsDiscographyCatalog.sampleTracks.find { it.id == quickPicks.first().trackId }
                                    firstTrack?.let { playerViewModel.playTrack(it) }
                                }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Play All",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF8B3CFC)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "▶",
                                fontSize = 10.sp,
                                color = Color(0xFF8B3CFC)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // 4 Quick Pick Cards
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        quickPicks.forEach { item ->
                            val targetTrack = BtsDiscographyCatalog.sampleTracks.find { it.id == item.trackId }
                            val isItemPlaying = currentTrack?.id == item.trackId && isPlaying
                            QuickPickCard(
                                item = item,
                                isPlaying = isItemPlaying,
                                onClick = {
                                    targetTrack?.let { playerViewModel.playTrack(it) }
                                }
                            )
                        }
                    }
                }
            }

            // ==========================================
            // RECENTLY LISTENED SECTION (From Design Mockup)
            // ==========================================
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    // Recently Listened Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "RECENTLY LISTENED",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                letterSpacing = 0.5.sp
                            )
                        }

                        Text(
                            text = "See all",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF8B3CFC),
                            modifier = Modifier.clickable { }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 3 Recently Listened Cards in a Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        recentlyListenedItems.forEach { item ->
                            val targetTrack = BtsDiscographyCatalog.sampleTracks.find { it.id == item.trackId }
                            val isItemPlaying = currentTrack?.id == item.trackId && isPlaying
                            RecentlyListenedCard(
                                item = item,
                                isPlaying = isItemPlaying,
                                onClick = {
                                    targetTrack?.let { playerViewModel.playTrack(it) }
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        } else {
            // Solo Member Tracks Header
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

            // Solo Member Tracks List
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
    // Outer Frame Box
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF090312))
            .border(
                width = if (isPlaying) 1.5.dp else 1.dp,
                color = if (isPlaying) BorahaeNeon else Color(0xFF25113C),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .padding(6.dp)
    ) {
        // Inner Container Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(172.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF0F061C))
                .border(
                    width = 1.dp,
                    color = Color(0xFF2B1445),
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Center Emblem Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    when (album.id) {
                        "mots7" -> {
                            Text(
                                text = "7",
                                fontSize = 66.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFF331757),
                                textAlign = TextAlign.Center
                            )
                        }
                        "ly_answer" -> {
                            Canvas(modifier = Modifier.size(52.dp)) {
                                val w = size.width
                                val h = size.height
                                val path = Path().apply {
                                    moveTo(w * 0.5f, h * 0.85f)
                                    cubicTo(w * 0.12f, h * 0.55f, w * 0.02f, h * 0.22f, w * 0.26f, h * 0.10f)
                                    cubicTo(w * 0.40f, h * 0.02f, w * 0.5f, h * 0.20f, w * 0.5f, h * 0.26f)
                                    cubicTo(w * 0.5f, h * 0.20f, w * 0.60f, h * 0.02f, w * 0.74f, h * 0.10f)
                                    cubicTo(w * 0.98f, h * 0.22f, w * 0.88f, h * 0.55f, w * 0.5f, h * 0.85f)
                                    close()
                                }
                                drawPath(
                                    path = path,
                                    color = Color(0xFF331757),
                                    style = Stroke(
                                        width = 3.5.dp.toPx(),
                                        cap = StrokeCap.Round,
                                        join = StrokeJoin.Round
                                    )
                                )
                            }
                        }
                        "wings" -> {
                            Canvas(modifier = Modifier.size(46.dp)) {
                                val r = 8.5.dp.toPx()
                                val strokeW = 2.dp.toPx()
                                val color = Color(0xFF331757)
                                val left = size.width * 0.30f
                                val right = size.width * 0.70f
                                val top = size.height * 0.30f
                                val bottom = size.height * 0.70f

                                // Top-Left (outlined)
                                drawCircle(color = color, radius = r, center = Offset(left, top), style = Stroke(strokeW))
                                // Top-Right (filled)
                                drawCircle(color = color, radius = r, center = Offset(right, top))
                                // Bottom-Left (filled)
                                drawCircle(color = color, radius = r, center = Offset(left, bottom))
                                // Bottom-Right (outlined)
                                drawCircle(color = color, radius = r, center = Offset(right, bottom), style = Stroke(strokeW))
                            }
                        }
                        "proof" -> {
                            Canvas(modifier = Modifier.size(52.dp)) {
                                val strokeW = 2.dp.toPx()
                                val color = Color(0xFF331757)

                                val outerW = 30.dp.toPx()
                                val outerH = 44.dp.toPx()
                                drawRoundRect(
                                    color = color,
                                    topLeft = Offset((size.width - outerW) / 2f, (size.height - outerH) / 2f),
                                    size = Size(outerW, outerH),
                                    cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                                    style = Stroke(strokeW)
                                )

                                val innerW = 16.dp.toPx()
                                val innerH = 28.dp.toPx()
                                drawRoundRect(
                                    color = color,
                                    topLeft = Offset((size.width - innerW) / 2f, (size.height - innerH) / 2f),
                                    size = Size(innerW, innerH),
                                    cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx()),
                                    style = Stroke(strokeW)
                                )
                            }
                        }
                    }
                }

                // Bottom Content Row (Title, Subtitle & Circular Purple Play Button)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = album.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = album.subtitle,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFB0A2C8),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    // Solid Purple Play Button
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF8B3CFC)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

data class QuickPickItem(
    val id: String,
    val badge: String,
    val title: String,
    val subtitle: String,
    val trackId: String
)

private val quickPicks = listOf(
    QuickPickItem(
        id = "bst",
        badge = "BST",
        title = "Blood Sweat & Tears",
        subtitle = "BTS • 3:37 • 520M+ streams",
        trackId = "bts-blood-sweat-tears"
    ),
    QuickPickItem(
        id = "spring_day",
        badge = "봄날",
        title = "Spring Day (봄날)",
        subtitle = "BTS • 4:34 • Queen of Melon",
        trackId = "bts-spring-day"
    ),
    QuickPickItem(
        id = "fake_love",
        badge = "FL",
        title = "Fake Love",
        subtitle = "BTS • 4:02 • Love Yourself",
        trackId = "bts-fake-love"
    ),
    QuickPickItem(
        id = "dynamite",
        badge = "DYN",
        title = "Dynamite",
        subtitle = "BTS • 3:19 • Grammy Nominee",
        trackId = "bts-dynamite"
    )
)

data class RecentlyListenedItem(
    val id: String,
    val emblemText: String,
    val timeAgo: String,
    val title: String,
    val subtitle: String,
    val trackId: String
)

private val recentlyListenedItems = listOf(
    RecentlyListenedItem(
        id = "butter",
        emblemText = "BUTTER",
        timeAgo = "12m ago",
        title = "Butter",
        subtitle = "Single • BTS",
        trackId = "bts-butter"
    ),
    RecentlyListenedItem(
        id = "life_goes_on",
        emblemText = "BE",
        timeAgo = "2h ago",
        title = "Life Goes On",
        subtitle = "BE • BTS",
        trackId = "bts-life-goes-on"
    ),
    RecentlyListenedItem(
        id = "mic_drop",
        emblemText = "MIC DROP",
        timeAgo = "5h ago",
        title = "MIC Drop",
        subtitle = "Steve Aoki Remix",
        trackId = "bts-mic-drop"
    )
)

@Composable
private fun QuickPickCard(
    item: QuickPickItem,
    isPlaying: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF0F061C))
            .border(
                width = 1.dp,
                color = if (isPlaying) BorahaeNeon else Color(0xFF26123E),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 9.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Square Icon Badge Box
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF241142)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.badge,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC084FC)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = item.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.subtitle,
                        fontSize = 11.sp,
                        color = Color(0xFF9CA3AF),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Play Button
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(if (isPlaying) Color(0xFF8B3CFC) else Color(0xFF241142)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = if (isPlaying) Color.White else Color(0xFFC084FC),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun RecentlyListenedCard(
    item: RecentlyListenedItem,
    isPlaying: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    ) {
        // Upper Artwork Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(106.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF130726))
                .border(
                    width = 1.dp,
                    color = if (isPlaying) BorahaeNeon else Color(0xFF2E154E),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.emblemText,
                fontSize = if (item.emblemText.length > 5) 13.sp else 16.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFFD8B4FE),
                textAlign = TextAlign.Center
            )

            // Time Ago Badge in bottom right
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF090312).copy(alpha = 0.85f))
                    .padding(horizontal = 5.dp, vertical = 2.dp)
            ) {
                Text(
                    text = item.timeAgo,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFD8B4FE)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = item.title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = item.subtitle,
            fontSize = 10.sp,
            color = Color(0xFF9CA3AF),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}



