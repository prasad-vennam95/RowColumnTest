package com.example.rowcolumntest.ui.tokens

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

object TypographyTokens {
    val defaultFont = FontFamily.SansSerif
    val h1 = TextStyle(fontFamily = defaultFont, fontSize = 28.sp)
    val h2 = TextStyle(fontFamily = defaultFont, fontSize = 22.sp)
    val body1 = TextStyle(fontFamily = defaultFont, fontSize = 16.sp)
    val caption = TextStyle(fontFamily = defaultFont, fontSize = 12.sp)
}