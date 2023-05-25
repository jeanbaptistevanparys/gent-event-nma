package com.example.gentevent.di

import com.example.gentevent.data.EventRepository
import com.example.gentevent.data.NetworkEventRepository
import com.example.gentevent.network.EventApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val eventRepository: EventRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://gent-event.hasura.app/api/rest/"

    private val json = Json {
        ignoreUnknownKeys = true
    }


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()


    private val retrofitService: EventApiService by lazy {
        retrofit.create(EventApiService::class.java)
    }

    override val eventRepository: EventRepository by lazy {
        NetworkEventRepository(retrofitService)
    }
}