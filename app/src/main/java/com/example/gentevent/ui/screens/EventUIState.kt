package com.example.gentevent.ui.screens

import com.example.gentevent.model.Event
import com.example.gentevent.model.Events
import com.example.gentevent.model.UpcomingEvent

sealed interface IEventsUIstate {
    object Loading : IEventsUIstate
    data class Success(val Events: Events) : IEventsUIstate
    object Error : IEventsUIstate
}

data class EventUIState(
    var clickedEvent: Event? = null,
)

data class UpcomingEventUIState(
    val UpcomingEvents: List<UpcomingEvent> = emptyList(),
)




