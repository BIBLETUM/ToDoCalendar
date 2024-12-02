package com.example.ui.screens.calendar

import com.example.ui.models.TaskUi

internal sealed class CalendarScreenState {

    data object Initial : CalendarScreenState()
    data class Error(val message: String) : CalendarScreenState()
    sealed class DateSelected() : CalendarScreenState() {
        data class Loading(val selectedDate: Long) : DateSelected()
        data class Content(val tasks: Map<String, TaskUi>, val selectedDate: Long) : DateSelected()
    }

}