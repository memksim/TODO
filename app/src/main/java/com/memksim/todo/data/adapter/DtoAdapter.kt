package com.memksim.todo.data.adapter

import com.memksim.todo.data.entity.Reminder
import com.memksim.todo.domain.model.ReminderDto

fun convertDtoToReminder(dto: ReminderDto): Reminder =
    Reminder(
        id = dto.id,
        title = dto.title,
        note = dto.note,
        date = dto.date,
        time = dto.time,
        isCompleted = dto.isCompleted
    )

fun convertReminderToDto(reminder: Reminder): ReminderDto =
    ReminderDto(
        id = reminder.id,
        title = reminder.title,
        note = reminder.note,
        date = reminder.date,
        time = reminder.time,
        isCompleted = reminder.isCompleted
    )

fun convertReminderListToDtoList(reminderList: List<Reminder>) : List<ReminderDto> =
    reminderList.map {
        convertReminderToDto(it)
    }