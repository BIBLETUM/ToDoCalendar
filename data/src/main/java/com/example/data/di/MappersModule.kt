package com.example.data.di

import com.example.data.remote.mappers.ITaskMapper
import com.example.data.remote.mappers.TaskMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mapperModule = module {
    singleOf(::TaskMapper) bind ITaskMapper::class
}