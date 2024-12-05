package com.example.ui.screens.add_task

import java.util.Date

internal sealed class AddTaskScreenState {

    data object Initial : AddTaskScreenState()

    data class Content(
        val name: String,
        val description: String,
        val dateStart: Date?,
        val dateFinish: Date?,
    ) : AddTaskScreenState() {
        fun getIsButtonEnabled(): Boolean {
            return name.isNotEmpty() && description.isNotEmpty() && dateStart != null && dateFinish != null
        }
    }

    data class Error(val errorMessage: String) : AddTaskScreenState()
}