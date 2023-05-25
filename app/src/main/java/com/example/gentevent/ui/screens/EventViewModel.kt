package com.example.gentevent.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gentevent.GentEventApplication
import com.example.gentevent.data.EventRepository
import com.example.gentevent.model.Event
import com.example.gentevent.model.Events
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {

    var eventUiState by mutableStateOf(EventUIState())
        private set

    var events: IEventsUIstate by mutableStateOf(IEventsUIstate.Loading)
        private set

    init {
        getEvents()
    }

    fun getEvents() {
        viewModelScope.launch {
            events = IEventsUIstate.Loading
            events =
                try {
                    IEventsUIstate.Success(eventRepository.getEvents())
                } catch (e: IOException) {
                    IEventsUIstate.Error
                } catch (e: HttpException) {
                    IEventsUIstate.Error
                }
        }
    }


    fun setEventClicked(Event: Event) {
        eventUiState.clickedEvent = Event
    }

    fun getEventClicked(): Event? {
        return eventUiState.clickedEvent
    }

    fun filterEvents(s: String) {
        viewModelScope.launch {
            events = IEventsUIstate.Loading
            events =
                try {
                    IEventsUIstate.Success(Events(eventRepository.getEvents().events.filter{ it.day.contains(s) }))
                } catch (e: IOException) {
                    IEventsUIstate.Error
                } catch (e: HttpException) {
                    IEventsUIstate.Error
                }
        }

    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                println(this[APPLICATION_KEY])

                val application = (this[APPLICATION_KEY] as GentEventApplication)
                val eventRepository = application.container.eventRepository
                EventViewModel(eventRepository = eventRepository)
            }
        }
    }

}