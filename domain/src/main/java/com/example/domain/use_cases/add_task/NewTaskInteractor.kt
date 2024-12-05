package com.example.domain.use_cases.add_task

import com.example.domain.models.Task
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class NewTaskInteractor(
    private val taskRepository: TaskRepository,
    private val newTaskInnerFlow: NewTaskInnerFlow,
) : INewTaskInteractor {

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        newTaskInnerFlow.observe().onEach { newTask ->
            taskRepository.addTask(newTask)
        }.launchIn(scope)
    }

    override fun addNewTask(task: Task) {
        newTaskInnerFlow.addNewTask(task)
    }

}

interface INewTaskInteractor {

    fun addNewTask(task: Task)

}