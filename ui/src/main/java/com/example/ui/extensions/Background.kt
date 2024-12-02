package com.example.ui.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

internal fun DrawScope.drawDiagonalGradientBackground(
    size: Size,
    colorStops: Array<Pair<Float, Color>>
) {
    drawRect(
        brush = Brush.linearGradient(
            colorStops = colorStops,
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height)
        ), size = size
    )
}

internal fun DrawScope.drawFilledBackground(size: Size, color: Color) {
    drawRect(color = color, size = size)
}