package com.example.gentevent.di

import android.content.Context
import com.example.gentevent.data.*
import com.example.gentevent.network.EventApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val eventRepository: EventRepository
    val upcomingEventsRepository: UpcomingEventsRepository
    val workmanagerRepository: WorkmanagerRepository
}

class DefaultAppContainer(private val context : Context) : AppContainer {
    private val BASE_URL = "https://gent-event.hasura.app/api/rest/"

    private val json = Json {
        ignoreUnknownKeys = true
    }


    @OptIn(ExperimentalSerializationApi::class)
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

    override val upcomingEventsRepository: UpcomingEventsRepository by lazy {
        OfflineUpcomingEventsRepository(UpcomingEventDatabase.getDatabase(context).upcomingEventsDao())
    }

    override val workmanagerRepository = WorkmanagerRepository(context)
}