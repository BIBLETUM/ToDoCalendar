package com.example.data.remote.mappers

import com.example.data.remote.models.TaskDto
import com.example.domain.models.Task

internal class TaskMapper : ITaskMapper {

    override fun mapTaskDtoToDomain(taskDto: TaskDto): Task {
        return Task(
            id = taskDto.id,
            name = taskDto.name,
            description = taskDto.description,
            dateStart = taskDto.dateStart,
            dateFinish = taskDto.dateFinish
        )
    }

    override fun mapTaskDomainToDto(task: Task): TaskDto {
        return TaskDto(
            id = task.id,
            name = task.name,
            description = task.description,
            dateStart = task.dateStart,
            dateFinish = task.dateFinish
        )
    }
}

internal interface ITaskMapper {

    fun mapTaskDtoToDomain(taskDto: TaskDto): Task

    fun mapTaskDomainToDto(task: Task): TaskDto

}