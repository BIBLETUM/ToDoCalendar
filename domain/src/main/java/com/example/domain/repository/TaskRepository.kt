package com.example.domain.repository

import com.example.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    suspend fun addTask(task: Task)

}