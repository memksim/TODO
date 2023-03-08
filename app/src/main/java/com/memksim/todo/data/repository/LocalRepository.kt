package com.memksim.todo.data.repository

import com.memksim.todo.data.converters.convertReminderListToDtoList
import com.memksim.todo.data.converters.toDto
import com.memksim.todo.data.converters.toDatabaseEntity
import com.memksim.todo.data.entity.Task
import com.memksim.todo.data.local.TaskDao
import com.memksim.todo.domain.model.TaskDto
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val dao: TaskDao
) {

    suspend fun insertTask(task: TaskDto) =
        dao.insertTask(task = task.toDatabaseEntity())

    suspend fun updateTask(task: TaskDto) =
        dao.updateTask(task = task.toDatabaseEntity())

    suspend fun removeTask(task: TaskDto) =
        dao.deleteTask(task = task.toDatabaseEntity())


    suspend fun getTasks(): List<TaskDto> {
        val taskList: List<Task> = dao.getTasks()
        return convertReminderListToDtoList(taskList = taskList)
    }

    suspend fun getTask(id: Int): TaskDto {
        val reminder = dao.getTask(id = id)
        return reminder.toDto()
    }

}