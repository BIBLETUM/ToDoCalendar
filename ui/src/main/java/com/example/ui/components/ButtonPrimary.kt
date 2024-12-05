package com.example.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.extensions.drawDiagonalGradientBackground
import com.example.ui.extensions.drawFilledBackground
import theme.ToDoCalendarTheme

@Composable
internal fun ButtonPrimary(
    modifier: Modifier = Modifier,
    @StringRes textResId: Int,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val gradientColors = ToDoCalendarTheme.colors.gradientViolet.toTypedArray()
    val disabledBackGroundColor = ToDoCalendarTheme.colors.backgroundDisabled

    Box(modifier = modifier
        .clip(RoundedCornerShape(16.dp))
        .clickable(enabled = enabled) {
            onClick()
        }
        .drawBehind {
            when (enabled) {
                true -> {
                    drawDiagonalGradientBackground(size = size, colorStops = gradientColors)
                }

                else -> {
                    drawFilledBackground(size = size, color = disabledBackGroundColor)
                }
            }
        }, contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            text = stringResource(id = textResId),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            color = if (enabled) Color.White else ToDoCalendarTheme.colors.buttonTextDisabled
        )
    }
}