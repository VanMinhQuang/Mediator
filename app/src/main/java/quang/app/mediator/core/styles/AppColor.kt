package quang.app.mediator.core.styles


import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


object AppColor {

    val Primary = Color(0xFF8E97FD)
    val Secondary = Color(0xFFFF8FA2)
    val Accent = Color(0xFFFFC107)
    val Background = Color(0xFFF5F6FA)
    val TextPrimary = Color(0xFF1C1C1E)

    val PrimaryBlue = Color(0xFF7583CA)
    val Cream = Color(0xFFFAF8F5)
    val White = Color(0xFFFFFFFF)
    val Gray = Color(0xFFF2F3F7)

    val TextBackGround = Color(0XFFF2F3F7)
    val TextColor = Color(0xFF3F414E)
    val TextHint = Color(0XFFA1A4B2)

    val PrimaryGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF9178FF), // ~0.57, 0.47, 1.0
            Color(0xFF6CA9FF)  // ~0.43, 0.66, 1.0
        )
    )
}
