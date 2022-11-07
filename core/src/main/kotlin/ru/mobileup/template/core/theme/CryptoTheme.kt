package ru.mobileup.template.features.crypto.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.mobileup.template.core.theme.*

/*@Composable
fun CryptoTheme(
    colors: CryptoColors,
    typography: CryptoTypography,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalCryptoColors provides colors,
        LocalCryptoTypography provides typography
    ) {
        content()
    }
}*/

object CryptoTheme {
    private val LocalCryptoColors = staticCompositionLocalOf<CryptoColors?> {
        CryptoColors(
            text = TextColors(
                titleCrypto = Black87,
                titleDesc = Color.Black,
                primaryCrypto = CryptoBlack,
                shortCrypto = CryptoShortBlack,
                plusCryptoPercent = GreenPercent,
                minusCryptoPercent = RedPercent,
                selectedChips = OrangeChipsText,
                disabledChips = Black87,
            ),
            background = BackgroundColors(
                primary = Color.White,
                selectedChips = OrangeChipsBack,
                disabledChips = Black12
            )
        )
    }
    private val LocalCryptoTypography = staticCompositionLocalOf<CryptoTypography?> {
        CryptoTypography(
            title = TitleTypography(
                boldLarge = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            ),
            body = BodyTypography(
                regularLarge = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                ),
                regularNormal = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
                boldSmall = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        )
    }

    val colors: CryptoColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCryptoColors.current
            ?: throw Exception("CryptoColors are not provided. Did you forget to apply CryptoTheme?")

    val typography: CryptoTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalCryptoTypography.current
            ?: throw Exception("CryptoTypography is not provided. Did you forget to apply CryptoTheme?")
}

data class CryptoColors(
    val text: TextColors,
    val background: BackgroundColors,
)

data class TextColors(
    val titleCrypto: Color,
    val titleDesc: Color,
    val primaryCrypto: Color,
    val shortCrypto: Color,
    val plusCryptoPercent: Color,
    val minusCryptoPercent: Color,
    val selectedChips: Color,
    val disabledChips: Color,
)

data class BackgroundColors(
    val primary: Color,
    val selectedChips: Color,
    val disabledChips: Color,
)

data class CryptoTypography(
    val title: TitleTypography,
    val body: BodyTypography
)

data class TitleTypography(
    val boldLarge: TextStyle
)

data class BodyTypography(
    val regularLarge: TextStyle,
    val regularNormal: TextStyle,
    val boldSmall: TextStyle
)




