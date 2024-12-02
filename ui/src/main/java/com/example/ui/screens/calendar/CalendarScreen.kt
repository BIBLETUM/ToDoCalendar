@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ui.screens.calendar

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.Calendar
import com.example.ui.components.LoaderScreen
import com.example.ui.components.TimeTable
import com.example.ui.models.TaskUi
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CalendarScreenRoot(
    viewModel: CalendarViewModel = koinViewModel()
) {

    val screenState = viewModel.getScreenState().collectAsStateWithLifecycle()

    CalendarScreen(
        viewModel = viewModel,
        screenState = screenState,
    )
}

@Composable
private fun CalendarScreen(
    viewModel: CalendarViewModel,
    screenState: State<CalendarScreenState>,
) {
    val datePickerState = rememberDatePickerState()

    when (val state = screenState.value) {
        is CalendarScreenState.Initial -> CalendarScreenInitial(
            datePickerState = datePickerState,
            onDateSelected = viewModel::selectDate
        )

        is CalendarScreenState.DateSelected.Content -> CalendarScreenContent(
            datePickerState = datePickerState,
            tasks = state.tasks,
            onTaskClick = {},
            onDateSelected = viewModel::selectDate
        )

        is CalendarScreenState.DateSelected.Loading -> CalendarScreenLoading(
            datePickerState = datePickerState,
            onDateSelected = viewModel::selectDate
        )

        is CalendarScreenState.Error -> {
            Log.e("CalendarScreen", "Error: ${state.message}")
        }

    }

}

@Composable
private fun CalendarScreenLoading(
    datePickerState: DatePickerState,
    onDateSelected: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Calendar(datePickerState = datePickerState, onDateSelected = onDateSelected)
        LoaderScreen()
    }
}

@Composable
private fun CalendarScreenInitial(
    datePickerState: DatePickerState,
    onDateSelected: (Long) -> Unit
) {
    Calendar(datePickerState = datePickerState, onDateSelected = onDateSelected)
}

@Composable
private fun CalendarScreenContent(
    datePickerState: DatePickerState,
    tasks: Map<String, TaskUi>,
    onTaskClick: (TaskUi) -> Unit,
    onDateSelected: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Calendar(datePickerState = datePickerState, onDateSelected = onDateSelected)
        TimeTable(tasks = tasks, onTaskClick = onTaskClick)
    }
}



