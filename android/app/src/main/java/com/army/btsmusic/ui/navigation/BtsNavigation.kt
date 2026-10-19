package com.army.btsmusic.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.army.btsmusic.social.ListenTogetherManager
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.BorahaePurpleDeep
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant
import com.army.btsmusic.ui.components.AboutDialog
import com.army.btsmusic.ui.components.AccountDialog
import com.army.btsmusic.ui.components.FavSongsDialog
import com.army.btsmusic.ui.components.HelpDialog
import com.army.btsmusic.ui.components.MiniPlayer
import com.army.btsmusic.ui.components.PrivacyPolicyDialog
import com.army.btsmusic.ui.components.TermsDialog
import com.army.btsmusic.ui.screens.equalizer.AudioEngineScreen
import com.army.btsmusic.ui.screens.home.HomeScreen
import com.army.btsmusic.ui.screens.library.LibraryScreen
import com.army.btsmusic.ui.screens.lyrics.SyncedLyricsScreen
import com.army.btsmusic.ui.screens.player.FullPlayerScreen
import com.army.btsmusic.ui.screens.social.ListenTogetherScreen
import com.army.btsmusic.viewmodel.ArmyBombViewModel
import com.army.btsmusic.viewmodel.LibraryViewModel
import com.army.btsmusic.viewmodel.MusicPlayerViewModel
import kotlinx.coroutines.launch

sealed class BtsScreen(val route: String, val label: String, val icon: ImageVector) {
    object Home : BtsScreen("home", "Home", Icons.Default.Home)
    object Library : BtsScreen("library", "Library", Icons.Default.LibraryMusic)
    object Together : BtsScreen("together", "Together", Icons.Default.Group)
    object Equalizer : BtsScreen("equalizer", "Equalizer", Icons.Default.Equalizer)
}

