package com.memksim.todo.view.main_page

import com.memksim.todo.view.utils.enums.SortCondition
import com.memksim.todo.view.utils.base.UiState
import com.memksim.todo.view.utils.enums.SearchAppBarState

data class MainPageUiState(
    val upcomingRemindersList: List<MainPageItemUiState>,
    val completedRemindersList: List<MainPageItemUiState>,
    val conditionForSortReminders: SortCondition,
    val searchTopAppBarState: SearchAppBarState,
    val searchText: String
): UiState