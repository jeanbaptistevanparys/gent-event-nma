package com.example.gentevent.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.gentevent.R

class GentEventReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams
) {

    override suspend fun doWork(): Result {

        val upcomingEventName = inputData.getString(nameKey)

        makeUpcomingEventReminderNotification(
            applicationContext.resources.getString(R.string.UpcomingEvent, upcomingEventName),
            applicationContext
        )


        return Result.success()
    }

    companion object {
        const val nameKey = "NAME"
    }

}