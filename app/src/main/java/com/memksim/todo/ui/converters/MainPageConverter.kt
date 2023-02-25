package com.memksim.todo.ui.converters

import com.memksim.todo.domain.model.TaskDto
import com.memksim.todo.ui.pages.main.MainPageItemUiState

fun TaskDto.toItemUiState(): MainPageItemUiState = MainPageItemUiState(
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
): List<MainPageItemUiState> {
    dtoList ?: return emptyList()
    return dtoList.map {
        it.toItemUiState()
    }
}

fun MainPageItemUiState.toDto(): TaskDto = TaskDto(
    id = id,
    title = title,
    note = note,
    date = date,
    time = time,
    isCompleted = isCompleted,
    state = itemState
)

fun convertItemUiStateListToDtoList(
    itemList: List<MainPageItemUiState>?
): List<TaskDto> {
    itemList ?: return emptyList()
    return itemList.map {
        it.toDto()
    }
}


