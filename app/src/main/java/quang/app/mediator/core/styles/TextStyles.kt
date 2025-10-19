package com.yourapp.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import quang.app.mediator.R
import quang.app.mediator.core.styles.AppColor



val HelveticaNeue = FontFamily(
    Font(R.font.helvetica_regular, FontWeight.Normal),
    Font(R.font.helvetica_round_bold, FontWeight.Bold),
    Font(R.font.helvetica_light, FontWeight.Light),
    Font(R.font.helvetica_semi, FontWeight.SemiBold),
    Font(R.font.helvetica_round_bold, FontWeight.Black)
)

object AppTextStyle {

    // MARK: - 32
    val black32 = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Black, fontFamily = HelveticaNeue)
    val bold32 = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold, fontFamily = HelveticaNeue)
    val semiBold32 = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.SemiBold, fontFamily = HelveticaNeue)
    val regular32 = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)

    // MARK: - 28
    val bold28 = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold, fontFamily = HelveticaNeue)
    val semiBold28 = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.SemiBold, fontFamily = HelveticaNeue)
    val medium28 = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Medium, fontFamily = HelveticaNeue)
    val regular28 = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)
    val light28 = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Light, fontFamily = HelveticaNeue)

    // MARK: - 24
    val bold24 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = HelveticaNeue)
    val semiBold24 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold, fontFamily = HelveticaNeue)
    val medium24 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium, fontFamily = HelveticaNeue)
    val regular24 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)

    // MARK: - 22
    val bold22 = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, fontFamily = HelveticaNeue)
    val semiBold22 = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold, fontFamily = HelveticaNeue)
    val medium22 = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Medium, fontFamily = HelveticaNeue)
    val regular22 = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)

    // MARK: - 20
    val bold20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = HelveticaNeue)
    val semiBold20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, fontFamily = HelveticaNeue)
    val medium20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, fontFamily = HelveticaNeue)
    val regular20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)
    val light20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Light, fontFamily = HelveticaNeue)

    // MARK: - 18
    val bold18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = HelveticaNeue)
    val semiBold18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, fontFamily = HelveticaNeue)
    val medium18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, fontFamily = HelveticaNeue)
    val regular18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)
    val light18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Light, fontFamily = HelveticaNeue)

    // MARK: - 16
    val bold16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = HelveticaNeue)
    val semiBold16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, fontFamily = HelveticaNeue)
    val medium16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, fontFamily = HelveticaNeue)
    val regular16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)
    val light16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light, fontFamily = HelveticaNeue)

    // MARK: - 14
    val medium14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, fontFamily = HelveticaNeue)

    val bold14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, fontFamily = HelveticaNeue)
    val semiBold14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = HelveticaNeue)
    val regular14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)
    val light14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Light, fontFamily = HelveticaNeue)

    // MARK: - 12
    val semiBold12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, fontFamily = HelveticaNeue)
    val regular12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)
    val light12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light, fontFamily = HelveticaNeue)

    // MARK: - 10
    val regular10 = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal, fontFamily = HelveticaNeue)
    val light10 = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Light, fontFamily = HelveticaNeue)
    val thin10 = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Thin, fontFamily = HelveticaNeue)

    // MARK: - Color variants
    fun withColor(base: TextStyle, color: Color?): TextStyle = base.copy(color = color ?: AppColor.TextColor)
    // Predefined combinations
    val title = semiBold24.copy(color = AppColor.TextPrimary)
    val subtitle = regular16.copy(color = AppColor.TextHint)
    val button = semiBold16.copy(color = AppColor.White)
    val caption = regular12.copy(color = AppColor.TextHint)
}
