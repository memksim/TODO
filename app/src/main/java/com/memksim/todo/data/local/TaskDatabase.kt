package com.memksim.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.memksim.todo.data.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getDao(): TaskDao

}