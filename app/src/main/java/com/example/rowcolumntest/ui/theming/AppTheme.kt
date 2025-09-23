package com.example.rowcolumntest.ui.theming

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.rowcolumntest.ui.tokens.DefaultDarkTokens
import com.example.rowcolumntest.ui.tokens.DefaultLightTokens
import com.example.rowcolumntest.ui.tokens.ElevationTokens
import com.example.rowcolumntest.ui.tokens.StrokeTokens
import com.example.rowcolumntest.ui.tokens.TypographyTokens

@Composable
fun AppTheme(
    themeSelection: ThemeSelection,
    windowSizeClass: WindowSizeClass? = null,
    content: @Composable () -> Unit,
) {
    val systemDark = isSystemInDarkTheme()
    val darkTheme = when (themeSelection.themeMode) {
        ThemeMode.SYSTEM -> systemDark
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }
    val dynamicAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = if (themeSelection.useDynamicColor && dynamicAvailable) {
        if (darkTheme) dynamicDarkColorScheme(LocalContext.current)
        else dynamicLightColorScheme(LocalContext.current)
    } else {
        val appColors = if (darkTheme) DefaultDarkTokens.colors else DefaultLightTokens.colors
        if (!darkTheme) {
            lightColorScheme(
                primary = appColors.primary,
                onPrimary = appColors.onPrimary,
                secondary = appColors.secondary,
                background = appColors.background,
                surface = appColors.surface,
                onBackground = appColors.onBackground,
                onSurface = appColors.onSurface,
                error = appColors.error
            )
        } else {
            darkColorScheme(
                primary = appColors.primary,
                onPrimary = appColors.onPrimary,
                secondary = appColors.secondary,
                background = appColors.background,
                surface = appColors.surface,
                onBackground = appColors.onBackground,
                onSurface = appColors.onSurface,
                error = appColors.error
            )
        }
    }

    val spacing = when (windowSizeClass?.widthSizeClass) {
        WindowWidthSizeClass.Compact -> SpacingCompact
        WindowWidthSizeClass.Medium -> SpacingMedium
        WindowWidthSizeClass.Expanded -> SpacingExpanded
        else -> SpacingMedium
    }

    val appColors = if (themeSelection.accentColorArgb != null) {
        val accentColor = Color(themeSelection.accentColorArgb)
        val base = if (darkTheme) DefaultDarkTokens.colors else DefaultLightTokens.colors
        base.copy(primary = accentColor)
    } else {
        if (darkTheme) DefaultDarkTokens.colors else DefaultLightTokens.colors
    }

    val themeValues = AppThemeValues(
        colors = appColors,
        spacing = spacing,
        elevation = ElevationTokens,
        typography = TypographyTokens,
        stroke = StrokeTokens
    )

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