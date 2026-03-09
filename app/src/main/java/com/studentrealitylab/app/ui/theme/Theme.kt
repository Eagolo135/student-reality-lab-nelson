package com.studentrealitylab.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val RichDarkColors = darkColorScheme(
    primary = GoldPrimary,
    onPrimary = RichBlack,
    primaryContainer = GoldDeep,
    onPrimaryContainer = TextOnDark,
    secondary = GoldAccent,
    onSecondary = RichBlack,
    background = RichBlack,
    onBackground = TextOnDark,
    surface = RichBlackSurface,
    onSurface = TextOnDark,
    surfaceVariant = RichBlackElevated,
    onSurfaceVariant = GoldAccent,
    outline = GoldDeep
)

@Composable
fun StudentRealityLabTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = RichDarkColors,
        typography = Typography,
        content = content
    )
}
