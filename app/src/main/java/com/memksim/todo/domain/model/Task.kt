package com.memksim.todo.domain.model

import com.memksim.todo.domain.utils.enums.TaskState

data class Task(
    val id: Int,
    val title: String,
    val note: String,
    val notificationTimeInMillis: Long
)