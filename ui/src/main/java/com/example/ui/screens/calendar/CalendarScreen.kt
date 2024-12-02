package com.example.ui.screens.calendar

import android.util.Log
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

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
            Log.d("Calendar", "Selected date: $selectedDate")
        }
    }
    DatePicker(
        state = datePickerState,
    )
}
