package com.example.data.di

import android.content.Context
import com.example.data.remote.api.ApiFactory
import com.example.data.remote.api.IApiFactory
import org.koin.dsl.module

val remoteDataSourceModule = module {

    single { provideApiFactory(get()) }
    single { provideApiService(get()) }

}

private fun provideApiFactory(context: Context): IApiFactory = ApiFactory(context)
private fun provideApiService(apiFactory: IApiFactory) = apiFactory.getApiService()