package com.example.movielist.dio

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// hilt annotation to setup hilt implementation on this application
@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}