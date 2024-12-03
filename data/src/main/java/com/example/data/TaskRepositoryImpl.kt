package com.example.data

import com.example.data.local.dao.TaskDao
import com.example.data.remote.api.ApiService
import com.example.data.remote.mappers.ITaskMapper
import com.example.domain.models.Task
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class TaskRepositoryImpl(
    private val apiService: ApiService,
    private val taskDao: TaskDao,
    private val taskMapper: ITaskMapper,
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return flow {
            val tasksFromApi = apiService.getTasks()

            tasksFromApi.tasks.map { taskDto ->
                taskMapper.mapTaskDtoToEntity(taskDto)
            }.forEach { task ->
                taskDao.insertTask(task)
            }

            emitAll(taskDao.getAllTasks().map { tasks ->
                tasks.map {
                    taskMapper.mapTaskEntityToDomain(it)
                }
            })
        }
    }

    override suspend fun addTask(task: Task) {
        val taskDto = taskMapper.mapTaskDomainToDto(task)
        apiService.addTask(taskDto)
    }
}