package com.example.domain.models

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val dateStart: Long,
    val dateFinish: Long,
)
