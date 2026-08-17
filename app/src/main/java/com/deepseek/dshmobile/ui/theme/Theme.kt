package com.deepseek.dshmobile.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val PrimaryColor = Color(0xFF1976D2)
private val PrimaryDarkColor = Color(0xFF0D47A1)
private val AccentColor = Color(0xFF4CAF50)
private val BackgroundColor = Color(0xFFF5F5F5)
private val SurfaceColor = Color(0xFFFFFFFF)
private val UserBubbleColor = Color(0xFF1976D2)
private val AssistantBubbleColor = Color(0xFFE3F2FD)

val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFBBDEFB),
    onPrimaryContainer = Color.Black,
    secondary = AccentColor,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFC8E6C9),
    onSecondaryContainer = Color.Black,
    background = BackgroundColor,
    onBackground = Color(0xFF212121),
    surface = SurfaceColor,
    onSurface = Color(0xFF212121),
    error = Color(0xFFF44336),
    onError = Color.White,
    surfaceVariant = Color(0xFFE0E0E0),
    onSurfaceVariant = Color(0xFF424242)
)

val DarkColorScheme = darkColorScheme(
    primary = PrimaryDarkColor,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF0D47A1),
    onPrimaryContainer = Color.White,
    secondary = AccentColor,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF2E7D32),
    onSecondaryContainer = Color.White,
    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),
    error = Color(0xFFCF6679),
    onError = Color.Black,
    surfaceVariant = Color(0xFF303030),
    onSurfaceVariant = Color(0xFFB0B0B0)
)

@Composable
fun DSHMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
