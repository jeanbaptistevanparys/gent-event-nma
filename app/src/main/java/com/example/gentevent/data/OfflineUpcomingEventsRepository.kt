package com.example.gentevent.data

import com.example.gentevent.model.UpcomingEvent
import kotlinx.coroutines.flow.Flow

class OfflineUpcomingEventsRepository(private val upcomingEventDao: UpcomingEventDao) : UpcomingEventsRepository {
    override fun getAllUpcomingEvents(): Flow<List<UpcomingEvent>> = upcomingEventDao.getAllUpcomingEvents()
    override suspend fun insertUpcomingEvents(upcomingEvent: UpcomingEvent) = upcomingEventDao.insertUpcomingEvents(upcomingEvent)
    override suspend fun deleteUpcomingEvents(id : Int) = upcomingEventDao.deleteUpcomingEvents(id)
}
