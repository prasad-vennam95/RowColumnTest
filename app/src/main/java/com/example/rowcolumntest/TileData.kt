package com.example.rowcolumntest

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

// Data classes for our content
data class TileData(
    val id: Int,
    val title: String,
    val subtitle: String,
    val color: Color,
)

data class RowData(
    val id: Int,
    val title: String,
    val tiles: List<TileData>,
)

fun generateSampleDataa(): List<RowData> {
    val colors = listOf(
        Color(0xFF6366F1), // Indigo
        Color(0xFF8B5CF6), // Violet
        Color(0xFF06B6D4), // Cyan
        Color(0xFF10B981), // Emerald
        Color(0xFFF59E0B), // Amber
        Color(0xFFEF4444), // Red
        Color(0xFFEC4899), // Pink
        Color(0xFF84CC16), // Lime
        Color(0xFF3B82F6), // Blue
        Color(0xFFDB2777), // Rose
        Color(0xFF22D3EE), // Sky
        Color(0xFF14B8A6), // Teal
        Color(0xFFF97316), // Orange
        Color(0xFF4ADE80), // Green,
        Color(0xFF0EA5E9), // Light Blue
        Color(0xFF1E293B), // Slate
        Color(0xFF475569), // Cool Gray
        Color(0xFFA855F7), // Purple Accent
        Color(0xFFD946EF), // Magenta
        Color(0xFF65A30D), // Olive Green
        Color(0xFFCA8A04), // Mustard
        Color(0xFFEA580C), // Deep Orange
        Color(0xFFBE123C), // Crimson
        Color(0xFF15803D), // Forest Green
        Color(0xFF0891B2), // Deep Cyan
        Color(0xFF701A75)  // Dark Violet
    )

    val categories = listOf(
        "Entertainment", "Technology", "Sports", "Music", "Travel", "Food", "Fashion", "Science", "Art", "Gaming", "Health", "Education", "Finance", "Nature", "History", "Culture", "Movies", "Books", "Photography", "Fitness", "DIY", "Pets", "Automotive", "Gardening", "Crafts"
    )

    val adjectives = listOf(
        "Amazing", "Incredible", "Awesome", "Fantastic", "Cool", "Epic", "Brilliant", "Stunning", "Perfect", "Ultimate", "Vibrant", "Dynamic", "Charming", "Elegant", "Funky", "Sleek", "Bold", "Radiant", "Lively", "Quirky", "Trendy", "Hip", "Fresh", "Snazzy", "Dazzling", "Glamorous"
    )

    return categories.mapIndexed { categoryIndex, category ->
        val tileCount = Random.nextInt(8, 30)
        val tiles = (0 until tileCount).map { tileIndex ->
            TileData(
                id = categoryIndex * 100 + tileIndex,
                title = "${adjectives.random()} $category",
                subtitle = "Item #${tileIndex + 1}",
                color = colors.random()
            )
        }

        RowData(
            id = categoryIndex,
            title = "$category Collection",
            tiles = tiles
        )
    }
}