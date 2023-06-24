package com.memksim.todo.ui.pages.main

import com.memksim.todo.base.mvi.ItemUiState
import com.memksim.todo.base.mvi.UiState
import com.memksim.todo.ui.utils.enums.Never
import com.memksim.todo.ui.utils.enums.Repeat

data class MainPageUiState(
    val tasks: List<MainPageItemUiState> = emptyList(),
    override var error: Boolean = false,
    override var loading: Boolean = false,
    override var toastMessage: String? = null
): UiState {
    data class MainPageItemUiState(
        val id: Int = 0,
        val title: String = "",
        val note: String = "",
        val dateTime: String = "",
        val dateTimeInMillis: Long = 0,
        val isTimeSelected: Boolean = true,
        val repeat: Repeat = Never,
    ): ItemUiState
}