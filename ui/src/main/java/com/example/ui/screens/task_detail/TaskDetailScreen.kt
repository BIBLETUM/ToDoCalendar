package com.example.ui.screens.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.models.TaskUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
internal fun TaskDetailScreenRoot() {


}

@Composable
private fun TaskDetailScreenContent(
    task: TaskUi,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TaskHeader(title = task.name)
        TaskDescription(description = task.description)
        TaskDate(label = stringResource(R.string.date_start), date = task.dateStart)
        TaskDate(label = stringResource(R.string.date_finish), date = task.dateFinish)
    }
}

@Composable
private fun TaskHeader(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        text = title,
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Composable
private fun TaskDescription(modifier: Modifier = Modifier, description: String) {
    Text(
        modifier = modifier.padding(bottom = 8.dp),
        text = description,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
private fun TaskDate(modifier: Modifier = Modifier, label: String, date: Date) {
    Column(
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = formatTime(date),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}


private fun formatTime(date: Date): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return formatter.format(date)
}

