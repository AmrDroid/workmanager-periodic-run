package com.example.workmanager_periodic_run.workmanager
import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit

object WorkManagerScheduler {

    fun refreshPeriodicWork(context: Context) {

        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance()

        // Set Execution around 04:00:00 AM
        dueDate.set(Calendar.HOUR_OF_DAY, 21)
        dueDate.set(Calendar.MINUTE, 48)
        dueDate.set(Calendar.SECOND, 0)
        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24)
        }

        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff)

        Log.d("MyWorker", "time difference $minutes")

        //define constraints
        val myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()


        val refreshCpnWork = PeriodicWorkRequest.Builder(MyWorker::class.java,
            2, TimeUnit.MINUTES)
            .setInitialDelay(minutes, TimeUnit.MINUTES)
            .setConstraints(myConstraints)
            .addTag("myWorkManager")
            .build()


        WorkManager.getInstance(context).enqueueUniquePeriodicWork("myWorkManager",
            ExistingPeriodicWorkPolicy.REPLACE, refreshCpnWork)

    }
}