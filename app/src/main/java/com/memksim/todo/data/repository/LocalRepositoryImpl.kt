package com.memksim.todo.data.repository

import com.memksim.todo.data.converters.toTask
import com.memksim.todo.data.converters.toTaskEntity
import com.memksim.todo.data.local.TaskDao
import com.memksim.todo.domain.model.Task
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : LocalRepository {
    override suspend fun getTask(taskId: Int): Task = dao.getTask(id = taskId).toTask()

    override suspend fun getAllTasksList(): List<Task> = dao.getTasks().map {
        it.toTask()
    }

    override suspend fun saveTask(task: Task) = dao.insertTask(taskEntity = task.toTaskEntity())

    override suspend fun updateTask(task: Task) = dao.updateTask(taskEntity = task.toTaskEntity())

    override suspend fun deleteTask(task: Task) = dao.deleteTask(taskEntity = task.toTaskEntity())


}