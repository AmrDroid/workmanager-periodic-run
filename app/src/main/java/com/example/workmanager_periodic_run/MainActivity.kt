package com.example.workmanager_periodic_run
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.workmanager_periodic_run.SharedPrefsManager.get
import com.example.workmanager_periodic_run.SharedPrefsManager.set
import com.example.workmanager_periodic_run.workmanager.WorkManagerScheduler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startWorkManager()
    }

    private fun startWorkManager() {

        val isScheduled: Boolean? =
            SharedPrefsManager.customPrefs?.get(Constants.IS_SCHEDULED, false) //getter

        isScheduled?.let { scheduled ->
            if (!scheduled) {
                Log.d("MyWorker", "started scheduler")
                SharedPrefsManager.customPrefs?.set(Constants.IS_SCHEDULED, true) //setter
                WorkManagerScheduler.refreshPeriodicWork(this)
            }
        }
    }

}
