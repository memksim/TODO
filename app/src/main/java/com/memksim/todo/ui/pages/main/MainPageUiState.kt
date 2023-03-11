package com.memksim.todo.ui.pages.main

import com.memksim.todo.ui.utils.model.TaskItemUiState
import com.memksim.todo.ui.utils.mvi.UiState

data class MainPageUiState(
    val tasks: List<TaskItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val needToCreateNewTask: Boolean = false,
    val toast: String? = null,
    val newTask: TaskItemUiState = TaskItemUiState()
): UiState