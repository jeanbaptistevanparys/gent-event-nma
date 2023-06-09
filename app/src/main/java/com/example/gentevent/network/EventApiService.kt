package com.example.gentevent.network

import com.example.gentevent.BuildConfig
import com.example.gentevent.model.Events
import retrofit2.http.GET
import retrofit2.http.Headers

interface EventApiService {
    @Headers(BuildConfig.API_KEY)
    @GET("events")
    suspend fun getEvents(): Events
}