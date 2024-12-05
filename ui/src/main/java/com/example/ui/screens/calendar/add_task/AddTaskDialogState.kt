package com.example.ui.screens.calendar.add_task

import java.util.Date

internal data class AddTaskDialogState(
    val name: String,
    val description: String,
    val dateStart: Date?,
    val dateFinish: Date?,
) {
    fun getIsButtonEnabled(): Boolean {
        return name.isNotEmpty() && description.isNotEmpty() && dateStart != null && dateFinish != null
    }
}

