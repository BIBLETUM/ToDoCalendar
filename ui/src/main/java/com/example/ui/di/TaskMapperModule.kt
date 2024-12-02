package com.example.ui.di

import com.example.ui.mappers.ITaskMapper
import com.example.ui.mappers.TaskMapper
import org.koin.dsl.module

val taskMapperModule = module {

    single<ITaskMapper> { TaskMapper() }


}