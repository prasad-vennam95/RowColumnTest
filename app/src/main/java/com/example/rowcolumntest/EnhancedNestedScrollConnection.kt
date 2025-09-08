package com.example.rowcolumntest

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.abs

// Enhanced nested scroll connection with proper momentum handling
class EnhancedNestedScrollConnection(
    private val onHorizontalScrollStopped: () -> Unit = {},
) : NestedScrollConnection {

    private var isHorizontalScrollActive = false
    private var lastHorizontalVelocity = 0f
    private val velocityThreshold = 50f // Minimum velocity to consider as "fast scroll"

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val horizontalScroll = available.x
        val verticalScroll = available.y

        // Track horizontal scroll activity
        if (abs(horizontalScroll) > abs(verticalScroll) && abs(horizontalScroll) > 1f) {
            isHorizontalScrollActive = true
            lastHorizontalVelocity = horizontalScroll
        }

        // If we detect vertical scroll while horizontal was active, prioritize vertical
        if (isHorizontalScrollActive && abs(verticalScroll) > abs(horizontalScroll) && abs(verticalScroll) > 1f) {
            isHorizontalScrollActive = false
            onHorizontalScrollStopped()
            // Don't consume the vertical scroll, let LazyColumn handle it
            return Offset.Zero
        }

        return Offset.Zero
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource,
    ): Offset {
        // Reset horizontal scroll state when scroll ends
        if (available == Offset.Zero && consumed == Offset.Zero) {
            isHorizontalScrollActive = false
        }
        return Offset.Zero
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val horizontalVelocity = available.x
        val verticalVelocity = available.y

        // If there's significant vertical fling while horizontal was active, stop horizontal momentum
        if (isHorizontalScrollActive && abs(verticalVelocity) > abs(horizontalVelocity) && abs(verticalVelocity) > velocityThreshold) {
            isHorizontalScrollActive = false
            onHorizontalScrollStopped()
            // Don't consume the vertical fling
            return Velocity.Zero
        }

        return Velocity.Zero
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        isHorizontalScrollActive = false
        return Velocity.Zero
    }
}

@Composable
fun NestedScrollApp(
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current

    // Generate sample data
    val rowsData = remember { generateSampleDataa() }

    // Column scroll state
    val columnScrollState = rememberLazyListState()

    // Track horizontal scroll states for all rows
    val rowScrollStates = remember {
        mutableMapOf<Int, androidx.compose.foundation.lazy.LazyListState>()
    }

    // Debug state
    var gestureDebugInfo by remember { mutableStateOf("Ready") }

    // Enhanced nested scroll connection with momentum cancellation
    val nestedScrollConnection = remember {
        EnhancedNestedScrollConnection(
            onHorizontalScrollStopped = {
                gestureDebugInfo = "Horizontal scroll interrupted by vertical"
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header with debug info
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Fixed Nested Scroll Demo",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Debug: $gestureDebugInfo",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Text(
                    text = "Try: Fast horizontal scroll → immediate vertical scroll",
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }

        // Main scrollable content
        LazyColumn(
            state = columnScrollState,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = rowsData,
                key = { it.id }
            ) { rowData ->
                // Get or create scroll state for this row
                val rowScrollState = rowScrollStates.getOrPut(rowData.id) {
                    androidx.compose.foundation.lazy.LazyListState()
                }

                EnhancedRowSection(
                    rowData = rowData,
                    scrollState = rowScrollState,
                    onGestureDebug = { info ->
                        gestureDebugInfo = info
                    }
                )
            }
        }
    }
}

@Composable
fun EnhancedRowSection(
    rowData: RowData,
    scrollState: androidx.compose.foundation.lazy.LazyListState,
    onGestureDebug: (String) -> Unit,
) {
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)
    val coroutineScope = rememberCoroutineScope()

    // Enhanced scroll state tracking
    var isScrolling by remember { mutableStateOf(false) }
    var lastScrollTime by remember { mutableLongStateOf(0L) }

    // Track scroll events with timing
    LaunchedEffect(scrollState.isScrollInProgress) {
        val currentTime = System.currentTimeMillis()
        if (scrollState.isScrollInProgress && !isScrolling) {
            isScrolling = true
            lastScrollTime = currentTime
            onGestureDebug("Horizontal scroll started - Row ${rowData.id}")
        } else if (!scrollState.isScrollInProgress && isScrolling) {
            isScrolling = false
            onGestureDebug("Horizontal scroll ended - Row ${rowData.id}")
        }
    }

    // Create a custom nested scroll connection for this row
    val rowNestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val horizontalScroll = abs(available.x)
                val verticalScroll = abs(available.y)

                // If vertical scroll is detected while horizontal scrolling, stop horizontal scroll
                if (isScrolling && verticalScroll > horizontalScroll && verticalScroll > 2f) {
                    coroutineScope.launch {
                        // Stop the horizontal scroll immediately
                        scrollState.scrollToItem(scrollState.firstVisibleItemIndex)
                    }
                    isScrolling = false
                    onGestureDebug("Horizontal momentum cancelled - Row ${rowData.id}")
                }

                return Offset.Zero
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                val horizontalVelocity = abs(available.x)
                val verticalVelocity = abs(available.y)

                // Cancel horizontal fling if vertical fling is detected
                if (verticalVelocity > horizontalVelocity && verticalVelocity > 100f) {
                    // Stop any ongoing horizontal scroll animation
                    scrollState.scrollToItem(scrollState.firstVisibleItemIndex)
                    onGestureDebug("Horizontal fling cancelled - Row ${rowData.id}")
                }

                return Velocity.Zero
            }
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

        // Enhanced horizontal scrollable row
        LazyRow(
            state = scrollState,
            modifier = Modifier
                .fillMaxWidth()
                .nestedScroll(rowNestedScrollConnection),
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
                        coroutineScope.launch {
                            scrollState.animateScrollToItem(
                                rowData.tiles.indexOf(tile)
                            )
                        }
                    }
                )
            }
        }

        // Enhanced row footer with more info
        Text(
            text = "Items: ${rowData.tiles.size} | Scroll pos: ${scrollState.firstVisibleItemIndex} | Scrolling: $isScrolling",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FixedNestedScrollAppPreview() {
    NestedScrollApp()
}