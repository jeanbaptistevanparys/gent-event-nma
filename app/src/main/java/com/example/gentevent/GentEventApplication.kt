package com.example.gentevent

import android.app.Application
import com.example.gentevent.di.AppContainer
import com.example.gentevent.di.DefaultAppContainer

class GentEventApplication: Application() {
    lateinit var  container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}