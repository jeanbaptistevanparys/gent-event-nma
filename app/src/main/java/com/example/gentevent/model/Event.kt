package com.example.gentevent.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("day")
    val day: String = "",
    @SerialName("friends")
    val friends: Int = 0,
    @SerialName("id")
    val id: Int = 0,
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
)