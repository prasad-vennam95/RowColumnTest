package com.example.rowcolumntest

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun RowSection(
    rowData: RowData,
    onGestureDebug: (String) -> Unit
) {
    val rowScrollState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = rowScrollState)
    val coroutineScope = rememberCoroutineScope()
    
    // Track scroll events for debugging
    LaunchedEffect(rowScrollState.isScrollInProgress) {
        if (rowScrollState.isScrollInProgress) {
            onGestureDebug("Horizontal scroll - Row ${rowData.id}")
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(vertical = 8.dp)
    ) {
        // Row header
        Text(
            text = rowData.title,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF333333)
        )
        
        // Horizontal scrollable row
        LazyRow(
            state = rowScrollState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            flingBehavior = snapBehavior
        ) {
            items(
                items = rowData.tiles,
                key = { it.id }
            ) { tile ->
                TileItem(
                    tile = tile,
                    onClick = {
                        onGestureDebug("Clicked tile ${tile.id} in row ${rowData.id}")
                        // Smooth scroll to show the interaction
                        coroutineScope.launch {
                            rowScrollState.animateScrollToItem(
                                rowData.tiles.indexOf(tile)
                            )
                        }
                    }
                )
            }
        }
        
        // Row footer with scroll position info
        Text(
            text = "Items: ${rowData.tiles.size} | Scroll pos: ${rowScrollState.firstVisibleItemIndex}",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}