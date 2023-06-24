package com.memksim.todo.domain.model

data class Task(
    val id: Int,
    val title: String,
    val note: String,
    val notificationTimeInMillis: Long,
    val isTimeSelected: Boolean,
    val repeatCode: Int
)