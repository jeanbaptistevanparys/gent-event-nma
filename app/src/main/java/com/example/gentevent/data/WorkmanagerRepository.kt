package com.example.gentevent.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.gentevent.SECONDS
import com.example.gentevent.model.UpcomingEvent
import com.example.gentevent.worker.GentEventReminderWorker
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.time.toDuration

class WorkmanagerRepository(context: Context) : IWorkManagerRepository {
    private val workManager = WorkManager.getInstance(context)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun scheduleNotification(UpcomingEvent: UpcomingEvent) {

        val message = "Upcoming event: " + UpcomingEvent.title + " at " + UpcomingEvent.startTime

        val data = Data.Builder()
        data.putString(GentEventReminderWorker.nameKey , message)

        val date = LocalDate.parse(UpcomingEvent.date, DateTimeFormatter.ISO_DATE)
        val time = LocalTime.parse(UpcomingEvent.startTime, DateTimeFormatter.ofPattern("HH:mm"))
        val eventDateTime = date.atTime(time)
        val now = LocalDate.now().atTime(LocalTime.now())
        var duration = now.until(eventDateTime, java.time.temporal.ChronoUnit.HOURS)


        //for testing
        //duration = SECONDS


        val unit = java.util.concurrent.TimeUnit.SECONDS


        val workRequestBuilder = OneTimeWorkRequestBuilder<GentEventReminderWorker>()
            .setInitialDelay(duration, unit)
            .setInputData(data.build())
            .build()

        workManager.enqueueUniqueWork(
            UpcomingEvent.title + duration,
            ExistingWorkPolicy.REPLACE,
            workRequestBuilder
        )

    }
}