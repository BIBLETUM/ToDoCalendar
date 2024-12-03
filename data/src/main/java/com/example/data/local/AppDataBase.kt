package com.example.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.TaskDao
import com.example.data.local.models.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class AppDataBase : RoomDatabase() {

    abstract fun tasksDao(): TaskDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        private const val DB_NAME = "todo_calendar.db"
        private val LOOCK = Any()

        fun getInstance(application: Application): AppDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

}