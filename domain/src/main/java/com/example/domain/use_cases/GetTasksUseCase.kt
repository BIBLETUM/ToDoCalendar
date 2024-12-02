package com.example.domain.use_cases

import com.example.domain.models.Task
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.ZoneId

internal class GetTasksUseCase(
    taskRepository: TaskRepository,
    private val selectedDateInnerFlow: SelectedDateInnerFlow
) : IGetTasksUseCase {

    private val allTasksFlow = taskRepository.getTasks()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(): Flow<List<Task>> {
        return selectedDateInnerFlow.observe().flatMapLatest { selectedDate ->
            val startOfDay = getStartOfDayTimeStamp(selectedDate)
            val endOfDay = getEndOfDayTimeStamp(selectedDate)

            allTasksFlow.map { tasks ->
                tasks.filter { task ->
                    (task.dateStart < endOfDay && task.dateFinish > startOfDay)
                }
            }
        }
    }

    private fun getStartOfDayTimeStamp(timestamp: Long): Long {
        return Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    }

    private fun getEndOfDayTimeStamp(timestamp: Long): Long {
        return Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    }

}

interface IGetTasksUseCase {
    operator fun invoke(): Flow<List<Task>>
}