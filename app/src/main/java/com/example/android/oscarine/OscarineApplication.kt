package com.example.android.oscarine

import android.app.Application
import timber.log.Timber

class OscarineApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}