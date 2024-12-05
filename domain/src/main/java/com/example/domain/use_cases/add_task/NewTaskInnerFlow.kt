package com.example.domain.use_cases.add_task

import com.example.domain.models.Task
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class NewTaskInnerFlow {

    private val _newTaskFlow = MutableSharedFlow<Task>(replay = 1)

    fun observe(): SharedFlow<Task> = _newTaskFlow.asSharedFlow()

    fun addNewTask(task: Task) {
        _newTaskFlow.tryEmit(task)
    }

}