package com.example.rowcolumntest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.rowcolumntest.ui.theming.themeColorOnPrimary
import com.example.rowcolumntest.ui.theming.themeColorPrimary
import com.example.rowcolumntest.ui.theming.themeElevationMedium
import com.example.rowcolumntest.ui.theming.themeSpacingLg
import com.example.rowcolumntest.ui.theming.themeTypographyBody

/**
 * High-level scaffold that places a center FAB and a Bottom Navigation with notch support.
 *
 * - fabContent: composable content of the FAB (icon + optional text if extended)
 * - fabSize / fabBottomPadding allow tuning for material 3 comfortable layout
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavScaffold(
    navItems: List<BottomNavItem>,
    selectedItemId: String,
    onItemSelected: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit,
    fabIsExtended: Boolean = false,
    fabSize: Dp = themeSpacingLg(),
    fabContent: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    onSettingsClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier, floatingActionButton = {
            if (fabIsExtended) {
                ExtendedFloatingActionButton(
                    text = { Text("Action", style = themeTypographyBody(), color = themeColorOnPrimary()) },
                    onClick = onFabClick,
                    icon = fabContent,
                    containerColor = themeColorPrimary(),
                    contentColor = themeColorOnPrimary(),
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = themeElevationMedium())
                )
            } else {
                FloatingActionButton(
                    onClick = onFabClick,
                    containerColor = themeColorPrimary(),
                    contentColor = themeColorOnPrimary(),
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = themeElevationMedium()),
                    modifier = Modifier.size(fabSize)
                ) {
                    fabContent()
                }
            }
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = themeColorPrimary(),
                    scrolledContainerColor = themeColorPrimary(),
                    navigationIconContentColor = themeColorOnPrimary(),
                    titleContentColor = themeColorOnPrimary(),
                    actionIconContentColor = themeColorOnPrimary(),
                ),
                title = {
                    Text("Android", style = themeTypographyBody(), color = themeColorOnPrimary())
                }, modifier = Modifier.fillMaxWidth(),
                actions = {
                    Box(modifier = Modifier.padding(themeSpacingLg())) {
                        Icon(
                            modifier = Modifier.clickable {
                                onSettingsClick
                            },
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = themeColorOnPrimary()
                        )
                    }
                }
            )
        }, floatingActionButtonPosition = FabPosition.End, bottomBar = {
            AppBottomNavigation(
                items = navItems, selectedItemId = selectedItemId, onItemSelected = onItemSelected, modifier = Modifier.fillMaxWidth()
            )
        }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content(innerPadding)
        }
    }
}