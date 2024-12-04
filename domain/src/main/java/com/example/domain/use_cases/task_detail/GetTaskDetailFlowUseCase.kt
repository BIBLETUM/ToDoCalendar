package com.example.domain.use_cases.task_detail

import com.example.domain.models.Task
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

internal class GetTaskDetailFlowUseCase(
    private val taskIdFlow: TaskIdInnerFlow,
    private val taskRepository: TaskRepository,
) : IGetTaskDetailFlowUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(): Flow<Task> {
        return taskIdFlow.observe().flatMapLatest { taskId ->
            taskRepository.getTaskDetail(taskId)
        }
    }

}

interface IGetTaskDetailFlowUseCase {

    operator fun invoke(): Flow<Task>

}