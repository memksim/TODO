package com.memksim.todo.ui.pages.main

import com.memksim.todo.ui.utils.enums.SortCondition
import com.memksim.todo.ui.base.UiState
import com.memksim.todo.ui.utils.enums.SearchAppBarState

data class MainPageUiState(
    val upcomingRemindersList: List<MainPageItemUiState>,
    val completedRemindersList: List<MainPageItemUiState>,
    val conditionForSortReminders: SortCondition,
    val searchTopAppBarState: SearchAppBarState,
    val searchText: String
): UiState