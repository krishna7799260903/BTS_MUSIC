package com.army.btsmusic.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.army.btsmusic.social.ListenTogetherManager
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.ui.components.MiniPlayer
import com.army.btsmusic.ui.screens.equalizer.AudioEngineScreen
import com.army.btsmusic.ui.screens.home.HomeScreen
import com.army.btsmusic.ui.screens.library.LibraryScreen
import com.army.btsmusic.ui.screens.live.BoraLiveScreen
import com.army.btsmusic.ui.screens.lyrics.SyncedLyricsScreen
import com.army.btsmusic.ui.screens.player.FullPlayerScreen
import com.army.btsmusic.ui.screens.social.ListenTogetherScreen
import com.army.btsmusic.viewmodel.ArmyBombViewModel
import com.army.btsmusic.viewmodel.BoraLiveViewModel
import com.army.btsmusic.viewmodel.LibraryViewModel
import com.army.btsmusic.viewmodel.MusicPlayerViewModel

sealed class BtsScreen(val route: String, val label: String, val icon: ImageVector) {
    object Home : BtsScreen("home", "Home", Icons.Default.Home)
    object Library : BtsScreen("library", "Library", Icons.Default.LibraryMusic)
    object BoraLive : BtsScreen("bora_live", "Bora-Live", Icons.Default.LiveTv)
    object Together : BtsScreen("together", "Together", Icons.Default.Group)
    object Equalizer : BtsScreen("equalizer", "Equalizer", Icons.Default.Equalizer)
}

@Composable
fun BtsNavigation(
    playerViewModel: MusicPlayerViewModel,
    libraryViewModel: LibraryViewModel,
    boraLiveViewModel: BoraLiveViewModel,
    armyBombViewModel: ArmyBombViewModel,
    listenTogetherManager: ListenTogetherManager,
    modifier: Modifier = Modifier
) {
    var currentTab by remember { mutableStateOf<BtsScreen>(BtsScreen.Home) }
    var showLyricsScreen by remember { mutableStateOf(false) }

    val currentTrack by playerViewModel.currentTrack.collectAsState()
    val isPlaying by playerViewModel.isPlaying.collectAsState()
    val positionMs by playerViewModel.currentPositionMs.collectAsState()
    val durationMs by playerViewModel.durationMs.collectAsState()
    val frequencies by playerViewModel.frequencies.collectAsState()
    val isFullPlayerExpanded by playerViewModel.isFullPlayerExpanded.collectAsState()

    val navItems = listOf(
        BtsScreen.Home,
        BtsScreen.Library,
        BtsScreen.BoraLive,
        BtsScreen.Together,
        BtsScreen.Equalizer
    )

    Box(modifier = modifier.fillMaxSize().background(DarkBackground)) {
        Scaffold(
            bottomBar = {
                Column {
                    // Floating MiniPlayer docked directly above bottom nav
                    if (currentTrack != null && !isFullPlayerExpanded && !showLyricsScreen) {
                        MiniPlayer(
                            track = currentTrack,
                            isPlaying = isPlaying,
                            currentPositionMs = positionMs,
                            durationMs = durationMs,
                            frequencies = frequencies,
                            onTogglePlayPause = { playerViewModel.togglePlayPause() },
                            onExpand = { playerViewModel.expandFullPlayer() }
                        )
                    }

                    NavigationBar(
                        containerColor = DarkCard,
                        contentColor = BorahaeNeon,
                        tonalElevation = 8.dp
                    ) {
                        navItems.forEach { screen ->
                            val isSelected = currentTab.route == screen.route
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = {
                                    currentTab = screen
                                    showLyricsScreen = false
                                },
                                icon = {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.label,
                                        tint = if (isSelected) BorahaeNeon else Color.Gray
                                    )
                                },
                                label = {
                                    Text(
                                        text = screen.label,
                                        fontSize = 11.sp,
                                        color = if (isSelected) Color.White else Color.Gray
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = BorahaeNeon,
                                    selectedTextColor = Color.White,
                                    indicatorColor = BorahaePurple.copy(alpha = 0.35f)
                                )
                            )
                        }
                    }
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (currentTab) {
                    BtsScreen.Home -> HomeScreen(
                        playerViewModel = playerViewModel,
                        libraryViewModel = libraryViewModel,
                        armyBombViewModel = armyBombViewModel,
                        onNavigateToLive = { currentTab = BtsScreen.BoraLive }
                    )
                    BtsScreen.Library -> LibraryScreen(
                        libraryViewModel = libraryViewModel,
                        playerViewModel = playerViewModel
                    )
                    BtsScreen.BoraLive -> BoraLiveScreen(
                        viewModel = boraLiveViewModel
                    )
                    BtsScreen.Together -> ListenTogetherScreen(
                        listenTogetherManager = listenTogetherManager,
                        playerViewModel = playerViewModel
                    )
                    BtsScreen.Equalizer -> AudioEngineScreen(
                        viewModel = playerViewModel,
                        onBack = { currentTab = BtsScreen.Home }
                    )
                }
            }
        }

        // Full Player Modal Overlay
        AnimatedVisibility(
            visible = isFullPlayerExpanded && !showLyricsScreen,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            FullPlayerScreen(
                viewModel = playerViewModel,
                onNavigateToLyrics = { showLyricsScreen = true },
                onNavigateToEqualizer = {
                    playerViewModel.collapseFullPlayer()
                    currentTab = BtsScreen.Equalizer
                }
            )
        }

        // Karaoke Synced Lyrics Overlay
        AnimatedVisibility(
            visible = showLyricsScreen,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            SyncedLyricsScreen(
                viewModel = playerViewModel,
                onBack = { showLyricsScreen = false }
            )
        }
    }
}
