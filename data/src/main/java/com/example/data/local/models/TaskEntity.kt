package com.example.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
internal data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val dateStart: Long,
    val dateFinish: Long
)