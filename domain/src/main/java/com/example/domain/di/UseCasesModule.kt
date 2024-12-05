package com.example.domain.di

import com.example.domain.use_cases.add_task.INewTaskInteractor
import com.example.domain.use_cases.add_task.NewTaskInnerFlow
import com.example.domain.use_cases.add_task.NewTaskInteractor
import com.example.domain.use_cases.task_detail.GetTaskDetailFlowUseCase
import com.example.domain.use_cases.task_detail.IGetTaskDetailFlowUseCase
import com.example.domain.use_cases.task_detail.ISelectTaskUseCase
import com.example.domain.use_cases.task_detail.SelectTaskUseCase
import com.example.domain.use_cases.task_detail.TaskIdInnerFlow
import com.example.domain.use_cases.tasks_calendar.GetTasksUseCase
import com.example.domain.use_cases.tasks_calendar.IGetTasksUseCase
import com.example.domain.use_cases.tasks_calendar.ISelectDateUseCase
import com.example.domain.use_cases.tasks_calendar.SelectDateUseCase
import com.example.domain.use_cases.tasks_calendar.SelectedDateInnerFlow
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val useCasesModule = module {
    singleOf(::GetTasksUseCase) bind IGetTasksUseCase::class
    singleOf(::SelectDateUseCase) bind ISelectDateUseCase::class
    single<SelectedDateInnerFlow> { SelectedDateInnerFlow() }

    single<TaskIdInnerFlow> { TaskIdInnerFlow() }
    singleOf(::GetTaskDetailFlowUseCase) bind IGetTaskDetailFlowUseCase::class
    singleOf(::SelectTaskUseCase) bind ISelectTaskUseCase::class

    single<NewTaskInnerFlow> { NewTaskInnerFlow() }
    singleOf(::NewTaskInteractor) bind INewTaskInteractor::class
}