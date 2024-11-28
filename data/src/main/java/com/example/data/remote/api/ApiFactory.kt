package com.example.data.remote.api

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal class ApiFactory(context: Context) : IApiFactory {

    private val mockInterceptor = MockInterceptor(context)
    private val mockClient = OkHttpClient.Builder()
        .addInterceptor(mockInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(mockClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create<ApiService>()

    override fun getApiService(): ApiService = apiService

    private companion object {
        const val BASE_URL = "https://anything.com/"
    }

}

internal interface IApiFactory {
    fun getApiService(): ApiService
}