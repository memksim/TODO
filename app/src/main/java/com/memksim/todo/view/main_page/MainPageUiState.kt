package com.memksim.todo.view.main_page

import com.memksim.todo.view.SortCondition
import com.memksim.todo.view.UiState

data class MainPageUiState(
    val upcomingRemindersList: List<MainPageItemUiState>,
    val completedRemindersList: List<MainPageItemUiState>,
    val conditionForSortReminders: SortCondition,
    val substringForSearch: String
): UiState