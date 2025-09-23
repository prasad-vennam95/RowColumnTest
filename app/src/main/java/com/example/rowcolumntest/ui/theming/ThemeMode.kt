package com.example.rowcolumntest.ui.theming

enum class ThemeMode { SYSTEM, LIGHT, DARK }

data class ThemeSelection(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val useDynamicColor: Boolean = true,
    val accentColorArgb: Int? = null
)