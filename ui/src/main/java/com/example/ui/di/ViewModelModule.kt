package com.example.ui.di

import com.example.ui.screens.calendar.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::CalendarViewModel)
}