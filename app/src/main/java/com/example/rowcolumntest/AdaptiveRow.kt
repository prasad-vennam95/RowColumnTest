package com.example.rowcolumntest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> AdaptiveRow(
    itemsInRow: List<T>,
    modifier: Modifier = Modifier,
    visibleItems: Int = 4,
    itemSpacing: Dp = 12.dp,
    horizontalPadding: Dp = 16.dp,
    content: @Composable (T, Modifier) -> Unit,
) {
    require(visibleItems > 0)

    BoxWithConstraints(modifier = modifier) {
        val itemWidth = rememberAdaptiveItemWidth(
            totalWidth = this.maxWidth,
            visibleItems = visibleItems,
            horizontalPadding = horizontalPadding,
            itemSpacing = itemSpacing
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(itemsInRow) { sport ->
                Box(modifier = Modifier.width(itemWidth)) {
                    content(sport, Modifier.fillMaxWidth())
                }
            }
        }
    }
}