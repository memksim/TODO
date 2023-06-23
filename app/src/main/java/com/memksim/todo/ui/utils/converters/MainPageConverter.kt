package com.memksim.todo.ui.utils.converters

import com.memksim.todo.domain.model.Task
import com.memksim.todo.ui.utils.model.TaskItemUiState

fun Task.toItemUiState(): TaskItemUiState = TaskItemUiState(
    id = id,
    title = title,
    note = note,
    date = date,
    time = time,
    itemState = state
)

fun convertDtoListToItemUiStateList(
    dtoList: List<Task>?
): List<TaskItemUiState> {
    dtoList ?: return emptyList()
    return dtoList.map {
        it.toItemUiState()
    }
}

fun TaskItemUiState.toDto(): Task = Task(
    id = id,
    title = title,
    note = note,
    date = date,
    time = time,
    state = itemState
)

fun convertItemUiStateListToDtoList(
    itemList: List<TaskItemUiState>?
): List<Task> {
    itemList ?: return emptyList()
    return itemList.map {
        it.toDto()
    }
}


