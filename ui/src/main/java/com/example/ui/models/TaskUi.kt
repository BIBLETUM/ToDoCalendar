package com.example.ui.models

import java.util.Date

internal data class TaskUi(
    val id: Int,
    val name: String,
    val description: String,
    val dateStart: Date,
    val dateFinish: Date,
)