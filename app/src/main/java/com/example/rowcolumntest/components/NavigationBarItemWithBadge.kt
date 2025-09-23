package com.example.rowcolumntest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rowcolumntest.ui.theming.themeColorOnSurface
import com.example.rowcolumntest.ui.theming.themeColorPrimary
import com.example.rowcolumntest.ui.theming.themeSpacingSm
import com.example.rowcolumntest.ui.theming.themeSpacingXs

@Composable
fun NavigationBarItemWithBadge(
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (selected) themeColorPrimary() else themeColorOnSurface()
    val alpha = if (selected) 1f else 0.85f

    Box(
        modifier = Modifier
            .padding(horizontal = themeSpacingSm())
            .wrapContentWidth()
            .clickable(onClick = onClick)
            .semantics { contentDescription = item.contentDescription ?: item.label },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = themeSpacingXs(), horizontal = themeSpacingSm())
        ) {
            Box(modifier = Modifier.size(24.dp), contentAlignment = Alignment.TopEnd) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription ?: item.label,
                    tint = contentColor.copy(alpha = alpha),
                    modifier = Modifier.align(Alignment.Center)
                )

                item.badgeCount?.let { count ->
                    // small offset so badge sits near top-right of icon
                    BadgeOverlay(count = count, modifier = Modifier.offset(x = 6.dp, y = (-4).dp))
                }
            }
        }
    }
}