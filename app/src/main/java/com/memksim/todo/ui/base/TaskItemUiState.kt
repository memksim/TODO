package com.memksim.todo.ui.base

data class TaskItemUiState(
    val title: String,
    val note: String? = null,
    val date: String,
    val time: String
    //TODO
)