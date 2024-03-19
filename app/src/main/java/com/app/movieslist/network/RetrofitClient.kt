package com.app.movieslist.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org"
    private const val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYjc0MjhiYjdjZmY3YjkzNTAxNGIxZTFkNDIyZDkzZCIsInN1YiI6IjY1ZjlmODg0NmMwYjM2MDE3ZDA2MDUxOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.dU9UFxlAPg1XHTtU76bVEuU_K_UPFs02LEuojP2iTAo"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val tokenInterceptor = TokenInterceptor(ACCESS_TOKEN)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val apiService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}