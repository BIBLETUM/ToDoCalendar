import com.example.domain.models.Task
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class TaskRepositoryMock : TaskRepository {

    private val taskList = mutableListOf(
        Task(
            id = 1,
            name = "Task 1",
            description = "Description for Task 1",
            dateStart = 1733137200000,
            dateFinish = 1733140800000,
        ),
        Task(
            id = 2,
            name = "Task 2",
            description = "Description for Task 2",
            dateStart = 1733148000000,
            dateFinish = 1733151600000,
        ),
        Task(
            id = 3,
            name = "Task 3",
            description = "Description for Task 3",
            dateStart = 1733166000000,
            dateFinish = 1733169600000,
        )
    )
    private val taskFlow = MutableSharedFlow<List<Task>>(replay = 1)

    init {
        taskFlow.tryEmit(taskList)
    }

    override fun getTasks(): Flow<List<Task>> = taskFlow.asSharedFlow()

    override suspend fun addTask(task: Task) {
        taskList.add(task)
        taskFlow.emit(taskList)
    }

    override fun getTaskDetail(taskId: Int): Flow<Task> {
        val task = taskList.find { it.id == taskId }
        return if (task != null) {
            flowOf(task)
        } else {
            flow { throw RuntimeException("Task with id $taskId was not found") }
        }
    }
}
