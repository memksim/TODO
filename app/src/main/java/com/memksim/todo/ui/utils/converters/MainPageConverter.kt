package com.memksim.todo.ui.utils.converters

import com.memksim.todo.domain.model.Task
import com.memksim.todo.ui.pages.main.MainPageUiState.MainPageItemUiState
import com.memksim.todo.ui.utils.DATE_PATTERN_WITHOUT_TIME
import com.memksim.todo.ui.utils.FULL_DATE_TIME_PATTERN
import com.memksim.todo.ui.utils.formatMillisToDateString

fun Task.toItemUiState(): MainPageItemUiState = MainPageItemUiState(
    id = id,
    title = title,
    note = note,
    dateTime = formatMillisToDateString(
        millis = notificationTimeInMillis,
        if (isTimeSelected) FULL_DATE_TIME_PATTERN
        else DATE_PATTERN_WITHOUT_TIME
    ),
    dateTimeInMillis = notificationTimeInMillis,
    repeat = getRepeat(repeatCode)
)

fun MainPageItemUiState.toTask(): Task = Task(
    id = id,
    title = title,
    note = note,
    notificationTimeInMillis = dateTimeInMillis,
    repeatCode = repeat.getRepeatCode(),
    isTimeSelected = isTimeSelected
)


