package com.memksim.todo.view.adapter

import com.memksim.todo.domain.model.ReminderDto
import com.memksim.todo.view.main_page.MainPageItemUiState

fun convertDtoToItemUiState(
    dto: ReminderDto
): MainPageItemUiState = MainPageItemUiState(
    id = dto.id,
    title = dto.title,
    note = dto.note,
    date = dto.date,
    time = dto.time,
    isCompleted = dto.isCompleted,
    itemState = dto.state
)

fun convertDtoListToItemUiStateList(
    dtoList: List<ReminderDto>?
): List<MainPageItemUiState> {
    dtoList ?: return emptyList()
    return dtoList.map {
        convertDtoToItemUiState(dto = it)
    }
}

fun convertItemUiStateToDto(
    item: MainPageItemUiState
): ReminderDto = ReminderDto(
    id = item.id,
    title = item.title,
    note = item.note,
    date = item.date,
    time = item.time,
    isCompleted = item.isCompleted,
    state = item.itemState
)

fun convertItemUiStateListToDtoList(
    itemList: List<MainPageItemUiState>?
): List<ReminderDto> {
    itemList ?: return emptyList()
    return itemList.map {
        convertItemUiStateToDto(item = it)
    }
}


