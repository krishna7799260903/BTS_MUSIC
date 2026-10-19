package com.army.btsmusic.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = BorahaePurple,
    onPrimary = DarkOnBackground,
    primaryContainer = BorahaePurpleDeep,
    onPrimaryContainer = BorahaeLilacLight,
    secondary = BorahaeLilac,
    onSecondary = DarkBackground,
    secondaryContainer = DarkSurfaceVariant,
    onSecondaryContainer = DarkOnSurfaceVariant,
    tertiary = BorahaeNeon,
    onTertiary = DarkBackground,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,
    outline = DarkBorder
)

private val LightColorScheme = lightColorScheme(
    primary = BorahaePurpleDark,
    onPrimary = LightBackground,
    primaryContainer = LightSurfaceVariant,
    onPrimaryContainer = LightOnBackground,
    secondary = BorahaePurple,
    onSecondary = LightBackground,
    secondaryContainer = LightSurface,
    onSecondaryContainer = LightOnSurfaceVariant,
    tertiary = BorahaePink,
    onTertiary = LightBackground,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,
    outline = LightBorder
)

@Composable
fun BtsMusicTheme(
    darkTheme: Boolean = true, // Default to gorgeous signature Borahae dark mode
    dynamicColor: Boolean = false, // Preserve signature purple by default
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            try {
                val window = (view.context as? Activity)?.window ?: return@SideEffect
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
            } catch (e: Throwable) { }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
