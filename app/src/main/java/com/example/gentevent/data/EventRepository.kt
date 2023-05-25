package com.example.gentevent.data

import com.example.gentevent.model.Events
import com.example.gentevent.network.EventApiService

interface EventRepository {
    suspend fun getEvents(): Events
}

class NetworkEventRepository(
    private val eventApiService: EventApiService
) : EventRepository {
    override suspend fun getEvents(): Events = eventApiService.getEvents()
}