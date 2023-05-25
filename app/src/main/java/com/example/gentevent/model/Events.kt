package com.example.gentevent.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Events(
    @SerialName("Events")
    val events: List<Event> = listOf()
)