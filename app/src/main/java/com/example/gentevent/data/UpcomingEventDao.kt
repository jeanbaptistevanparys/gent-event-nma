package com.example.gentevent.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gentevent.model.UpcomingEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingEventDao {
    @Query("SELECT * FROM upcoming_events")
    fun getAllUpcomingEvents(): Flow<List<UpcomingEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingEvents(upcomingEvent: UpcomingEvent)

    @Query("DELETE FROM upcoming_events WHERE id = :id")
    suspend fun deleteUpcomingEvents(id : Int)
}