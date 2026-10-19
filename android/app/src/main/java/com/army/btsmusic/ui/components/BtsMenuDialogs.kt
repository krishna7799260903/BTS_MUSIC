package com.army.btsmusic.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.army.btsmusic.model.Track
import com.army.btsmusic.model.UserAccount
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant

// 1. Account Dialog
@Composable
fun AccountDialog(
    userAccount: UserAccount,
    downloadedCount: Int,
    onSync: () -> Unit,
    onSignIn: () -> Unit,
    onSignOut: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .clip(RoundedCornerShape(24.dp)),
            color = DarkCard
        ) {
            Column(modifier = Modifier.padding(22.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "1. YouTube Account",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.LightGray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // User Profile Box
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(BorahaePurple),
                        contentAlignment = Alignment.Center
                    ) {
                        if (userAccount.avatarUrl != null) {
                            AsyncImage(
                                model = userAccount.avatarUrl,
                                contentDescription = "Avatar",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Icon(Icons.Default.CloudDone, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = userAccount.channelTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = if (userAccount.isSignedIn) userAccount.email else "Not connected to YouTube",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DarkOnSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Stats Cards
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF160B24))
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${userAccount.youtubeMusicPlaylistCount}", fontWeight = FontWeight.Bold, color = BorahaeNeon, fontSize = 18.sp)
                        Text(text = "Playlists", fontSize = 11.sp, color = DarkOnSurfaceVariant)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF160B24))
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${userAccount.syncedLikedSongsCount}", fontWeight = FontWeight.Bold, color = BorahaeLilac, fontSize = 18.sp)
                        Text(text = "Liked Tracks", fontSize = 11.sp, color = DarkOnSurfaceVariant)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF160B24))
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "$downloadedCount", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 18.sp)
                        Text(text = "Offline", fontSize = 11.sp, color = DarkOnSurfaceVariant)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (userAccount.isSignedIn) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = onSync,
                            colors = ButtonDefaults.buttonColors(containerColor = BorahaePurple),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            if (userAccount.isSyncing) {
                                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                            } else {
                                Icon(Icons.Default.Sync, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Sync Now")
                            }
                        }
                        OutlinedButton(
                            onClick = onSignOut,
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFF43F5E)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Sign Out")
                        }
                    }
                } else {
                    Button(
                        onClick = onSignIn,
                        colors = ButtonDefaults.buttonColors(containerColor = BorahaeNeon),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Connect YouTube Music Account", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

// 2. Favorite Songs Dialog
@Composable
fun FavSongsDialog(
    favoriteTracks: List<Track>,
    favoriteTrackIds: Set<String>,
    onTrackClick: (Track) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.75f)
                .clip(RoundedCornerShape(24.dp)),
            color = DarkCard
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Favorite, contentDescription = null, tint = BorahaeNeon, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "2. Favorite Songs (${favoriteTracks.size})",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.LightGray)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (favoriteTracks.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No favorite songs yet. Tap the heart to add songs!", color = Color.Gray, fontSize = 13.sp)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(favoriteTracks) { track ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF160B24))
                                    .clickable {
                                        onTrackClick(track)
                                        onDismiss()
                                    }
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                    AsyncImage(
                                        model = track.coverUrl,
                                        contentDescription = track.title,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.size(42.dp).clip(RoundedCornerShape(8.dp))
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(text = track.title, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 13.sp)
                                        Text(text = "${track.artist} • ${track.member.stageName}", color = DarkOnSurfaceVariant, fontSize = 11.sp)
                                    }
                                }

                                IconButton(onClick = { onToggleFavorite(track.id) }) {
                                    Icon(
                                        imageVector = if (favoriteTrackIds.contains(track.id)) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = "Fav",
                                        tint = BorahaeNeon,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// 4. Privacy Policy Dialog
@Composable
fun PrivacyPolicyDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            color = DarkCard
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("4. Privacy Policy", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                    IconButton(onClick = onDismiss) { Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.LightGray) }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = """
                    Last Updated: October 2026

                    BTS Music ("we", "our", or "the app") is committed to protecting your privacy as an ARMY user.

                    1. Data Storage & Local Cache:
                    All your offline music downloads, equalizer audio presets, and playlist configurations are stored locally on your device in secure SQLite app storage. No audio files or personal data are uploaded to external third-party servers.

                    2. YouTube & Google Sync:
                    When you connect your YouTube account, authentication is processed directly via Google's secure OAuth flow. The app only accesses playlist and liked track metadata to synchronize your BTS collection.

                    3. Bluetooth & Peripheral Usage:
                    Bluetooth Low Energy (BLE) permissions are used exclusively to scan for and synchronize your official BTS ARMY Bomb lightstick with playback beats. We never track your physical location.

                    4. No Advertisements or Tracking:
                    BTS Music is a fan-created, non-commercial application. We do not sell user data, nor do we run third-party advertising SDKs or analytics trackers.
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BorahaePurple),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Understood")
                }
            }
        }
    }
}

// 5. Terms & Conditions Dialog
@Composable
fun TermsDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            color = DarkCard
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("5. Terms & Conditions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                    IconButton(onClick = onDismiss) { Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.LightGray) }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = """
                    Welcome to BTS Music. By installing and using this application, you agree to the following terms:

                    1. Non-Commercial Fan Tribute:
                    BTS Music is an independent fan-made streaming client designed exclusively for the BTS ARMY community. All rights to songs, lyrics, stage photos, and trademarks belong to BIGHIT MUSIC, HYBE, and the members of BTS (RM, Jin, SUGA, j-hope, Jimin, V, Jung Kook).

                    2. Fair Use & Media Sources:
                    All audio streams are queried through YouTube Music public endpoints in compliance with fair use principles. Offline downloads are intended strictly for personal listening on the user's private device.

                    3. Code of Conduct:
                    In social features such as 'Listen Together' and virtual cheering rooms, users agree to uphold the Borahae spirit: respect, kindness, and positivity. Any abusive behavior will result in a local room block.
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BorahaePurple),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("I Agree")
                }
            }
        }
    }
}

