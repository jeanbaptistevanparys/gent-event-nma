package com.example.gentevent

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gentevent.data.UpcomingEventDao
import com.example.gentevent.data.UpcomingEventDatabase
import com.example.gentevent.model.UpcomingEvent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UpcomingEventDaoTest {

    private lateinit var upcomingEventDao: UpcomingEventDao
    private lateinit var upcomingEventDatabase: UpcomingEventDatabase
    private val event1 = UpcomingEvent(
        id = 1,
        title = "Test Event",
        description = "This is a test event",
        location = "Test Location",
        date = "2021-01-01",
        startTime = "12:00",
        endTime = "13:00",
        imgUrl = "https://www.google.com",
        friends = 1,
    )
    private val event2 = UpcomingEvent(
        id = 2,
        title = "Test Event",
        description = "This is a test event",
        location = "Test Location",
        date = "2021-01-01",
        startTime = "12:00",
        endTime = "13:00",
        imgUrl = "https://www.google.com",
        friends = 1,
    )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        upcomingEventDatabase = Room.inMemoryDatabaseBuilder(context, UpcomingEventDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        upcomingEventDao = upcomingEventDatabase.upcomingEventsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        upcomingEventDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsEventIntoDB() = runBlocking {
        addOneEventToDb()
        val allEvents = upcomingEventDao.getAllUpcomingEvents().first()
        assertEquals(allEvents[0], event1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllEvents_returnsAllEventsFromDB() = runBlocking {
        addTwoEventsToDb()
        val allEvents = upcomingEventDao.getAllUpcomingEvents().first()
        assertEquals(allEvents[0], event1)
        assertEquals(allEvents[1], event2)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteEvent_deletesEventFromDB() = runBlocking {
        addOneEventToDb()
        upcomingEventDao.deleteUpcomingEvents(event1.id)
        val allEvents = upcomingEventDao.getAllUpcomingEvents().first()
        assertEquals(allEvents.size, 0)
    }

    private suspend fun addOneEventToDb() {
        upcomingEventDao.insertUpcomingEvents(event1)
    }

    private suspend fun addTwoEventsToDb() {
        upcomingEventDao.insertUpcomingEvents(event1)
        upcomingEventDao.insertUpcomingEvents(event2)
    }
}
