package com.memksim.todo.data.local

import androidx.room.*
import com.memksim.todo.data.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

    @Query("select * from tasks")
    suspend fun getTasks(): List<TaskEntity>

    @Query("select * from tasks where id = :id")
    suspend fun getTask(id: Int): TaskEntity
}