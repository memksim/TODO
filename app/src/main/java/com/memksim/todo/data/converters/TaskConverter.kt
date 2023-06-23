package com.memksim.todo.data.converters

import com.memksim.todo.data.entity.TaskEntity
import com.memksim.todo.domain.utils.enums.TaskState.*
import com.memksim.todo.domain.model.Task

fun Task.toTaskEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    note = note,
    date_time = notificationTimeInMillis
)

fun TaskEntity.toTask(): Task = Task(
    id = id,
    title = title,
    note = note,
    notificationTimeInMillis = date_time
)