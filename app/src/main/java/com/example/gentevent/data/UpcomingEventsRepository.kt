package com.example.gentevent.data

import com.example.gentevent.model.UpcomingEvent
import kotlinx.coroutines.flow.Flow

interface UpcomingEventsRepository{
    fun getAllUpcomingEvents(): Flow<List<UpcomingEvent>>
    suspend fun insertUpcomingEvents(upcomingEvents: UpcomingEvent)
    suspend fun deleteUpcomingEvents(id : Int)
}