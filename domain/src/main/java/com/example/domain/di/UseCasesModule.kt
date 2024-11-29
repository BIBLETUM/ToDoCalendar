package com.example.domain.di

import com.example.domain.use_cases.GetTasksUseCase
import com.example.domain.use_cases.IGetTasksUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val useCasesModule = module {
    singleOf(::GetTasksUseCase) bind IGetTasksUseCase::class
}