package com.example.data.di

import com.example.data.TaskRepositoryImpl
import com.example.domain.repository.TaskRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::TaskRepositoryImpl) bind TaskRepository::class
}