package com.example.rowcolumntest.ui.theming

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

@Composable
fun themeSpacingXs(): Dp = AppTheme.spacing.xs

@Composable
fun themeSpacingMd(): Dp = AppTheme.spacing.md

@Composable
fun themeSpacingLg(): Dp = AppTheme.spacing.lg

@Composable
fun themeSpacingXl(): Dp = AppTheme.spacing.xl

@Composable
fun themeColorPrimary() = AppTheme.colors.primary

@Composable
fun themeColorOnPrimary() = AppTheme.colors.onPrimary

@Composable
fun themeColorSecondary() = AppTheme.colors.secondary

@Composable
fun themeColorOnSecondary() = AppTheme.colors.onSecondary

@Composable
fun themeColorBackground() = AppTheme.colors.background

@Composable
fun themeColorOnBackground() = AppTheme.colors.onBackground

@Composable
fun themeColorSurface() = AppTheme.colors.surface

@Composable
fun themeColorOnSurface() = AppTheme.colors.onSurface

@Composable
fun themeColorError() = AppTheme.colors.error

@Composable
fun themeElevationLow() = AppTheme.elevation.low

@Composable
fun themeElevationMedium() = AppTheme.elevation.medium

@Composable
fun themeElevationHigh() = AppTheme.elevation.high

@Composable
fun themeElevationNone() = AppTheme.elevation.none

@Composable
fun themeStrokeThin() = AppTheme.stroke.thin

@Preview
@Composable
fun PreviewCard() {
    Card(
        shape = RoundedCornerShape(size = themeSpacingXs()), modifier = Modifier.padding(themeSpacingXs()), colors = CardDefaults.cardColors(
            containerColor = themeColorPrimary(), contentColor = themeColorOnPrimary()
        ), elevation = CardDefaults.cardElevation(
            defaultElevation = themeElevationLow(), pressedElevation = themeElevationMedium(), focusedElevation = themeElevationHigh(), hoveredElevation = themeElevationNone()
        ), border = BorderStroke(
            themeStrokeThin(), color = themeColorOnPrimary()
        )

    ) {
        Text(text = "Hello World", modifier = Modifier.padding(themeSpacingXs()))
    }
}

@Composable
@Preview
fun PreviewCard2() {
    Card(
        shape = RoundedCornerShape(size = themeSpacingXs()), modifier = Modifier.padding(themeSpacingXs()), colors = CardDefaults.cardColors(
            containerColor = themeColorSecondary(), contentColor = themeColorOnPrimary()
        )
    ) {
        Text(text = "Hello World", modifier = Modifier.padding(themeSpacingXs()))
    }
}

