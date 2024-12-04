package com.example.ui.screens.task_detail

import com.example.ui.models.TaskUi

internal sealed class TaskDetailScreenState {

    data class Error(val errorMessage: String) : TaskDetailScreenState()
    data object Loading : TaskDetailScreenState()
    data class Content(val taskUi: TaskUi) : TaskDetailScreenState()

}