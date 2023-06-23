package com.memksim.todo.data.converters

import com.memksim.todo.data.entity.TaskEntity
import com.memksim.todo.domain.utils.enums.TaskState.*
import com.memksim.todo.domain.model.Task

fun Task.toDatabaseEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    note = note,
    date = date,
    time = time
)

fun TaskEntity.toDto(): Task = Task(
    id = id,
    title = title,
    note = note,
    date = date,
    time = time,
    state = SAME
)

fun convertReminderListToDtoList(taskEntityList: List<TaskEntity>) : List<Task> =
    taskEntityList.map {
        it.toDto()
    }