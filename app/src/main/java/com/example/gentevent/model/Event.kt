package com.example.gentevent.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("day")
    val day: String = "",
    @SerialName("friends")
    val friends: Int = 0,
    @SerialName("img_url")
    val imgUrl: String = "",
    @SerialName("location")
    val location: String = "",
    @SerialName("location_name")
    val locationName: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("date")
    val date: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("category")
    val category: String = "",
    @SerialName("start_time")
    val startTime: String = "",
    @SerialName("end_time")
    val endTime: String = "",
)