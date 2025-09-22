package com.example.rowcolumntest.ui.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.rowcolumntest.ui.tokens.DefaultDarkTokens
import com.example.rowcolumntest.ui.tokens.DefaultLightTokens
import com.example.rowcolumntest.ui.tokens.ElevationTokens
import com.example.rowcolumntest.ui.tokens.MyAppColors
import com.example.rowcolumntest.ui.tokens.SpacingTokens
import com.example.rowcolumntest.ui.tokens.StrokeTokens
import com.example.rowcolumntest.ui.tokens.TypographyTokens

@Immutable
data class AppThemeValues(
    val colors: MyAppColors,
    val spacing: SpacingTokens = SpacingTokens,
    val elevation: ElevationTokens = ElevationTokens,
    val typography: TypographyTokens = TypographyTokens,
    val stroke: StrokeTokens = StrokeTokens,
)

private val LocalAppTheme = staticCompositionLocalOf {
    AppThemeValues(
        colors = DefaultLightTokens.colors
    )
}

object AppTheme {
    val colors: MyAppColors
        @Composable
        get() = LocalAppTheme.current.colors

    val spacing: SpacingTokens
        @Composable
        get() = LocalAppTheme.current.spacing

    val typography: TypographyTokens
        @Composable
        get() = LocalAppTheme.current.typography

    val elevation: ElevationTokens
        @Composable
        get() = LocalAppTheme.current.elevation

    val stroke: StrokeTokens
        @Composable
        get() = LocalAppTheme.current.stroke
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val appColors = if (darkTheme) DefaultDarkTokens.colors else DefaultLightTokens.colors

    val colorScheme = if (!darkTheme) {
        lightColorScheme(
            primary = appColors.primary,
            onPrimary = appColors.onPrimary,
            secondary = appColors.secondary,
            onSecondary = appColors.onSecondary,
            background = appColors.background,
            surface = appColors.surface,
            onBackground = appColors.onBackground,
            onSurface = appColors.onSurface,
            error = appColors.error,
        )
    } else {
        darkColorScheme(
            primary = appColors.primary,
            onPrimary = appColors.onPrimary,
            secondary = appColors.secondary,
            onSecondary = appColors.onSecondary,
            background = appColors.background,
            surface = appColors.surface,
            onBackground = appColors.onBackground,
            onSurface = appColors.onSurface,
            error = appColors.error,
        )
    }

    val themeValues = AppThemeValues(colors = appColors)

    CompositionLocalProvider(LocalAppTheme provides themeValues) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography(
                headlineLarge = TypographyTokens.h1,
                headlineMedium = TypographyTokens.h2,
                bodyLarge = TypographyTokens.body1,
                labelLarge = TypographyTokens.caption
            ),
            content = content
        )
    }
}