package com.memksim.todo.domain.model

data class ReminderDto(
    val id: Int,
    val title: String,
    val note: String,
    val date: String,
    val time: String,
    val isCompleted: Boolean
)