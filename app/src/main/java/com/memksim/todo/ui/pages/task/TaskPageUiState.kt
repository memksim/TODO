package com.memksim.todo.ui.pages.task

import com.memksim.todo.ui.utils.model.TaskItemUiState
import com.memksim.todo.ui.utils.mvi.UiState

data class TaskPageUiState(
    val task: TaskItemUiState = TaskItemUiState(),
    val toast: String? = null
): UiState
