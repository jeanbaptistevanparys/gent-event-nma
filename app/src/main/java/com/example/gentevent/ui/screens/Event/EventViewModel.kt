package com.example.gentevent.ui.screens.Event

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.gentevent.data.IWorkManagerRepository
import com.example.gentevent.data.UpcomingEventsRepository
import com.example.gentevent.model.Event
import com.example.gentevent.model.Events
import com.example.gentevent.model.UpcomingEvent
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
class EventViewModel(
    private val eventRepository: EventRepository,
    private val upcomingEventsRepository: UpcomingEventsRepository,
    private val workmanagerRepository: IWorkManagerRepository
) : ViewModel() {


    var eventUiState by mutableStateOf(EventUIState())
        private set

    var events: IEventsUIstate by mutableStateOf(IEventsUIstate.Loading)
        private set

    val upcomingEventUIState: StateFlow<UpcomingEventUIState> =
        upcomingEventsRepository.getAllUpcomingEvents()
            .map {
                UpcomingEventUIState(it, daysUntilFirstEvent())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = UpcomingEventUIState()
            )
    init {
        getEvents()
    }

    fun daysUntilFirstEvent(): Int? {
        if (upcomingEventUIState.value.upcomingEvents.isEmpty()) {
            return null
        }
        val upcoming = upcomingEventUIState.value.upcomingEvents.sortedBy { it.date }[0].date
        val date = LocalDate.parse(upcoming, DateTimeFormatter.ISO_DATE)
        val now = LocalDate.now()
        return date.dayOfYear - now.dayOfYear
    }


    fun insertUpcomingEvent(event: Event) {
        val ev = UpcomingEvent(
            id = event.id,
            title = event.title,
            day = event.day,
            location = event.location,
            description = event.description,
            category = event.category,
            imgUrl = event.imgUrl,
            friends = event.friends,
            locationName = event.locationName,
            date = event.date,
            startTime = event.startTime,
            endTime = event.endTime,
        )
        viewModelScope.launch {
            upcomingEventsRepository.insertUpcomingEvents(ev)
        }
    }

    fun deleteUpcomingEvents(id: Int) {
        viewModelScope.launch {
            upcomingEventsRepository.deleteUpcomingEvents(id)
        }
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
                    IEventsUIstate.Success(Events(eventRepository.getEvents().events.filter {
                        it.day.contains(
                            s
                        )
                    }))
                } catch (e: IOException) {
                    IEventsUIstate.Error
                } catch (e: HttpException) {
                    IEventsUIstate.Error
                }
        }
    }

    fun scheduleNotification(event: Event) {
        val ev = UpcomingEvent(
            id = event.id,
            title = event.title,
            day = event.day,
            location = event.location,
            description = event.description,
            category = event.category,
            imgUrl = event.imgUrl,
            friends = event.friends,
            locationName = event.locationName,
            date = event.date,
            startTime = event.startTime,
            endTime = event.endTime,
        )
        workmanagerRepository.scheduleNotification(ev)
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GentEventApplication)
                val eventRepository = application.container.eventRepository
                val upcomingEventsRepository = application.container.upcomingEventsRepository
                EventViewModel(
                    eventRepository = eventRepository,
                    upcomingEventsRepository = upcomingEventsRepository,
                    workmanagerRepository = application.container.workmanagerRepository
                )
            }
        }
    }

}