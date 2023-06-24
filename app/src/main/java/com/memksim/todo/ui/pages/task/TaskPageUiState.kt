package com.memksim.todo.ui.pages.task

import com.memksim.todo.base.mvi.UiState

data class TaskPageUiState(
    val task: TaskItemUiState = TaskItemUiState(),
    val toast: String? = null
): UiState
