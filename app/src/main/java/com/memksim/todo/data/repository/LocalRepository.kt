package com.memksim.todo.data.repository

import com.memksim.todo.data.converters.convertReminderListToDtoList
import com.memksim.todo.data.converters.toDto
import com.memksim.todo.data.converters.toDatabaseEntity
import com.memksim.todo.data.entity.TaskEntity
import com.memksim.todo.data.local.TaskDao
import com.memksim.todo.domain.model.Task
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val dao: TaskDao
) {

    suspend fun insertTask(task: Task) =
        dao.insertTask(taskEntity = task.toDatabaseEntity())

    suspend fun updateTask(task: Task) =
        dao.updateTask(taskEntity = task.toDatabaseEntity())

    suspend fun removeTask(task: Task) {
        dao.deleteTask(taskEntity = task.toDatabaseEntity())
    }
    suspend fun getTasks(): List<Task> {
        val taskEntityList: List<TaskEntity> = dao.getTasks()
        return convertReminderListToDtoList(taskEntityList = taskEntityList)
    }

    suspend fun getTask(id: Int): Task {
        val reminder = dao.getTask(id = id)
        return reminder.toDto()
    }

}