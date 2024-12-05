package com.example.ui.di

import com.example.ui.screens.add_task.AddTaskViewModel
import com.example.ui.screens.calendar.CalendarViewModel
import com.example.ui.screens.task_detail.TaskDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::CalendarViewModel)
    viewModelOf(::TaskDetailViewModel)
    viewModelOf(::AddTaskViewModel)
}