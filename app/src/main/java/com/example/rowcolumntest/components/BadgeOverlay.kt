package com.example.rowcolumntest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rowcolumntest.ui.theming.themeColorOnPrimary
import com.example.rowcolumntest.ui.theming.themeColorSecondary
import com.example.rowcolumntest.ui.theming.themeSpacingSm
import com.example.rowcolumntest.ui.theming.themeStrokeThin
import com.example.rowcolumntest.ui.theming.themeTypographyCaption

@Composable
fun BadgeOverlay(count: Int, modifier: Modifier = Modifier) {
    val badgeColor = themeColorSecondary()
    val contentColor = themeColorOnPrimary()

    if (count <= 0) {
        // dot
        Box(
            modifier = modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(badgeColor)
        )
    } else {
        val displayText = if (count > 99) "99+" else count.toString()
        Box(
            modifier = modifier
                .height(18.dp)
                .wrapContentWidth()
                .clip(RoundedCornerShape(9.dp))
                .background(badgeColor)
                .border(width = themeStrokeThin(), color = badgeColor, shape = RoundedCornerShape(9.dp))
                .padding(horizontal = themeSpacingSm() / 2, vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = displayText,
                style = themeTypographyCaption(),
                color = contentColor,
                fontSize = 10.sp,
                maxLines = 1
            )
        }
    }
}