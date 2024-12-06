package com.example.domain

import TaskRepositoryMock
import com.example.domain.models.Task
import com.example.domain.repository.TaskRepository
import com.example.domain.use_cases.add_task.INewTaskInteractor
import com.example.domain.use_cases.add_task.NewTaskInnerFlow
import com.example.domain.use_cases.add_task.NewTaskInteractor
import com.example.domain.use_cases.tasks_calendar.GetTasksUseCase
import com.example.domain.use_cases.tasks_calendar.IGetTasksUseCase
import com.example.domain.use_cases.tasks_calendar.ISelectDateUseCase
import com.example.domain.use_cases.tasks_calendar.SelectDateUseCase
import com.example.domain.use_cases.tasks_calendar.SelectedDateInnerFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val date: Long = 1733097600000
    private val getTasksUseCase: IGetTasksUseCase by inject(IGetTasksUseCase::class.java)
    private val selectDateUseCase: ISelectDateUseCase by inject(ISelectDateUseCase::class.java)
    private val newTaskInteractor: INewTaskInteractor by inject(INewTaskInteractor::class.java)

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single { TaskRepositoryMock() } bind TaskRepository::class
                    single { SelectedDateInnerFlow() }
                    single { SelectDateUseCase(get()) } bind ISelectDateUseCase::class
                    single { GetTasksUseCase(get(), get()) } bind IGetTasksUseCase::class
                    single { NewTaskInnerFlow() }
                    single { NewTaskInteractor(get(), get()) } bind INewTaskInteractor::class
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_AddNewTask_IncreasesTaskListSize() = runTest {
        selectDateUseCase(date)
        val initialList = getTasksUseCase().first()
        val newDateStart = 1733122800000
        val newDateFinish = 1733122800000
        val newTask = Task(
            id = 0,
            name = "aboba",
            description = "kekw",
            dateStart = newDateStart,
            dateFinish = newDateFinish,
        )
        newTaskInteractor.addNewTask(newTask)
        advanceUntilIdle()
        delay(1000)
        val newList = getTasksUseCase().first()
        assertEquals(initialList.size + 1, newList.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_AddNewTask_TaskIsAddedToList() = runTest {
        selectDateUseCase(date)

        val newDateStart = 1733122800000
        val newDateFinish = 1733122800000
        val newTask = Task(
            id = 0,
            name = "aboba",
            description = "kekw",
            dateStart = newDateStart,
            dateFinish = newDateFinish,
        )

        newTaskInteractor.addNewTask(newTask)
        advanceUntilIdle()
        delay(1000)
        val newList = getTasksUseCase().first()
        assertTrue(newList.contains(newTask))
    }


}