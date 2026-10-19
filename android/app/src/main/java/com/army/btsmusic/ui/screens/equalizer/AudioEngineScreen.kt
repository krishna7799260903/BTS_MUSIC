package com.army.btsmusic.ui.screens.equalizer

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.army.btsmusic.model.AudioEffectsState
import com.army.btsmusic.theme.BorahaeLilac
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkBackground
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant
import com.army.btsmusic.viewmodel.MusicPlayerViewModel

@Composable
fun AudioEngineScreen(
    viewModel: MusicPlayerViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val audioEffects by viewModel.audioEffectsState.collectAsState()

    val bandFrequencies = listOf("60 Hz", "230 Hz", "910 Hz", "3.6 kHz", "14 kHz")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Bar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = "Advanced Audio Engine",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "ExoPlayer DSP, 5-Band EQ & Pitch Shifting",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BorahaeLilac
                    )
                }
            }
        }

        // Equalizer Presets Row
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "SIGNATURE PRESETS",
                        style = MaterialTheme.typography.labelLarge,
                        color = BorahaeNeon,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(AudioEffectsState.PRESETS.keys.toList()) { presetName ->
                            val isSelected = audioEffects.currentPreset == presetName
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSelected) BorahaePurple else Color(0xFF1B0F2E))
                                    .clickable { viewModel.setEqualizerPreset(presetName) }
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = presetName,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else Color.LightGray
                                )
                            }
                        }
                    }
                }
            }
        }

        // 5-Band Graphic Equalizer
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.GraphicEq, contentDescription = null, tint = BorahaeNeon)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "5-Band Graphic Equalizer",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Text(
                            text = audioEffects.currentPreset,
                            style = MaterialTheme.typography.labelSmall,
                            color = BorahaeLilac
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Sliders for each frequency band
                    audioEffects.bandLevels.forEachIndexed { index, level ->
                        val freqLabel = bandFrequencies.getOrElse(index) { "Band $index" }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = freqLabel,
                                fontSize = 12.sp,
                                color = Color.LightGray,
                                modifier = Modifier.width(62.dp)
                            )

                            Slider(
                                value = level.toFloat(),
                                onValueChange = { viewModel.setBandLevel(index, it.toInt()) },
                                valueRange = -10f..10f,
                                modifier = Modifier.weight(1f),
                                colors = SliderDefaults.colors(
                                    thumbColor = Color.White,
                                    activeTrackColor = BorahaePurple,
                                    inactiveTrackColor = Color(0xFF1B0F2E)
                                )
                            )

                            Text(
                                text = "%+d dB".format(level),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = BorahaeNeon,
                                modifier = Modifier.width(44.dp)
                            )
                        }
                    }
                }
            }
        }

        // Bass Boost & Virtualizer
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "ENHANCEMENTS & SPATIAL AUDIO",
                        style = MaterialTheme.typography.labelLarge,
                        color = BorahaeNeon,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Borahae Bass Boost
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Borahae Bass Boost", color = Color.White, fontSize = 13.sp)
                        Text("${audioEffects.bassBoostStrength / 10}%", color = BorahaeLilac, fontSize = 13.sp)
                    }
                    Slider(
                        value = audioEffects.bassBoostStrength.toFloat(),
                        onValueChange = { viewModel.setBassBoost(it.toInt()) },
                        valueRange = 0f..1000f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = BorahaeNeon,
                            inactiveTrackColor = Color(0xFF1B0F2E)
                        )
                    )

                    // Virtualizer
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Stadium Virtualizer (3D Soundstage)", color = Color.White, fontSize = 13.sp)
                        Text("${audioEffects.virtualizerStrength / 10}%", color = BorahaeLilac, fontSize = 13.sp)
                    }
                    Slider(
                        value = audioEffects.virtualizerStrength.toFloat(),
                        onValueChange = { viewModel.setVirtualizer(it.toInt()) },
                        valueRange = 0f..1000f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = BorahaePurple,
                            inactiveTrackColor = Color(0xFF1B0F2E)
                        )
                    )
                }
            }
        }

        // Pitch & Tempo Shifting Controls
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Speed, contentDescription = null, tint = BorahaeNeon)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Pitch & Tempo Shifter",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Playback Speed (Tempo)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Playback Speed (Tempo)", color = Color.White, fontSize = 13.sp)
                        Text("%.2fx".format(audioEffects.tempo), color = BorahaeNeon, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                    Slider(
                        value = audioEffects.tempo,
                        onValueChange = { viewModel.setPlaybackSpeed(it) },
                        valueRange = 0.5f..2.0f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = BorahaeNeon,
                            inactiveTrackColor = Color(0xFF1B0F2E)
                        )
                    )

                    // Pitch Shifting (Semitones)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Pitch Shifting (Key Tuning)", color = Color.White, fontSize = 13.sp)
                        Text("%+.1f st".format(audioEffects.pitchSemitones), color = BorahaeLilac, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                    Slider(
                        value = audioEffects.pitchSemitones,
                        onValueChange = { viewModel.setPitchSemitones(it) },
                        valueRange = -6.0f..6.0f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = BorahaePurple,
                            inactiveTrackColor = Color(0xFF1B0F2E)
                        )
                    )
                }
            }
        }

        // Automatic Silence Skipping
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Automatic Silence Skipping",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Automatically detect and skip silence at the start/end of songs for gapless listening",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DarkOnSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Switch(
                        checked = audioEffects.skipSilenceEnabled,
                        onCheckedChange = { viewModel.setSkipSilence(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = BorahaePurple,
                            uncheckedTrackColor = Color(0xFF1B0F2E)
                        )
                    )
                }
            }
        }
    }
}
