package com.example.data.remote.models

import com.google.gson.annotations.SerializedName

internal data class TaskDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("date_start") val dateStart: String,
    @SerializedName("date_finish") val dateFinish: String,
)
