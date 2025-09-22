package com.example.rowcolumntest.ui.tokens

import androidx.compose.ui.graphics.Color

object DefaultLightTokens {
    val colors = MyAppColors(
        primary = ColorTokens.BrandPrimary,
        onPrimary = Color.White,
        secondary = ColorTokens.BrandSecondary,
        onSecondary = Color.White,
        background = ColorTokens.Neutral100,
        surface = Color.White,
        onBackground = ColorTokens.Neutral700,
        onSurface = ColorTokens.Neutral700,
        error = ColorTokens.Error,
    )
}