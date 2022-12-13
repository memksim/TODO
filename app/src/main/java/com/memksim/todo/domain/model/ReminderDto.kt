package com.memksim.todo.domain.model

import com.memksim.todo.domain.constants.ReminderState

data class ReminderDto(
    val id: Int,
    val title: String,
    val note: String,
    val date: String,
    val time: String,
    val isCompleted: Boolean,
    val state: ReminderState
)