@Composable
fun BtsNavigation(
    playerViewModel: MusicPlayerViewModel,
    libraryViewModel: LibraryViewModel,
    armyBombViewModel: ArmyBombViewModel,
    listenTogetherManager: ListenTogetherManager,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var currentTab by remember { mutableStateOf<BtsScreen>(BtsScreen.Home) }
    var showLyricsScreen by remember { mutableStateOf(false) }

    // Dialog states for the 7 menu items
    var showAccountDialog by remember { mutableStateOf(false) }
    var showFavSongsDialog by remember { mutableStateOf(false) }
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var showTermsDialog by remember { mutableStateOf(false) }
    var showHelpDialog by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }

    val currentTrack by playerViewModel.currentTrack.collectAsState()
    val isPlaying by playerViewModel.isPlaying.collectAsState()
    val positionMs by playerViewModel.currentPositionMs.collectAsState()
    val durationMs by playerViewModel.durationMs.collectAsState()
    val frequencies by playerViewModel.frequencies.collectAsState()
    val isFullPlayerExpanded by playerViewModel.isFullPlayerExpanded.collectAsState()

    val userAccount by libraryViewModel.userAccount.collectAsState()
    val downloadedTracks by libraryViewModel.downloadedTracks.collectAsState()
    val allTracks by libraryViewModel.filteredTracks.collectAsState()
    val favoriteTrackIds by libraryViewModel.favoriteTrackIds.collectAsState()

    val navItems = listOf(
        BtsScreen.Home,
        BtsScreen.Library,
        BtsScreen.Together,
        BtsScreen.Equalizer
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = DarkBackground,
                drawerContentColor = Color.White,
                modifier = Modifier.width(300.dp)
            ) {
                // Drawer Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(BorahaePurpleDeep, Color(0xFF1B0E2C))
                            )
                        )
                        .padding(horizontal = 20.dp, vertical = 26.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "BTS MUSIC", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black, color = Color.White)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "💜", fontSize = 20.sp)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (userAccount.isSignedIn) userAccount.channelTitle else "Official ARMY Sanctuary",
                            fontSize = 12.sp,
                            color = BorahaeNeon,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                HorizontalDivider(color = Color(0xFF261542), thickness = 1.dp)

                Spacer(modifier = Modifier.height(10.dp))

                // 1. ACCOUNT
                NavigationDrawerItem(
                    label = { Text("1. ACCOUNT", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = null, tint = BorahaeNeon) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        showAccountDialog = true
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedTextColor = Color.White),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                )

                // 2. FAV SONGS
                NavigationDrawerItem(
                    label = { Text("2. FAV SONGS", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null, tint = Color(0xFFEC4899)) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        showFavSongsDialog = true
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedTextColor = Color.White),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                )

                // 3. EQUALIZER
                NavigationDrawerItem(
                    label = { Text("3. EQUALIZER", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                    icon = { Icon(Icons.Default.Equalizer, contentDescription = null, tint = BorahaeLilac) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        currentTab = BtsScreen.Equalizer
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedTextColor = Color.White),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                )

                // 4. PRIVACY POLICY
                NavigationDrawerItem(
                    label = { Text("4. PRIVACY POLICY", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                    icon = { Icon(Icons.Default.Security, contentDescription = null, tint = Color(0xFF38BDF8)) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        showPrivacyDialog = true
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedTextColor = Color.White),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                )

                // 5. TERMS AND CONDITIONS
                NavigationDrawerItem(
                    label = { Text("5. TERMS AND CONDITIONS", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                    icon = { Icon(Icons.Default.Description, contentDescription = null, tint = Color(0xFFFBBF24)) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        showTermsDialog = true
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedTextColor = Color.White),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                )

                // 6. HELP
                NavigationDrawerItem(
                    label = { Text("6. HELP", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                    icon = { Icon(Icons.Default.HelpOutline, contentDescription = null, tint = Color(0xFF34D399)) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        showHelpDialog = true
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedTextColor = Color.White),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                )

                // 7. ABOUT
                NavigationDrawerItem(
                    label = { Text("7. ABOUT", fontWeight = FontWeight.SemiBold, fontSize = 13.sp) },
                    icon = { Icon(Icons.Default.Info, contentDescription = null, tint = BorahaeLilac) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        showAboutDialog = true
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedTextColor = Color.White),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                )
            }
        }
    ) {
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
                            onOpenMenu = { scope.launch { drawerState.open() } }
                        )
                        BtsScreen.Library -> LibraryScreen(
                            libraryViewModel = libraryViewModel,
                            playerViewModel = playerViewModel,
                            onOpenMenu = { scope.launch { drawerState.open() } }
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

    // --- Render Dialogs for the 7 Menu Items ---

    // 1. Account Dialog
    if (showAccountDialog) {
        AccountDialog(
            userAccount = userAccount,
            downloadedCount = downloadedTracks.size,
            onSync = { libraryViewModel.syncYouTubeLibrary() },
            onSignIn = { libraryViewModel.signInWithYouTube("army.borahae@gmail.com") },
            onSignOut = { libraryViewModel.signOutYouTube() },
            onDismiss = { showAccountDialog = false }
        )
    }

    // 2. Fav Songs Dialog
    if (showFavSongsDialog) {
        val favTracks = allTracks.filter { favoriteTrackIds.contains(it.id) }
        FavSongsDialog(
            favoriteTracks = favTracks,
            favoriteTrackIds = favoriteTrackIds,
            onTrackClick = { playerViewModel.playTrack(it) },
            onToggleFavorite = { libraryViewModel.toggleFavorite(it) },
            onDismiss = { showFavSongsDialog = false }
        )
    }

    // 4. Privacy Policy Dialog
    if (showPrivacyDialog) {
        PrivacyPolicyDialog(onDismiss = { showPrivacyDialog = false })
    }

    // 5. Terms Dialog
    if (showTermsDialog) {
        TermsDialog(onDismiss = { showTermsDialog = false })
    }

    // 6. Help Dialog
    if (showHelpDialog) {
        HelpDialog(onDismiss = { showHelpDialog = false })
    }

    // 7. About Dialog
    if (showAboutDialog) {
        AboutDialog(onDismiss = { showAboutDialog = false })
    }
}
