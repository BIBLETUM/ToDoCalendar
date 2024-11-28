package com.example.data

import com.example.data.remote.api.ApiService
import com.example.data.remote.mappers.ITaskMapper
import com.example.domain.models.Task
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TaskRepositoryImpl(
    private val apiService: ApiService,
    private val taskMapper: ITaskMapper,
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return flow {
            val tasks = apiService.getTasks().tasks.map { taskDto ->
                taskMapper.mapTaskDtoToDomain(taskDto)
            }
            emit(tasks)
        }
    }

    override suspend fun addTask(task: Task) {
        val taskDto = taskMapper.mapTaskDomainToDto(task)
        apiService.addTask(taskDto)
    }
}