package com.memksim.todo.domain.model

import com.memksim.todo.domain.utils.enums.TaskState
import java.time.LocalDateTime

data class Task(
    val id: Int,
    val title: String,
    val note: String,
    val date: String,
    val time: String,
    val state: TaskState
)