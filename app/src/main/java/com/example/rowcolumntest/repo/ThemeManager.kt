package com.example.rowcolumntest.repo

import com.example.rowcolumntest.ui.theming.ThemeSelection
import kotlinx.coroutines.flow.Flow

class ThemeManager(private val prefsRepository: ThemePrefsRepository) {
    val themeState: Flow<ThemeSelection> = prefsRepository.themeSelectionFlow
    suspend fun updateTheme(themeSelection: ThemeSelection) {
        prefsRepository.setThemeSelection(themeSelection)
    }
}