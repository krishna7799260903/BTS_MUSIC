package com.army.btsmusic.ui.screens.social

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Podcasts
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.army.btsmusic.model.Member
import com.army.btsmusic.social.ListenTogetherManager
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant
import com.army.btsmusic.theme.OnlineGreen
import com.army.btsmusic.viewmodel.MusicPlayerViewModel

@Composable
fun ListenTogetherScreen(
    listenTogetherManager: ListenTogetherManager,
    playerViewModel: MusicPlayerViewModel,
    modifier: Modifier = Modifier
) {
    val activeRoom by listenTogetherManager.activeRoom.collectAsState()
    val currentTrack by playerViewModel.currentTrack.collectAsState()

    var joinCodeInput by remember { mutableStateOf("") }
    var hostNameInput by remember { mutableStateOf("") }
    var selectedBias by remember { mutableStateOf(Member.OT7) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title Header
        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Podcasts,
                        contentDescription = null,
                        tint = BorahaeNeon,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Listen Together",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(
                    text = "Stream BTS tracks synchronously in real-time with ARMY friends",
                    style = MaterialTheme.typography.bodyMedium,
                    color = BorahaeLilac
                )
            }
        }

        if (activeRoom != null) {
            // Active Room Card
            val room = activeRoom!!
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkCard)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(OnlineGreen)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "LIVE SYNC ACTIVE",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = OnlineGreen,
                                    letterSpacing = 1.sp
                                )
                            }

                            // Room Code Badge
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(BorahaePurple.copy(alpha = 0.3f))
                                    .border(1.dp, BorahaeNeon, RoundedCornerShape(8.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "CODE: ${room.roomCode}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Black,
                                    color = BorahaeNeon
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = room.roomName,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Host: ${room.hostName}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DarkOnSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Synchronized Current Track Card
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF160B24))
                                .padding(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.MusicNote,
                                    contentDescription = null,
                                    tint = BorahaeNeon,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = room.currentTrack.title,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "${room.currentTrack.artist} • ${room.currentTrack.member.stageName}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = DarkOnSurfaceVariant
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Participant Avatars
                        Text(
                            text = "Connected ARMY Listeners (${room.participants.size}):",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.LightGray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            room.participants.forEach { user ->
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .background(Color(user.avatarColorHex)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = user.name.take(2).uppercase(),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            color = Color.White
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = user.name,
                                        fontSize = 10.sp,
                                        color = Color.LightGray
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedButton(
                            onClick = { listenTogetherManager.leaveRoom() },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFF43F5E)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Leave Room")
                        }
                    }
                }
            }
        } else {
            // Create a Room Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkCard)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Group, contentDescription = null, tint = BorahaeNeon)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Host a Streaming Room",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = hostNameInput,
                            onValueChange = { hostNameInput = it },
                            placeholder = { Text("Enter your ARMY Nickname...") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BorahaePurple,
                                unfocusedBorderColor = Color(0xFF261542),
                                focusedContainerColor = Color(0xFF130722),
                                unfocusedContainerColor = Color(0xFF130722)
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                val track = currentTrack ?: com.army.btsmusic.data.BtsDiscographyCatalog.sampleTracks.first()
                                listenTogetherManager.createRoom(hostNameInput, track)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = BorahaePurple),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Create ARMY Sync Room 💜", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Join a Room Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkCard)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "Join an Existing Room",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = joinCodeInput,
                            onValueChange = { joinCodeInput = it },
                            placeholder = { Text("Enter 6-digit code (e.g. ARMY-777)...") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BorahaePurple,
                                unfocusedBorderColor = Color(0xFF261542),
                                focusedContainerColor = Color(0xFF130722),
                                unfocusedContainerColor = Color(0xFF130722)
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Choose your Bias:",
                            style = MaterialTheme.typography.labelSmall,
                            color = DarkOnSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            items(Member.entries.toTypedArray()) { member ->
                                val isSelected = selectedBias == member
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) Color(member.signatureColorHex) else Color(0xFF160B24))
                                        .clickable { selectedBias = member }
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = member.stageName,
                                        fontSize = 11.sp,
                                        color = if (isSelected) Color.White else Color.Gray,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = {
                                if (joinCodeInput.isNotBlank()) {
                                    listenTogetherManager.joinRoom(joinCodeInput, hostNameInput, selectedBias)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = BorahaeNeon),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Join Room", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
