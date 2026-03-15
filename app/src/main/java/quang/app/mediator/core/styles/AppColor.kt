package quang.app.mediator.core.styles


import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


object AppColor {

    val Primary = Color(0xFF8E97FD)
    val Secondary = Color(0xFFAEB4FC)
    val Accent = Color(0xFFFFC107)
    val Background = Color(0xFFF5F6FA)

    val Gray600 = Color(0xFFB6B8BF)

    val BluePurple = Color(0xFF808AFF)
    val PrimaryBlue = Color(0xFF7583CA)
    val Cream = Color(0xFFFAF8F5)
    val White = Color(0xFFFFFFFF)
    val CreamyWhite = Color(0xFFE5E5E5)
    val Gray = Color(0xFFF2F3F7)
    val TextPrimary = Color(0xFF1C1C1E)
    val TextBackGround = Color(0XFFF2F3F7)
    val TextColor = Color(0xFF3F414E)
    val TextHint = Color(0XFFA1A4B2)
    val TextCream = Color(0XFFFFECCC)
    val WhiteBox = Color(0XFFEBEAEC)
    val CarrotOrange = Color(0xFFFA6E5A)
    val LightOrange = Color(0xFFFEB18F)
    val BrightYellow = Color(0xFFFFCF86)
    val DarkGreen = Color(0xFF6CB28E)
    val LightGreen = Color(0xFF84DBC7)
    val DarkGray = Color(0xFF4E5567)
    val LightGray = Color(0xFFF5F5F9)
    val Gray500 = Color(0xFFE6E7F2)
    val Gray700 = Color(0xFFA0A3B1)
    val DarkBlue = Color(0xFF424495)
    val DarkPink = Color(0xFFD9A5B5)
    val LightPink = Color(0xFFFF84A2)
    val LightBlue = Color(0xFF7FD2F2)
    val DarkYellow = Color(0xFFFFC97E)
    val DarkPurple = Color(0xFF333242)
    val PrimaryGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF9178FF), // ~0.57, 0.47, 1.0
            Color(0xFF6CA9FF)  // ~0.43, 0.66, 1.0
        )
    )

    val CardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF8E97FD),
            Color(0xFFAEB4FC)
        )
    )

    val LightPeach = Color(0xFFF1DDCF)
}
