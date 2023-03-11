package com.memksim.todo.ui.pages.task

import com.memksim.todo.domain.interactor.UpdateDataInteractor
import com.memksim.todo.ui.utils.mvi.BaseViewModel
import com.memksim.todo.ui.utils.model.TaskItemUiState
import com.memksim.todo.ui.utils.mvi.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TaskPageViewModel @Inject constructor(
    private val updateDataInteractor: UpdateDataInteractor
): BaseViewModel<TaskPageUiState>() {

    private var _viewState: MutableStateFlow<TaskPageUiState> = MutableStateFlow(TaskPageUiState())
    val viewState: StateFlow<TaskPageUiState> = _viewState

    fun putValue(task: TaskItemUiState){
        _viewState.value = _viewState.value.copy(
            task = task
        )
    }

    override fun handleEvent(uiEvent: UiEvent) {
        TODO("Not yet implemented")
    }

}