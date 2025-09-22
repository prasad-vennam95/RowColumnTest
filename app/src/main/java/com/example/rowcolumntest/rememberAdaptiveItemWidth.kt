package com.example.rowcolumntest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberAdaptiveItemWidth(
    totalWidth: Dp,
    visibleItems: Int,
    horizontalPadding: Dp = 16.dp,
    itemSpacing: Dp = 12.dp,
): Dp {
    return remember(totalWidth, visibleItems, horizontalPadding, itemSpacing) {
        val availableWidth = (totalWidth - (horizontalPadding * 2)).coerceAtLeast(0.dp)
        val totalSpacing = itemSpacing * (visibleItems - 1)
        val rawItemWidth = (availableWidth - totalSpacing) / visibleItems
        rawItemWidth.coerceAtLeast(0.dp)
    }
}