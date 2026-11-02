package com.example.jobprepai

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// 10.0.2.2 = localhost-ul PC-ului vazut din emulator. Pe telefon fizic: IP-ul PC-ului din retea.
private const val BASE_URL = "http://10.0.2.2:8000/"

data class HealthResponse(val status: String)

interface ApiService {
    @GET("health")
    suspend fun health(): HealthResponse
}

val api: ApiService = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiService::class.java)
