package com.example.rowcolumntest.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.rowcolumntest.ui.theming.AppTheme
import com.example.rowcolumntest.ui.theming.themeColorSurface
import com.example.rowcolumntest.ui.theming.themeElevationLow
import com.example.rowcolumntest.ui.theming.themeSpacingMd
import com.example.rowcolumntest.ui.theming.themeSpacingXs

/**
 * The Bottom Navigation bar composable.
 * Uses Material3 NavigationBar under the hood and custom Badge overlay.
 */
@Composable
fun AppBottomNavigation(
    items: List<BottomNavItem>,
    selectedItemId: String,
    onItemSelected: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
    elevation: Dp = themeElevationLow()
) {
    Surface(
        tonalElevation = elevation,
        color = themeColorSurface(),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = themeSpacingMd(), vertical = themeSpacingXs()),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // simple layout: evenly distribute items
            val itemWeight = 1f / items.size
            items.forEach { item ->
                Box(
                    modifier = Modifier
                        .weight(itemWeight)
                        .height(AppTheme.spacing.spacing56),
                    contentAlignment = Alignment.Center
                ) {
                    NavigationBarItemWithBadge(
                        item = item,
                        selected = selectedItemId == item.id,
                        onClick = { onItemSelected(item) }
                    )
                }
            }
        }
    }
}