package com.example.domain.use_cases.tasks_calendar

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class SelectedDateInnerFlow {

    private val _selectedDate = MutableSharedFlow<Long>(replay = 1)

    fun selectDate(date: Long) {
        _selectedDate.tryEmit(date)
    }

    fun observe(): SharedFlow<Long> = _selectedDate.asSharedFlow()

}