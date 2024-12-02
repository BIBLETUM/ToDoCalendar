package com.example.ui.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.tasks_calendar.IGetTasksUseCase
import com.example.domain.use_cases.tasks_calendar.ISelectDateUseCase
import com.example.ui.mappers.ITaskMapper
import com.example.ui.models.TaskUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CalendarViewModel(
    private val getTasksUseCase: IGetTasksUseCase,
    private val selectDateUseCase: ISelectDateUseCase,
    private val taskMapper: ITaskMapper,
) : ViewModel() {

    private val _screenState = MutableStateFlow<CalendarScreenState>(CalendarScreenState.Initial)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        updateStateWithError(throwable)
    }

    init {
        observeTasks()
    }

    fun getScreenState(): StateFlow<CalendarScreenState> = _screenState.asStateFlow()

    fun selectDate(date: Long) {
        updateStateWithLoading(date)
        selectDateUseCase(date)
    }

    private fun observeTasks() {
        viewModelScope.launch(exceptionHandler) {
            getTasksUseCase()
                .map { tasks -> tasks.map(taskMapper::mapTaskDomainToUi) }
                .collect { tasks -> updateStateWithTasks(tasks) }
        }
    }

    private fun updateStateWithLoading(selectedDate: Long) {
        _screenState.update { CalendarScreenState.DateSelected.Loading(selectedDate) }
    }

    private fun updateStateWithTasks(tasks: List<TaskUi>) {
        val tasksMap: Map<String, TaskUi> = tasks.associateBy { task ->
            val startHour = task.dateStart.hours
            val endHour = task.dateFinish.hours
            "%02d:00-%02d:00".format(startHour, endHour)
        }

        _screenState.update { state ->
            when (state) {
                is CalendarScreenState.DateSelected.Loading -> {
                    CalendarScreenState.DateSelected.Content(tasksMap, state.selectedDate)
                }
                else -> state
            }
        }
    }


    private fun updateStateWithError(throwable: Throwable) {
        _screenState.update {
            CalendarScreenState.Error(throwable.toString())
        }
    }
}
