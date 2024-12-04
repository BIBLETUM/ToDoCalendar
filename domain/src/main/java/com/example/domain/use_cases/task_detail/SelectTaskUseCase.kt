package com.example.domain.use_cases.task_detail

internal class SelectTaskUseCase(
    private val taskIdFlow: TaskIdInnerFlow,
) : ISelectTaskUseCase {

    override fun invoke(taskId: Int) {
        taskIdFlow.selectTask(taskId)
    }
}

interface ISelectTaskUseCase {
    operator fun invoke(taskId: Int)
}