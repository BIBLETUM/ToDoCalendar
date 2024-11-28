package com.example.data.remote

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal class ApiFactory(context: Context) {

    private val mockInterceptor = MockInterceptor(context)
    private val mockClient = OkHttpClient.Builder()
        .addInterceptor(mockInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(mockClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create<ApiService>()

    private companion object {
        const val BASE_URL = "https://anything.com/"
    }

}