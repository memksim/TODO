package com.memksim.todo.view.main_page

import com.memksim.todo.domain.utils.enums.TaskState
import com.memksim.todo.view.utils.base.ItemUiState

data class MainPageItemUiState(
    val id: Int,
    val title: String,
    val note: String,
    val date: String,
    val time: String,
    val isCompleted: Boolean,
    val itemState: TaskState
): ItemUiState