package com.example.ui.screens.calendar

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import theme.ToDoCalendarTheme

@Composable
internal fun CalendarScreenRoot(
    viewModel: CalendarViewModel = koinViewModel()
) {


}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Calendar(
    modifier: Modifier = Modifier,
    datePickerState: DatePickerState = rememberDatePickerState(),
    onDateSelected: (Long) -> Unit = {},
) {

    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let { selectedDate ->
            onDateSelected(selectedDate)
        }
    }
    DatePicker(
        state = datePickerState,
        colors = DatePickerDefaults.colors().copy(
            containerColor = ToDoCalendarTheme.colors.secondary,
            todayContentColor = ToDoCalendarTheme.colors.primary,
            todayDateBorderColor = ToDoCalendarTheme.colors.primaryStroke,
            selectedDayContainerColor = ToDoCalendarTheme.colors.primary,
            selectedYearContainerColor = ToDoCalendarTheme.colors.primary,
            currentYearContentColor = ToDoCalendarTheme.colors.primary,
        )
    )
}
