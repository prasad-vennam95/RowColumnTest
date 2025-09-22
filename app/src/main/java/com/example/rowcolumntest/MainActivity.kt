package com.example.rowcolumntest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.rowcolumntest.ui.theming.AppTheme
import com.example.rowcolumntest.ui.theming.themeColorOnPrimary
import com.example.rowcolumntest.ui.theming.themeColorPrimary
import com.example.rowcolumntest.ui.theming.themeElevationHigh
import com.example.rowcolumntest.ui.theming.themeElevationLow
import com.example.rowcolumntest.ui.theming.themeElevationMedium
import com.example.rowcolumntest.ui.theming.themeElevationNone
import com.example.rowcolumntest.ui.theming.themeSpacingLg
import com.example.rowcolumntest.ui.theming.themeSpacingMd
import com.example.rowcolumntest.ui.theming.themeStrokeThin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
        )
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Card(
                            shape = RoundedCornerShape(size = themeSpacingLg()),
                            modifier = Modifier.padding(themeSpacingLg()),
                            colors = CardDefaults.cardColors(
                                containerColor = themeColorPrimary(), contentColor = themeColorOnPrimary()
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = themeElevationLow(), pressedElevation = themeElevationMedium(), focusedElevation = themeElevationHigh(), hoveredElevation = themeElevationNone()
                            ),
                            border = BorderStroke(
                                themeStrokeThin(), color = themeColorOnPrimary()
                            )

                        ) {
                            Text(
                                text = "Hello World", modifier = Modifier.padding(themeSpacingMd()),
                                style = AppTheme.typography.h2
                            )
                        }
                    }
                }
            }
        }
    }
}