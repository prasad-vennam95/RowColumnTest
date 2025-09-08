//package com.example.rowcolumntest
//
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.nestedscroll.NestedScrollSource
//import androidx.compose.ui.input.nestedscroll.nestedScroll
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//// Custom nested scroll connection for fine-tuned gesture handling
//class CustomNestedScrollConnection : androidx.compose.ui.input.nestedscroll.NestedScrollConnection {
//    override fun onPreScroll(
//        available: Offset,
//        source: NestedScrollSource,
//    ): Offset {
//        // Allow horizontal scrolls to be consumed by LazyRow first
//        return Offset.Zero
//    }
//}
//
//@Composable
//fun NestedScrollApp(
//    modifier: Modifier = Modifier,
//) {
//    // Generate sample data
//    val rowsData = remember { generateSampleData() }
//
//    // Column scroll state
//    val columnScrollState = rememberLazyListState()
//
//    // Nested scroll connection
//    val nestedScrollConnection = remember { CustomNestedScrollConnection() }
//
//    // Debug state
//    var gestureDebugInfo by remember { mutableStateOf("Ready") }
//
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .background(Color(0xFFF5F5F5))
//    ) {
//        // Header with debug info
//        Surface(
//            modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary, shadowElevation = 4.dp
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(
//                    text = "Nested Scroll Demo", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
//                )
//                Text(
//                    text = "Debug: $gestureDebugInfo", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f)
//                )
//            }
//        }
//
//        // Main scrollable content
//        LazyColumn(
//            state = columnScrollState, modifier = Modifier
//                .fillMaxSize()
//                .nestedScroll(nestedScrollConnection), contentPadding = PaddingValues(vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(
//                items = rowsData, key = { it.id }) { rowData ->
//                RowSection(
//                    rowData = rowData, onGestureDebug = { info ->
//                        gestureDebugInfo = info
//                    })
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun NestedScrollAppPreview() {
//    NestedScrollApp()
//}