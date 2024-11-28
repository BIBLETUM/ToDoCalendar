package com.example.todocalendar

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CalendarApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@CalendarApplication)
            modules(
                domainModule,
//                uiModule,
                dataModule,
            )
        }
    }

}