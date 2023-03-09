package com.memksim.todo.ui.pages.main

import com.memksim.todo.ui.base.UiState

data class MainPageUiState(
    val tasks: List<MainPageItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val needToCreateNewTask: Boolean = false,
    val toast: String? = null,
    val newTask: MainPageItemUiState = MainPageItemUiState()
): UiState