package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.models.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :taskId LIMIT 1")
    fun getTaskById(taskId: Int): Flow<TaskEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long
}