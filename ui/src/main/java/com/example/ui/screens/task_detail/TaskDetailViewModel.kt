package com.example.ui.screens.task_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.navigation.Screen.TaskDetail.KEY_TASK_ID
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class TaskDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val taskId: Int = savedStateHandle.get<String>(KEY_TASK_ID)?.toInt()
        ?: throw RuntimeException("param $KEY_TASK_ID is null")

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        updateStateWithError(throwable)
    }

    private val _screenState: MutableStateFlow<TaskDetailScreenState> =
        MutableStateFlow(TaskDetailScreenState.Loading)


    init {
        viewModelScope.launch(exceptionHandler) {

        }
    }


    fun getScreenState(): StateFlow<TaskDetailScreenState> = _screenState.asStateFlow()

    private fun updateStateWithError(throwable: Throwable) {
        _screenState.update {
            TaskDetailScreenState.Error(throwable.toString())
        }
    }

}