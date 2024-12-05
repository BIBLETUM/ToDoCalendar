package com.example.ui.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.add_task.INewTaskInteractor
import com.example.domain.use_cases.tasks_calendar.IGetTasksUseCase
import com.example.domain.use_cases.tasks_calendar.ISelectDateUseCase
import com.example.ui.mappers.ITaskMapper
import com.example.ui.models.TaskUi
import com.example.ui.screens.calendar.add_task.AddTaskDialogState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal class CalendarViewModel(
    private val newTaskInteractor: INewTaskInteractor,
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

    fun setTaskName(name: String) {
        updateDialogState { it.copy(name = name) }
    }

    fun setTaskDescription(description: String) {
        updateDialogState { it.copy(description = description) }
    }

    fun dismissDialog() {
        _screenState.update { state ->
            when (state) {
                is CalendarScreenState.DateSelected.Content -> state.copy(addTaskState = null)
                else -> state
            }
        }
    }


    fun setTaskTimeInterval(timeInterval: String) {
        val (dateStart, dateFinish) = parseTimeInterval(timeInterval) ?: return

        _screenState.update { state ->
            when (state) {
                is CalendarScreenState.DateSelected.Content -> state.copy(
                    addTaskState = AddTaskDialogState(
                        name = "",
                        description = "",
                        dateStart = dateStart,
                        dateFinish = dateFinish
                    )
                )

                else -> state
            }
        }
    }

    fun saveNewTask() {
        val currentContent = getDialogState() ?: return
        if (!currentContent.getIsButtonEnabled()) return

        val dateStart = currentContent.dateStart ?: return
        val dateFinish = currentContent.dateFinish ?: return

        newTaskInteractor.addNewTask(
            taskMapper.mapTaskUiToDomain(
                TaskUi(
                    id = 0,
                    name = currentContent.name,
                    description = currentContent.description,
                    dateStart = dateStart,
                    dateFinish = dateFinish
                )
            )
        )
        dismissDialog()
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
        val tasksMap: Map<String, TaskUi> = tasks.flatMap { task ->
            val startHour = task.dateStart.hours
            val endHour = task.dateFinish.hours

            (startHour until endHour).map { hour ->
                val timeSlot = "%02d:00-%02d:00".format(hour, hour + 1)
                timeSlot to task
            }
        }.toMap()

        _screenState.update { state ->
            when (state) {
                is CalendarScreenState.DateSelected.Loading -> {
                    CalendarScreenState.DateSelected.Content(tasksMap, state.selectedDate)
                }

                is CalendarScreenState.DateSelected.Content -> {
                    CalendarScreenState.DateSelected.Content(tasksMap, state.selectedDate)
                }

                else -> state
            }
        }
    }

    private fun parseTimeInterval(timeInterval: String): Pair<Date?, Date?>? {
        val timeParts = timeInterval.split("-").map { it.trim() }
        if (timeParts.size != 2) return null

        return try {
            val currentContent = getContentScreenState() ?: return null
            val currentDate = Date(currentContent.selectedDate)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val dateOnlyFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateOnly = dateOnlyFormat.format(currentDate)

            val dateStart = dateFormat.parse("$dateOnly ${timeParts[0]}")
            val dateFinish = dateFormat.parse("$dateOnly ${timeParts[1]}")

            dateStart to dateFinish
        } catch (e: Exception) {
            _screenState.update { CalendarScreenState.Error(e.toString()) }
            null
        }
    }

    private fun updateDialogState(update: (AddTaskDialogState) -> AddTaskDialogState) {
        val currentState = _screenState.value
        if (currentState is CalendarScreenState.DateSelected.Content) {
            val currentDialogState = currentState.addTaskState
            if (currentDialogState != null) {
                val updatedDialogState = update(currentDialogState)
                _screenState.value = currentState.copy(addTaskState = updatedDialogState)
            }
        }
    }

    private fun getDialogState(): AddTaskDialogState? {
        return (_screenState.value as? CalendarScreenState.DateSelected.Content)?.addTaskState
    }

    private fun getContentScreenState(): CalendarScreenState.DateSelected.Content? {
        return (_screenState.value as? CalendarScreenState.DateSelected.Content)
    }

    private fun updateStateWithError(throwable: Throwable) {
        _screenState.update {
            CalendarScreenState.Error(throwable.toString())
        }
    }
}
