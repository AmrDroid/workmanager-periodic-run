package com.example.workmanager_periodic_run
import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefsManager.init(this)
    }
}