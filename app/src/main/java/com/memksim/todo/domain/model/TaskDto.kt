package com.memksim.todo.domain.model

import com.memksim.todo.domain.utils.enums.TaskState

data class TaskDto(
    val id: Int,
    val title: String,
    val note: String,
    val date: String,
    val time: String,
    val isCompleted: Boolean,
    val state: TaskState
)