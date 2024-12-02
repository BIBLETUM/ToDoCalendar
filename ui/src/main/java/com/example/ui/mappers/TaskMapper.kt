package com.example.ui.mappers

import com.example.domain.models.Task
import com.example.ui.models.TaskUi
import java.util.Date

internal class TaskMapper : ITaskMapper {

    override fun mapTaskUiToDomain(taskUi: TaskUi): Task {
        return Task(
            id = taskUi.id,
            name = taskUi.name,
            description = taskUi.description,
            dateStart = taskUi.dateStart.time,
            dateFinish = taskUi.dateFinish.time,
        )
    }

    override fun mapTaskDomainToUi(task: Task): TaskUi {
        return TaskUi(
            id = task.id,
            name = task.name,
            description = task.description,
            dateStart = Date(task.dateStart),
            dateFinish = Date(task.dateFinish),
        )
    }
}

internal interface ITaskMapper {

    fun mapTaskUiToDomain(taskUi: TaskUi): Task

    fun mapTaskDomainToUi(task: Task): TaskUi

}