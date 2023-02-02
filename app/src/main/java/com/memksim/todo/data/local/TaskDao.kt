package com.memksim.todo.data.local

import androidx.room.*
import com.memksim.todo.data.entity.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(vararg task: Task)

    @Update
    suspend fun updateTask(vararg task: Task)

    @Delete
    suspend fun deleteTask(vararg task: Task)

    @Query("select * from tasks where isCompleted = 0")
    suspend fun getUpcomingTasks(): List<Task>

    @Query("select * from tasks where isCompleted = 1")
    suspend fun getCompletedTasks(): List<Task>

    @Query("select * from tasks where id = :id")
    suspend fun getTask(id: Int): Task
}