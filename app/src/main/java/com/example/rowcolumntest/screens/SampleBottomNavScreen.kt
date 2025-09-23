package com.example.rowcolumntest.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import com.example.rowcolumntest.components.BottomNavItem
import com.example.rowcolumntest.components.BottomNavScaffold
import com.example.rowcolumntest.ui.theming.themeSpacingMd
import com.example.rowcolumntest.ui.theming.themeTypographyBody

@Composable
fun SampleBottomNavScreen(
    onSettingsClick: () -> Unit = {}
) {
    var selectedId by remember { mutableStateOf("home") }

    val navItems = listOf(
        BottomNavItem(id = "home", icon = Icons.Default.Home, label = "Home"),
        BottomNavItem(id = "search", icon = Icons.Default.Search, label = "Search", badgeCount = 0),
        BottomNavItem(id = "add", icon = Icons.Default.Add, label = "Add"),
        BottomNavItem(id = "notifications", icon = Icons.Default.Notifications, label = "Alerts", badgeCount = 7),
        BottomNavItem(id = "profile", icon = Icons.Default.Person, label = "Profile")
    )

    BottomNavScaffold(
        navItems = navItems,
        selectedItemId = selectedId,
        onItemSelected = { selectedId = it.id },
        fabContent = {
            Icon(Icons.Default.Add, contentDescription = "Add")
        },
        onFabClick = {
            // Handle FAB click
        },
        fabIsExtended = true,
        onSettingsClick = {
            onSettingsClick
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(themeSpacingMd()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Selected: $selectedId", style = themeTypographyBody())
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun Preview_SampleBottomNavScreen() {
    // Use your AppTheme when available; if preview, just call the sample to render
    if (LocalInspectionMode.current) {
        SampleBottomNavScreen()
    } else {
        SampleBottomNavScreen()
    }
}