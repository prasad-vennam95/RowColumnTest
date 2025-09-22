package com.example.rowcolumntest.ui.tokens

import androidx.compose.ui.graphics.Color

object DefaultDarkTokens {

    val colors = MyAppColors(
        primary = ColorTokens.BrandPrimary,
        onPrimary = Color.White,
        secondary = ColorTokens.BrandSecondary,
        onSecondary = Color.White,
        background = Color(0xFF0F1722),
        surface = Color(0xFF111827),
        onBackground = Color(0xFFCAD4E2),
        onSurface = Color(0xFFCAD4E2),
        error = ColorTokens.Error,
    )
}