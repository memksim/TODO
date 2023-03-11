package com.memksim.todo.ui.utils.model

import android.os.Parcelable
import com.memksim.todo.domain.utils.enums.TaskState
import com.memksim.todo.ui.utils.enums.Never
import com.memksim.todo.ui.utils.enums.Repeat
import com.memksim.todo.ui.utils.mvi.ItemUiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskItemUiState(
    val id: Int = 0,
    val title: String = "",
    val note: String = "",
    val date: String = "",
    val time: String = "",
    val repeat: Repeat = Never,
    val isCompleted: Boolean = false,
    val itemState: TaskState = TaskState.NEW
): ItemUiState, Parcelable