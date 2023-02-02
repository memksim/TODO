package com.memksim.todo.data.converters

import com.memksim.todo.data.entity.Task
import com.memksim.todo.domain.utils.enums.TaskState.*
import com.memksim.todo.domain.model.TaskDto

fun TaskDto.toTask(): Task = Task(
    id = id,
    title = title,
    note = note,
    date = date,
    time = time,
    isCompleted = isCompleted
)

fun Task.toDto(): TaskDto = TaskDto(
    id = id,
    title = title,
    note = note,
    date = date,
    time = time,
    isCompleted = isCompleted,
    state = SAME
)

fun convertReminderListToDtoList(taskList: List<Task>) : List<TaskDto> =
    taskList.map {
        it.toDto()
    }