// 6. Help Dialog
@Composable
fun HelpDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            color = DarkCard
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("6. Help & FAQ", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                    IconButton(onClick = onDismiss) { Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.LightGray) }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = """
                    • How do I download songs for offline use?
                    Tap the download arrow icon on any song. The track will be saved to your device and will be accessible anytime under Library > Offline Downloads.

                    • How does the Equalizer work?
                    Tap Menu > 3. EQUALIZER to tune 5-band frequencies, trigger Borahae Bass Boost, adjust playback speed (tempo), or key-shift pitch up and down.

                    • How to connect an ARMY Bomb lightstick?
                    Turn on your lightstick in Bluetooth mode. In Home screen, tap "Scan BLE" to pair. If you don't have a lightstick, tap "Simulate" to test the on-screen glowing lightstick orb.

                    • How to use 'Listen Together'?
                    Go to the 'Together' tab. Tap 'Create Room' to generate a room code (e.g. ARMY-777). Give this code to your friends so they can join and stream in perfect sync!
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BorahaePurple),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Got It")
                }
            }
        }
    }
}

// 7. About Dialog
@Composable
fun AboutDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            color = DarkCard
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("7. About", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                    IconButton(onClick = onDismiss) { Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.LightGray) }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "💜", fontSize = 48.sp)
                Text(text = "BTS MUSIC", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Black, color = Color.White)
                Text(text = "Version 1.0.0 (Borahae Edition)", fontSize = 12.sp, color = BorahaeNeon)

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "A bespoke audio-visual sanctuary crafted exclusively for BTS (RM, Jin, SUGA, j-hope, Jimin, V, Jung Kook) and ARMY worldwide.",
                    fontSize = 13.sp,
                    color = Color.LightGray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1B0E2C))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "\"Purple is the last color of the rainbow colors, so it means I will trust and love you for a long time.\"\n— Kim Taehyung (V)",
                        fontSize = 12.sp,
                        color = BorahaeLilac,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BorahaePurple),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Borahae! 💜")
                }
            }
        }
    }
}
