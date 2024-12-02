package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.extensions.drawDiagonalGradientBackground
import com.example.ui.extensions.drawFilledBackground
import com.example.ui.models.TaskUi
import theme.ToDoCalendarTheme
import java.util.Date

@Composable
@Preview(showBackground = true)
fun PreviewTimeTable() {
    // Примерные данные задач
    val tasks = mapOf(
        "09:00-10:00" to TaskUi(
            id = 1,
            name = "Организация командного собрания",
            description = "Team meeting",
            dateStart = Date(),
            dateFinish = Date()
        ),
        "14:00-15:00" to TaskUi(
            id = 2,
            name = "Пресс качат",
            description = "Gym session",
            dateStart = Date(),
            dateFinish = Date()
        ),
        "18:00-19:00" to TaskUi(
            id = 3,
            name = "Анжумания",
            description = "Dinner with family",
            dateStart = Date(),
            dateFinish = Date()
        )
    )

    // Предпросмотр компонента
    TimeTable(
        timeIntervals = defaultTimeIntervals,
        tasks = tasks,
        onTaskClick = { task -> println("Clicked task: $task") }
    )
}

@Composable
internal fun TimeTable(
    modifier: Modifier = Modifier,
    timeIntervals: List<String> = defaultTimeIntervals,
    tasks: Map<String, TaskUi>,
    columns: Int = 3,
    onTaskClick: (TaskUi) -> Unit = {},
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier,
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(timeIntervals) { timeSlot ->
            val task = tasks[timeSlot]

            TaskCell(name = task?.name, timeInterval = timeSlot) {
                task?.let(onTaskClick)
            }

        }
    }
}

@Composable
private fun TaskCell(
    modifier: Modifier = Modifier,
    name: String?,
    timeInterval: String,
    onClick: () -> Unit,
) {
    val enabled = name != null
    val gradientColors = ToDoCalendarTheme.colors.gradientViolet.toTypedArray()
    val disabledBackGroundColor = ToDoCalendarTheme.colors.backgroundDisabled
    val textColor = if (name != null) ToDoCalendarTheme.colors.lightGray else Color.Black
    Box(
        modifier = modifier
            .heightIn(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .drawBehind {
                when (enabled) {
                    true -> {
                        drawDiagonalGradientBackground(size = size, colorStops = gradientColors)
                    }

                    else -> {
                        drawFilledBackground(size = size, color = disabledBackGroundColor)
                    }
                }
            }
            .padding(8.dp)
            .clickable(enabled = enabled, onClick = onClick), contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            name?.let {
                Text(
                    text = name,
                    color = textColor,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = timeInterval,
                color = textColor,
                maxLines = 1,
            )
        }
    }
}

private val defaultTimeIntervals = List(24) { hour ->
    val start = "%02d:00".format(hour)
    val end = "%02d:00".format((hour + 1) % 24)
    "$start-$end"
}