package com.example.rowcolumntest.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.rowcolumntest.ui.theming.ThemeMode
import com.example.rowcolumntest.ui.theming.ThemeSelection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemePrefsRepository(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val KEY_THEME_MODE = intPreferencesKey("theme_mode")
        private val KEY_USE_DYNAMIC = booleanPreferencesKey("use_dynamic")
        private val KEY_ACCENT = intPreferencesKey("accent_color")
    }

    val themeSelectionFlow: Flow<ThemeSelection> = dataStore.data
        .map { prefs ->
            val modeInt = prefs[KEY_THEME_MODE] ?: ThemeMode.SYSTEM.ordinal
            val mode = ThemeMode.entries.toTypedArray().getOrElse(modeInt) { ThemeMode.SYSTEM }
            val useDynamic = prefs[KEY_USE_DYNAMIC] != false
            val accent = prefs[KEY_ACCENT]
            ThemeSelection(mode, useDynamic, accent)
        }

    suspend fun setThemeSelection(selection: ThemeSelection) {
        dataStore.edit { prefs ->
            prefs[KEY_THEME_MODE] = selection.themeMode.ordinal
            prefs[KEY_USE_DYNAMIC] = selection.useDynamicColor
            selection.accentColorArgb?.let { prefs[KEY_ACCENT] = it }
        }
    }
}