package com.example.rowcolumntest.components

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class BottomNavItem(
    val id: String,
    val icon: ImageVector,
    val label: String,
    val badgeCount: Int? = null, // null -> no badge, 0 -> dot, >0 -> numeric badge
    val contentDescription: String? = null,
)