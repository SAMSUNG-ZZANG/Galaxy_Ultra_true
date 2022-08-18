package com.example.sopt_main

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SoptApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}