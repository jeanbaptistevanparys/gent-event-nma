package com.example.gentevent.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "upcoming_events")
data class UpcomingEvent(
    @ColumnInfo("day")
    val day: String = "",
    @ColumnInfo("friends")
    val friends: Int = 0,
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo("img_url")
    val imgUrl: String = "",
    @ColumnInfo("location")
    val location: String = "",
    @ColumnInfo("location_name")
    val locationName: String = "",
    @ColumnInfo("title")
    val title: String = "",
    @ColumnInfo("date")
    val date: String = "",
    @ColumnInfo("description")
    val description: String = "",
    @ColumnInfo("category")
    val category: String = "",
    @ColumnInfo("start_time")
    val startTime: String = "",
    @ColumnInfo("end_time")
    val endTime: String = "",

)
