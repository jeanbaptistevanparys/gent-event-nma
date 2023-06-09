import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.example.gentevent.worker.GentEventReminderWorker
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GentEventReminderWorkerTest {

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        WorkManagerTestInitHelper.initializeTestWorkManager(context)
    }

    @Test
    fun gentEventReminderWorker_doWork_success() {
        val inputData = Data.Builder()
            .putString(GentEventReminderWorker.nameKey, "Sample Event")
            .build()

        val worker = TestListenableWorkerBuilder<GentEventReminderWorker>(context)
            .setInputData(inputData)
            .build()

        val result = worker.startWork().get()

        assertEquals(ListenableWorker.Result.success(), result)
    }
}
