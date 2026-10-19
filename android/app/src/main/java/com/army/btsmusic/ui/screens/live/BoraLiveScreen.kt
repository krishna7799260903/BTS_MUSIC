package com.army.btsmusic.ui.screens.live

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.army.btsmusic.model.CameraAngle
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePink
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant
import com.army.btsmusic.ui.components.FloatingHeartsOverlay
import com.army.btsmusic.viewmodel.BoraLiveViewModel

@Composable
fun BoraLiveScreen(
    viewModel: BoraLiveViewModel,
    modifier: Modifier = Modifier
) {
    val concertStream by viewModel.concertStream.collectAsState()
    val chatMessages by viewModel.chatMessages.collectAsState()
    val floatingHearts by viewModel.floatingHearts.collectAsState()

    var chatInput by remember { mutableStateOf("") }
    val chatListState = rememberLazyListState()

    // Scroll to bottom of chat when new message arrives
    LaunchedEffect(chatMessages.size) {
        if (chatMessages.isNotEmpty()) {
            chatListState.animateScrollToItem(chatMessages.size - 1)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 110.dp)
        ) {
            // Live Stream Viewport
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .background(Color.Black)
            ) {
                // Gradient backdrop representing stadium concert lighting
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color(0xFF2E0854),
                                    Color(0xFF130722),
                                    Color.Black
                                )
                            )
                        )
                )

                // Stadium / Angle Display Simulation
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Top Live Badges
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // LIVE Badge
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFE11D48))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.FiberManualRecord,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(10.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("LIVE 4K", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }

                        // Viewers Count Pill
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Black.copy(alpha = 0.6f))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Visibility, contentDescription = null, tint = BorahaeNeon, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "%,d ARMYs".format(concertStream.viewerCount),
                                    fontSize = 11.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    // Active Camera Angle Title Overlay
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Videocam, contentDescription = null, tint = BorahaeNeon, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "CURRENT ANGLE: ${concertStream.activeAngle.label}",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                color = BorahaeNeon,
                                letterSpacing = 1.sp
                            )
                        }
                        Text(
                            text = concertStream.activeAngle.description,
                            fontSize = 11.sp,
                            color = Color.LightGray
                        )
                    }
                }
            }

            // Multi-Angle Camera Switcher Bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkCard)
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = "SWITCH CAMERA ANGLE",
                    style = MaterialTheme.typography.labelSmall,
                    color = BorahaeLilac,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 6.dp)
                ) {
                    items(CameraAngle.entries.toTypedArray()) { angle ->
                        val isSelected = concertStream.activeAngle == angle
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) BorahaePurple else Color(0xFF1A0E2C))
                                .border(
                                    width = if (isSelected) 1.dp else 0.dp,
                                    color = BorahaeNeon,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable { viewModel.switchCameraAngle(angle) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = angle.label,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else Color.LightGray
                            )
                        }
                    }
                }
            }

            // Interactive Cheering & Hearts Stats Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "%,d".format(concertStream.totalPurpleHearts),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        color = BorahaeNeon
                    )
                    Text(
                        text = "Global Purple Hearts 💜",
                        style = MaterialTheme.typography.labelSmall,
                        color = DarkOnSurfaceVariant
                    )
                }

                // Interactive Cheer Burst Button
                Button(
                    onClick = { viewModel.triggerHeartCheer() },
                    colors = ButtonDefaults.buttonColors(containerColor = BorahaePurple),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("💜 Cheer Boys!", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }

            // Live Fan Chat Feed Header
            Text(
                text = "LIVE ARMY CHAT",
                style = MaterialTheme.typography.labelSmall,
                color = DarkOnSurfaceVariant,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            // Chat Messages List
            LazyColumn(
                state = chatListState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(chatMessages, key = { it.id }) { msg ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        // User Avatar initial
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(Color(msg.avatarColorHex)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = msg.senderName.take(1),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = msg.senderName,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BorahaeLilac
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = msg.timestampFormatted,
                                    fontSize = 9.sp,
                                    color = Color.Gray
                                )
                            }

                            Text(
                                text = msg.message,
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // Chat Input Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = chatInput,
                    onValueChange = { chatInput = it },
                    placeholder = { Text("Send purple cheer message...", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BorahaePurple,
                        unfocusedBorderColor = Color(0xFF261542),
                        focusedContainerColor = DarkCard,
                        unfocusedContainerColor = DarkCard
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        if (chatInput.isNotBlank()) {
                            viewModel.sendChatMessage(chatInput)
                            chatInput = ""
                        }
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(BorahaePurple)
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        // Overlay of Floating Purple Hearts
        FloatingHeartsOverlay(hearts = floatingHearts)
    }
}
