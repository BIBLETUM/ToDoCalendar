package com.example.domain.use_cases.task_detail

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class TaskIdInnerFlow {

    private val _selectedTaskId = MutableSharedFlow<Int>(replay = 1)

    fun selectTask(taskId: Int) {
        _selectedTaskId.tryEmit(taskId)
    }

    fun observe(): SharedFlow<Int> = _selectedTaskId.asSharedFlow()

}