package com.memksim.todo.data.converters

import com.memksim.todo.data.entity.TaskEntity
import com.memksim.todo.domain.model.Task

fun Task.toTaskEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    note = note,
    date_time = notificationTimeInMillis,
    time_selected = isTimeSelected,
    repeatCode = repeatCode
)

fun TaskEntity.toTask(): Task = Task(
    id = id,
    title = title,
    note = note,
    notificationTimeInMillis = date_time,
    isTimeSelected = time_selected,
    repeatCode = repeatCode
)