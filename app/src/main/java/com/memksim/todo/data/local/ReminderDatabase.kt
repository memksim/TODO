package com.memksim.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.memksim.todo.data.entity.Reminder

@Database(
    entities = [Reminder::class],
    version = 1,
    exportSchema = false
)
abstract class ReminderDatabase: RoomDatabase() {

    abstract fun getDao(): ReminderDao

}