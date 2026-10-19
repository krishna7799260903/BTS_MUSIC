package com.army.btsmusic.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import com.army.btsmusic.viewmodel.FloatingHeart
import kotlin.math.roundToInt

@Composable
fun FloatingHeartsOverlay(
    hearts: List<FloatingHeart>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        hearts.forEach { heart ->
            SingleFloatingHeart(heart = heart)
        }
    }
}

@Composable
private fun SingleFloatingHeart(heart: FloatingHeart) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(heart.id) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1800)
        )
    }

    val yOffset = (-progress.value * 650).roundToInt()
    val xWiggle = (Math.sin(progress.value * 12.0) * 35).roundToInt()
    val alpha = (1f - progress.value).coerceIn(0f, 1f)
    val scale = 0.8f + (progress.value * 0.7f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset { IntOffset(x = ((heart.startXRatio - 0.5f) * 600).roundToInt() + xWiggle, y = yOffset) },
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = "💜",
            fontSize = 32.sp,
            modifier = Modifier
                .scale(scale)
                .alpha(alpha),
            color = Color(heart.colorHex)
        )
    }
}
