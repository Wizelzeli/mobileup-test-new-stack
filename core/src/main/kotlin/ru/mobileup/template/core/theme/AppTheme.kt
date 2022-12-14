package ru.mobileup.template.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ru.mobileup.template.features.crypto.ui.BackgroundColors
import ru.mobileup.template.features.crypto.ui.CryptoColors
import ru.mobileup.template.features.crypto.ui.TextColors

private val LightColorPalette = lightColors(
    primary = OrangeChipsText,
    primaryVariant = OrangeChipsText,
    secondary = OrangeChipsText

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val DarkColorPalette = darkColors(
    primary = Indigo400,
    primaryVariant = Indigo400,
    secondary = TealA400
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        LightColorPalette // not implemented yet
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}