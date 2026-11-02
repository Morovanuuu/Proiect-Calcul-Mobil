package com.example.jobprepai

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("jobs")
    suspend fun jobs(): List<Job>
}

val api: ApiService = Retrofit.Builder()
    .baseUrl(BuildConfig.BACKEND_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiService::class.java)
