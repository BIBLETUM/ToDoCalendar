package com.example.ui.screens.add_task

import androidx.lifecycle.ViewModel
import com.example.domain.use_cases.add_task.INewTaskInteractor
import com.example.ui.mappers.ITaskMapper
import com.example.ui.models.TaskUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

internal class AddTaskViewModel(
    private val newTaskInteractor: INewTaskInteractor,
    private val taskMapper: ITaskMapper,
) : ViewModel() {

    private val exceptionHandle = CoroutineExceptionHandler { _, throwable ->
        _screenState.value = AddTaskScreenState.Error(throwable.message ?: "")
    }

    private val _screenState = MutableStateFlow<AddTaskScreenState>(AddTaskScreenState.Initial)

    init {
        setInitialContentState()
    }

    fun getScreenState(): StateFlow<AddTaskScreenState> = _screenState.asStateFlow()

    fun setTaskName(name: String) {
        val currentContent = getCurrentContent() ?: return
        _screenState.value = currentContent.copy(name = name)
    }

    fun setTaskDescription(description: String) {
        val currentContent = getCurrentContent() ?: return
        _screenState.value = currentContent.copy(description = description)
    }

    fun setDateStart(date: Long) {
        val currentContent = getCurrentContent() ?: return
        _screenState.value = currentContent.copy(dateStart = Date(date))
    }

    fun setDateFinish(date: Long) {
        val currentContent = getCurrentContent() ?: return
        _screenState.value = currentContent.copy(dateFinish = Date(date))
    }

    fun saveNewTask() {
        val currentContent = getCurrentContent() ?: return
        if (!currentContent.getIsButtonEnabled()) return

        currentContent.dateStart ?: return
        currentContent.dateFinish ?: return

        newTaskInteractor.addNewTask(
            taskMapper.mapTaskUiToDomain(
                TaskUi(
                    id = 0,
                    name = currentContent.name,
                    description = currentContent.description,
                    dateStart = currentContent.dateStart,
                    dateFinish = currentContent.dateFinish,
                )
            )
        )
    }

    private fun getCurrentContent(): AddTaskScreenState.Content? {
        return (_screenState.value as? AddTaskScreenState.Content)
    }

    private fun setInitialContentState() {
        _screenState.value = AddTaskScreenState.Content(
            name = "",
            description = "",
            dateStart = null,
            dateFinish = null,
        )
    }

}