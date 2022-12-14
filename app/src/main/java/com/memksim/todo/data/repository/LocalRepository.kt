package com.memksim.todo.data.repository

import com.memksim.todo.data.adapter.convertDtoToReminder
import com.memksim.todo.data.adapter.convertReminderListToDtoList
import com.memksim.todo.data.adapter.convertReminderToDto
import com.memksim.todo.data.entity.Reminder
import com.memksim.todo.data.local.ReminderDao
import com.memksim.todo.domain.model.ReminderDto
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val dao: ReminderDao
) {

    suspend fun insertReminder(dtoList: List<ReminderDto>) =
        dao.insertReminder(reminder = dtoList.map { convertDtoToReminder(it) }.toTypedArray())

    suspend fun updateReminder(dtoList: List<ReminderDto>) =
        dao.updateReminder(reminder = dtoList.map { convertDtoToReminder(it) }.toTypedArray())

    suspend fun deleteReminder(dtoList: List<ReminderDto>) =
        dao.deleteReminder(reminder = dtoList.map { convertDtoToReminder(it) }.toTypedArray())


    suspend fun getUpcomingReminders(): List<ReminderDto> {
        val reminderList: List<Reminder> = dao.getUpcomingReminders()
        return convertReminderListToDtoList(reminderList = reminderList)
    }

    suspend fun getCompletedReminders(): List<ReminderDto> {
        val reminderList: List<Reminder> = dao.getCompletedReminders()
        return convertReminderListToDtoList(reminderList = reminderList)
    }

    suspend fun getReminder(id: Int): ReminderDto {
        val reminder = dao.getReminder(id = id)
        return convertReminderToDto(reminder = reminder)
    }

}