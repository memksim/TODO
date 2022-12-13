package com.memksim.todo.data.repository

import com.memksim.todo.data.adapter.convertDtoToReminder
import com.memksim.todo.data.adapter.convertReminderListToDtoList
import com.memksim.todo.data.adapter.convertReminderToDto
import com.memksim.todo.data.entity.Reminder
import com.memksim.todo.data.local.ReminderDao
import com.memksim.todo.domain.model.ReminderDto

class LocalRepository(
    private val dao: ReminderDao
) {

    suspend fun insertReminder(dto: ReminderDto){
        val reminder = convertDtoToReminder(dto = dto)
        dao.insertReminder(reminder = reminder)
    }

    suspend fun updateReminder(dto: ReminderDto){
        val reminder = convertDtoToReminder(dto = dto)
        dao.updateReminder(reminder = reminder)
    }

    suspend fun deleteReminder(dto: ReminderDto){
        val reminder = convertDtoToReminder(dto = dto)
        dao.deleteReminder(reminder = reminder)
    }

    suspend fun getUpcomingReminders(): List<ReminderDto>{
        val reminderList: List<Reminder> = dao.getUpcomingReminders()
        return convertReminderListToDtoList(reminderList = reminderList)
    }

    suspend fun getCompletedReminders(): List<ReminderDto>{
        val reminderList: List<Reminder> = dao.getCompletedReminders()
        return convertReminderListToDtoList(reminderList = reminderList)
    }

    suspend fun getReminder(id: Int): ReminderDto{
        val reminder = dao.getReminder(id = id)
        return convertReminderToDto(reminder = reminder)
    }

}