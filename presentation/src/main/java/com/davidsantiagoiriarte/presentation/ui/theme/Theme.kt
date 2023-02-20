package com.davidsantiagoiriarte.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val LightColorPalette = lightColors(
    primary = enRepartoColor,
    primaryVariant = enRepartoColor,
    secondary = enTerminalColor
)

@Composable
fun CoordinadoraTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}