package com.memksim.todo.data.repository

import com.memksim.todo.domain.model.Task

interface LocalRepository {

    suspend fun getTask(taskId: Int): Task

    suspend fun getAllTasksList(): List<Task>

    suspend fun saveTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

}