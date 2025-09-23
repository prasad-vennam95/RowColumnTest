package com.example.rowcolumntest.ui.theming

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThemeSettingsView(
    themeSelection: ThemeSelection,
    onUpdate: (ThemeSelection) -> Unit
) {
    Column(Modifier.padding(16.dp)) {
        Text("Theme Settings", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dynamic colors")
            Spacer(Modifier.width(8.dp))
            Switch(
                checked = themeSelection.useDynamicColor,
                onCheckedChange = { onUpdate(themeSelection.copy(useDynamicColor = it)) }
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Mode:")
            Spacer(Modifier.width(8.dp))
        }
    }
}