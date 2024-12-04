package com.example.ui.screens.task_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.task_detail.IGetTaskDetailFlowUseCase
import com.example.domain.use_cases.task_detail.ISelectTaskUseCase
import com.example.ui.mappers.ITaskMapper
import com.example.ui.navigation.Screen.TaskDetail.KEY_TASK_ID
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class TaskDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getTaskDetailFlow: IGetTaskDetailFlowUseCase,
    private val selectTaskUseCase: ISelectTaskUseCase,
    private val taskMapper: ITaskMapper,
) : ViewModel() {

    private val taskId: Int = savedStateHandle.get<String>(KEY_TASK_ID)?.toInt()
        ?: throw RuntimeException("param $KEY_TASK_ID is null")

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        updateStateWithError(throwable)
    }

    private val _screenState: MutableStateFlow<TaskDetailScreenState> =
        MutableStateFlow(TaskDetailScreenState.Loading)


    init {
        loadTaskDetails()
    }

    fun getScreenState(): StateFlow<TaskDetailScreenState> = _screenState.asStateFlow()

    private fun loadTaskDetails() {
        viewModelScope.launch(exceptionHandler) {
            selectTask()
            observeTaskDetails()
        }
    }

    private fun selectTask() {
        selectTaskUseCase(taskId)
    }

    private suspend fun observeTaskDetails() {
        getTaskDetailFlow().map { taskUi ->
            taskMapper.mapTaskDomainToUi(taskUi)
        }.collect { task ->
            _screenState.update { TaskDetailScreenState.Content(task) }
        }
    }

    private fun updateStateWithError(throwable: Throwable) {
        _screenState.update {
            TaskDetailScreenState.Error(throwable.toString())
        }
    }

}