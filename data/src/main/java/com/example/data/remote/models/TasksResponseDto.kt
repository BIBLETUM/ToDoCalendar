package com.example.data.remote.models

import com.google.gson.annotations.SerializedName

internal data class TasksResponseDto(
    @SerializedName("response") val tasks: List<TaskDto>
)
