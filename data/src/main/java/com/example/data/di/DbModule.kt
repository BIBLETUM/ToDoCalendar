package com.example.data.di

import android.app.Application
import com.example.data.local.AppDataBase
import com.example.data.local.dao.TaskDao
import org.koin.dsl.module

val dbModule = module {
    single { provideDataBase(get()) }

    single { provideTaskDao(get()) }
}

private fun provideDataBase(application: Application): AppDataBase =
    AppDataBase.getInstance(application)

private fun provideTaskDao(appDataBase: AppDataBase): TaskDao =
    appDataBase.tasksDao()