package com.memksim.todo.data.repository

import com.memksim.todo.data.converters.convertReminderListToDtoList
import com.memksim.todo.data.converters.toDto
import com.memksim.todo.data.converters.toTask
import com.memksim.todo.data.entity.Task
import com.memksim.todo.data.local.TaskDao
import com.memksim.todo.domain.model.TaskDto
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val dao: TaskDao
) {

    suspend fun insertTask(dtoList: List<TaskDto>) =
        dao.insertTask(task = dtoList.map { it.toTask() }.toTypedArray())

    suspend fun updateTask(dtoList: List<TaskDto>) =
        dao.updateTask(task = dtoList.map { it.toTask() }.toTypedArray())

    suspend fun deleteTask(dtoList: List<TaskDto>) =
        dao.deleteTask(task = dtoList.map { it.toTask() }.toTypedArray())


    suspend fun getUpcomingTasks(): List<TaskDto> {
        val taskList: List<Task> = dao.getUpcomingTasks()
        return convertReminderListToDtoList(taskList = taskList)
    }

    suspend fun getCompletedTasks(): List<TaskDto> {
        val taskList: List<Task> = dao.getCompletedTasks()
        return convertReminderListToDtoList(taskList = taskList)
    }

    suspend fun getTask(id: Int): TaskDto {
        val reminder = dao.getTask(id = id)
        return reminder.toDto()
    }

}