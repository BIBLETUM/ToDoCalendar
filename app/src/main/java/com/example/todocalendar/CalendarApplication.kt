package com.example.todocalendar

import android.app.Application
import com.example.data.di.dataModule
import org.koin.android.ext.koin.androidContext

class CalendarApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@CalendarApplication)
            modules(
//                uiModule,
                dataModule,
//                domainModule,
            )
        }
    }

}