package com.example.data.remote.api

import com.example.data.remote.models.TaskDto
import com.example.data.remote.models.TasksResponseDto
import retrofit2.http.GET
import retrofit2.http.POST

internal interface ApiService {

    @GET("tasks.json")
    suspend fun getTasks(): TasksResponseDto

    @POST("add_task")
    suspend fun addTask(task: TaskDto)

}