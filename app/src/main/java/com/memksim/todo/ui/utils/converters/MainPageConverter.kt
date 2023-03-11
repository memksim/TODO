package com.memksim.todo.ui.utils.converters

import com.memksim.todo.domain.model.TaskDto
import com.memksim.todo.ui.utils.model.TaskItemUiState

fun TaskDto.toItemUiState(): TaskItemUiState = TaskItemUiState(
    id = id,
    title = title,
    note = note,
    date = date,
    time = time,
    isCompleted = isCompleted,
    itemState = state
)

fun convertDtoListToItemUiStateList(
    dtoList: List<TaskDto>?
): List<TaskItemUiState> {
    dtoList ?: return emptyList()
    return dtoList.map {
        it.toItemUiState()
    }
}

fun TaskItemUiState.toDto(): TaskDto = TaskDto(
    id = id,
    title = title,
    note = note,
    date = date,
    time = time,
    isCompleted = isCompleted,
    state = itemState
)

fun convertItemUiStateListToDtoList(
    itemList: List<TaskItemUiState>?
): List<TaskDto> {
    itemList ?: return emptyList()
    return itemList.map {
        it.toDto()
    }
}


