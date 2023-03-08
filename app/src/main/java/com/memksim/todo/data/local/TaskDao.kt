package com.memksim.todo.data.local

import androidx.room.*
import com.memksim.todo.data.entity.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("select * from tasks")
    suspend fun getTasks(): List<Task>

    @Query("select * from tasks where id = :id")
    suspend fun getTask(id: Int): Task
}