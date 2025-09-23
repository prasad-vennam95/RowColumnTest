package com.example.rowcolumntest

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.datastore.preferences.preferencesDataStore
import com.example.rowcolumntest.repo.ThemeManager
import com.example.rowcolumntest.repo.ThemePrefsRepository
import com.example.rowcolumntest.screens.SampleBottomNavScreen
import com.example.rowcolumntest.ui.theming.AppTheme
import com.example.rowcolumntest.ui.theming.ThemeSelection

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    val Context.themeDataStore by preferencesDataStore(name = "theme_prefs")
    private val prefsRepository by lazy { ThemePrefsRepository(themeDataStore) }
    private val themeManager by lazy { ThemeManager(prefsRepository) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeSelection by themeManager.themeState.collectAsState(initial = ThemeSelection())
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            AppTheme(
                themeSelection = themeSelection,
                windowSizeClass = windowSizeClass,
            ) {
                SampleBottomNavScreen(
                    onSettingsClick = {
                       //
                    }
                )
            }
        }
    }
}