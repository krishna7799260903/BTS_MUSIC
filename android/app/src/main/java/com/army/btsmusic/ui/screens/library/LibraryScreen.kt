package com.army.btsmusic.ui.screens.library

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant
import com.army.btsmusic.ui.components.TrackListItem
import com.army.btsmusic.viewmodel.LibraryViewModel
import com.army.btsmusic.viewmodel.MusicPlayerViewModel

@Composable
fun LibraryScreen(
    libraryViewModel: LibraryViewModel,
    playerViewModel: MusicPlayerViewModel,
    onOpenMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    val allTracks by libraryViewModel.filteredTracks.collectAsState()
    val downloadedTracks by libraryViewModel.downloadedTracks.collectAsState()
    val currentTrack by playerViewModel.currentTrack.collectAsState()
    val isPlaying by playerViewModel.isPlaying.collectAsState()
    val downloadProgressMap by libraryViewModel.downloadProgressMap.collectAsState()
    val searchQuery by libraryViewModel.searchQuery.collectAsState()
    val searchResults by libraryViewModel.searchResults.collectAsState()

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("All Songs", "Offline Downloads (${downloadedTracks.size})", "Search YT Music")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentPadding = PaddingValues(bottom = 120.dp)
    ) {
        // Screen Title with Hamburger Menu Button
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onOpenMenu) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = BorahaeNeon,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Your Library & Downloads",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "BTS & Solo Members Offline Storage",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BorahaeLilac
                    )
                }
            }
        }

        // Tab Selector Row
        item {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                contentColor = BorahaeNeon,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = BorahaeNeon
                    )
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                fontSize = 12.sp,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTabIndex == index) Color.White else Color.Gray
                            )
                        }
                    )
                }
            }
        }

        // Search bar (if search tab selected)
        if (selectedTabIndex == 2) {
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { libraryViewModel.onSearchQueryChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("Search songs, solos, or albums on YouTube Music...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = BorahaeNeon) },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BorahaePurple,
                        unfocusedBorderColor = Color(0xFF261542),
                        focusedContainerColor = DarkCard,
                        unfocusedContainerColor = DarkCard
                    )
                )
            }

            val displayList = if (searchQuery.isBlank()) allTracks else searchResults
            items(displayList, key = { "search_${it.id}" }) { track ->
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
        } else if (selectedTabIndex == 1) {
            // Offline Downloads Tab
            if (downloadedTracks.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.DownloadDone,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(54.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "No Offline Downloads Yet",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Tap the download icon on any BTS track to listen offline without internet",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DarkOnSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 32.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            } else {
                items(downloadedTracks, key = { "offline_${it.id}" }) { track ->
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
        } else {
            // All Songs Tab
            items(allTracks, key = { "all_${it.id}" }) { track ->
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
