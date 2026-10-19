package com.army.btsmusic.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.army.btsmusic.theme.AudioVisualizerCyan
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple

@Composable
fun AudioVisualizer(
    frequencies: List<Float>,
    modifier: Modifier = Modifier,
    maxHeight: Dp = 54.dp,
    barWidth: Dp = 3.dp,
    barSpacing: Dp = 2.dp
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(maxHeight),
        horizontalArrangement = Arrangement.spacedBy(barSpacing, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val gradient = Brush.verticalGradient(
            colors = listOf(BorahaeNeon, BorahaePurple, AudioVisualizerCyan)
        )

        frequencies.forEach { freq ->
            val animatedHeightFraction by animateFloatAsState(
                targetValue = freq.coerceIn(0.1f, 1.0f),
                animationSpec = tween(durationMillis = 60),
                label = "freqBar"
            )

            Box(
                modifier = Modifier
                    .width(barWidth)
                    .fillMaxHeight(fraction = animatedHeightFraction)
                    .background(
                        brush = gradient,
                        shape = RoundedCornerShape(2.dp)
                    )
            )
        }
    }
}
