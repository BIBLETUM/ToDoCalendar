package com.example.data.di

import org.koin.dsl.module

val dataModule = module {
    includes(
        mapperModule,
        repositoryModule,
        remoteDataSourceModule,
        dbModule,
    )
}