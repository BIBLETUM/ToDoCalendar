package com.example.domain.use_cases

import com.example.domain.models.Task
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

internal class GetTasksUseCase(
    private val taskRepository: TaskRepository
) : IGetTasksUseCase {

    override fun invoke(): Flow<List<Task>> {
        return taskRepository.getTasks()
    }

}

interface IGetTasksUseCase {
    operator fun invoke(): Flow<List<Task>>
}