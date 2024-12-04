package com.example.data.remote.mappers

import com.example.data.local.models.TaskEntity
import com.example.data.remote.models.TaskDto
import com.example.domain.models.Task

internal class TaskMapper : ITaskMapper {

    override fun mapTaskDtoToDomain(taskDto: TaskDto): Task {
        return Task(
            id = taskDto.id,
            name = taskDto.name,
            description = taskDto.description,
            dateStart = taskDto.dateStart.toLong() * MILLIS_IN_SECOND,
            dateFinish = taskDto.dateFinish.toLong() * MILLIS_IN_SECOND,
        )
    }

    override fun mapTaskDomainToDto(task: Task): TaskDto {
        return TaskDto(
            id = task.id,
            name = task.name,
            description = task.description,
            dateStart = (task.dateStart / MILLIS_IN_SECOND).toString(),
            dateFinish = (task.dateFinish / MILLIS_IN_SECOND).toString(),
        )
    }

    override fun mapTaskDtoToEntity(taskDto: TaskDto): TaskEntity {
        return TaskEntity(
            id = taskDto.id,
            name = taskDto.name,
            description = taskDto.description,
            dateStart = taskDto.dateStart.toLong(),
            dateFinish = taskDto.dateFinish.toLong(),
        )
    }

    override fun mapTaskEntityToDomain(taskEntity: TaskEntity): Task {
        return Task(
            id = taskEntity.id,
            name = taskEntity.name,
            description = taskEntity.description,
            dateStart = taskEntity.dateStart * MILLIS_IN_SECOND,
            dateFinish = taskEntity.dateFinish * MILLIS_IN_SECOND,
        )
    }

    override fun mapTaskDomainToEntity(task: Task): TaskEntity {
        return TaskEntity(
            id = task.id,
            name = task.name,
            description = task.description,
            dateStart = task.dateStart / MILLIS_IN_SECOND,
            dateFinish = task.dateFinish / MILLIS_IN_SECOND,
        )
    }

    companion object {
        private const val MILLIS_IN_SECOND = 1000
    }

}

internal interface ITaskMapper {

    fun mapTaskDtoToDomain(taskDto: TaskDto): Task

    fun mapTaskDomainToDto(task: Task): TaskDto

    fun mapTaskDtoToEntity(taskDto: TaskDto): TaskEntity

    fun mapTaskEntityToDomain(taskEntity: TaskEntity): Task

    fun mapTaskDomainToEntity(task: Task): TaskEntity

}