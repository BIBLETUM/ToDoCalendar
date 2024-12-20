package com.example.ui.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import theme.ToDoCalendarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Calendar(
    modifier: Modifier = Modifier,
    datePickerState: DatePickerState = rememberDatePickerState(),
    onDateSelected: (Long) -> Unit,
) {
    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let { selectedDate ->
            val timestamp = selectedDate / MILLIS_IN_SECONDS
            onDateSelected(selectedDate)
        }
    }
    DatePicker(
        modifier = modifier,
        state = datePickerState,
        colors = DatePickerDefaults.colors().copy(
            containerColor = Color.White,
            todayContentColor = ToDoCalendarTheme.colors.primary,
            todayDateBorderColor = ToDoCalendarTheme.colors.primaryStroke,
            selectedDayContainerColor = ToDoCalendarTheme.colors.primary,
            selectedYearContainerColor = ToDoCalendarTheme.colors.primary,
            currentYearContentColor = ToDoCalendarTheme.colors.primary,
        )
    )
}

private const val MILLIS_IN_SECONDS = 1000