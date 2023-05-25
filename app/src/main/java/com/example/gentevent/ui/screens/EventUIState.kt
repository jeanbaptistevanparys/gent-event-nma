package com.example.gentevent.ui.screens

import com.example.gentevent.model.Event
import com.example.gentevent.model.Events

sealed interface IEventsUIstate {
    object Loading : IEventsUIstate
    data class Success(val Events: Events) : IEventsUIstate
    object Error : IEventsUIstate
}

data class EventUIState(
    var clickedEvent: Event? = null,
)




