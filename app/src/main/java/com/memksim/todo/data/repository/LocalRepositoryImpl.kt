package com.memksim.todo.data.repository

import com.memksim.todo.data.converters.toTask
import com.memksim.todo.data.converters.toTaskEntity
import com.memksim.todo.data.local.TaskDao
import com.memksim.todo.domain.model.Task
import com.memksim.todo.utils.exceptions.DatabaseException
import com.memksim.todo.utils.asyncTryCatching
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : LocalRepository {

    override suspend fun getTask(taskId: Int): Task =
        asyncTryCatching(
            cause = DatabaseException()
        ) {
            dao.getTask(id = taskId).toTask()
        }


    override suspend fun getAllTasksList(): List<Task> =
        asyncTryCatching(
            cause = DatabaseException()
        ) {
            dao.getTasks().map {
                it.toTask()
            }
        }

    override suspend fun saveTask(task: Task) =
        asyncTryCatching(
            cause = DatabaseException()
        ) {
            dao.insertTask(taskEntity = task.toTaskEntity())
        }

    override suspend fun updateTask(task: Task) =
        asyncTryCatching(
            cause = DatabaseException()
        ) {
            dao.updateTask(taskEntity = task.toTaskEntity())
        }

    override suspend fun deleteTask(task: Task) =
        asyncTryCatching(
            cause = DatabaseException()
        ) {
            dao.deleteTask(taskEntity = task.toTaskEntity())
        }

}