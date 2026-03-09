package com.studentrealitylab.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = BluePrimary,
    secondary = GreenSecondary
)

private val DarkColors = darkColorScheme(
    primary = BluePrimary,
    secondary = GreenSecondary
)

@Composable
fun StudentRealityLabTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
