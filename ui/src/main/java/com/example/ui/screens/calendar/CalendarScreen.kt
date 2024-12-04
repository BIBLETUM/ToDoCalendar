@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ui.screens.calendar

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CalendarScreenRoot(
    viewModel: CalendarViewModel = koinViewModel(),
    navigateToTaskDetail: (Int) -> Unit,
) {

    val screenState = viewModel.getScreenState().collectAsStateWithLifecycle()

    CalendarScreen(
        viewModel = viewModel,
        screenState = screenState,
        navigateToTaskDetail = navigateToTaskDetail,
    )
}

@Composable
private fun CalendarScreen(
    viewModel: CalendarViewModel,
    screenState: State<CalendarScreenState>,
    navigateToTaskDetail: (Int) -> Unit,
) {
    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Calendar(
            modifier = Modifier.padding(top = 16.dp),
            datePickerState = datePickerState,
            onDateSelected = viewModel::selectDate
        )
        when (val state = screenState.value) {
            is CalendarScreenState.Initial -> {}

            is CalendarScreenState.DateSelected.Content -> {
                TimeTable(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 20.dp),
                    tasks = state.tasks,
                    onTaskClick = { task ->
                        navigateToTaskDetail(task.id)
                    })
            }

            is CalendarScreenState.DateSelected.Loading -> {
                LoaderScreen()
            }

            is CalendarScreenState.Error -> {
                Log.e("CalendarScreen", "Error: ${state.message}")
            }

        }
    }

}