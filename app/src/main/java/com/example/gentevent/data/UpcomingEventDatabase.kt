package com.example.gentevent.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gentevent.model.UpcomingEvent

@Database(entities = [UpcomingEvent::class], version = 1 , exportSchema = false)
abstract class UpcomingEventDatabase : RoomDatabase() {

    abstract fun upcomingEventsDao(): UpcomingEventDao

    companion object {
        @Volatile
        private var INSTANCE: UpcomingEventDatabase? = null

        fun getDatabase(context : Context): UpcomingEventDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, UpcomingEventDatabase::class.java, "upcoming_events_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }



}