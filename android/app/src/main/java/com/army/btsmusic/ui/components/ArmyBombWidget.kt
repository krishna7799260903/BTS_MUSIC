package com.army.btsmusic.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.BluetoothConnected
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.army.btsmusic.bluetooth.LightstickConnectionState
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.OnlineGreen

@Composable
fun ArmyBombWidget(
    connectionState: LightstickConnectionState,
    deviceName: String?,
    currentColorHex: Long,
    glowIntensity: Float,
    colorPresets: List<Pair<String, Long>>,
    onConnectBle: () -> Unit,
    onStartSimulator: () -> Unit,
    onDisconnect: () -> Unit,
    onSelectColor: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val isConnected = connectionState == LightstickConnectionState.CONNECTED_HARDWARE ||
            connectionState == LightstickConnectionState.CONNECTED_SIMULATOR

    val targetColor = Color(currentColorHex)
    val animatedColor by animateColorAsState(targetValue = targetColor, label = "lightColor")
    val animatedGlow by animateFloatAsState(
        targetValue = if (isConnected) glowIntensity else 0.2f,
        animationSpec = tween(durationMillis = 80),
        label = "glowScale"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(DarkCard)
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(listOf(BorahaePurple, BorahaeNeon)),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(if (isConnected) OnlineGreen else Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ARMY BOMB BLE SYNC",
                        style = MaterialTheme.typography.labelLarge,
                        color = BorahaeNeon,
                        letterSpacing = 1.sp
                    )
                }

                Text(
                    text = when (connectionState) {
                        LightstickConnectionState.CONNECTED_HARDWARE -> "BLE Connected"
                        LightstickConnectionState.CONNECTED_SIMULATOR -> "Simulator Active"
                        LightstickConnectionState.SCANNING -> "Scanning..."
                        LightstickConnectionState.CONNECTING -> "Connecting..."
                        LightstickConnectionState.DISCONNECTED -> "Offline"
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Lightstick Simulation Orb & Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Interactive Glowing Orb (Simulator)
                Box(
                    modifier = Modifier.size(76.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer diffuse glow
                    Box(
                        modifier = Modifier
                            .size((64 * animatedGlow).dp)
                            .clip(CircleShape)
                            .background(animatedColor.copy(alpha = 0.35f * animatedGlow))
                    )
                    // Inner bright orb
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .shadow(
                                elevation = (16 * animatedGlow).dp,
                                shape = CircleShape,
                                spotColor = animatedColor
                            )
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(Color.White, animatedColor, Color(0xFF1E1035))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = "Lightbulb",
                            tint = Color.White.copy(alpha = 0.9f),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Info & Action Buttons
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = deviceName ?: "BTS Official Lightstick",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = if (isConnected) "Rhythm & audio strobe synchronized" else "Connect via BLE or run software simulation",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (!isConnected) {
                            OutlinedButton(
                                onClick = onConnectBle,
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = BorahaeNeon)
                            ) {
                                Icon(Icons.Default.Bluetooth, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Scan BLE", fontSize = 12.sp)
                            }
                            OutlinedButton(
                                onClick = onStartSimulator,
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                            ) {
                                Text("Simulate", fontSize = 12.sp)
                            }
                        } else {
                            OutlinedButton(
                                onClick = onDisconnect,
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFF43F5E))
                            ) {
                                Text("Disconnect", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            // Color Presets Row
            if (isConnected) {
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Light Color Moods:",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    colorPresets.forEach { (_, hex) ->
                        val isSelected = currentColorHex == hex
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .scale(if (isSelected) 1.15f else 1.0f)
                                .clip(CircleShape)
                                .background(Color(hex))
                                .border(
                                    width = if (isSelected) 2.dp else 0.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .clickable { onSelectColor(hex) }
                        )
                    }
                }
            }
        }
    }
}
