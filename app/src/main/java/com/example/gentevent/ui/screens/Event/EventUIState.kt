package com.example.gentevent.ui.screens.Event

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
    val upcomingEvents: List<UpcomingEvent> = emptyList(),
    var daysuntil: Int? = null,
)
// todo get everyting in one state



