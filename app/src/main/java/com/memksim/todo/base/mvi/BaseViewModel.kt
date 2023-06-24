package com.memksim.todo.base.mvi

import androidx.lifecycle.ViewModel
import com.memksim.todo.utils.consts.ADD_TASK_ERROR
import com.memksim.todo.utils.consts.LOAD_TASK_ERROR
import com.memksim.todo.utils.consts.REMOVE_TASK_ERROR
import com.memksim.todo.utils.consts.UNKNOWN_ERROR
import com.memksim.todo.utils.consts.UPDATE_TASK_ERROR
import com.memksim.todo.utils.exceptions.AddTaskException
import com.memksim.todo.utils.exceptions.LoadDataException
import com.memksim.todo.utils.exceptions.RemoveTaskException
import com.memksim.todo.utils.exceptions.UpdateTaskException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    protected abstract val _state: MutableStateFlow<UiState>
    val state: StateFlow<UiState> = _state

    protected abstract fun handleEvent(uiEvent: UiEvent)

    protected fun handleException(exception: Throwable) {
        renderState(_state.value.apply {
            loading = false
            error = true
            toastMessage = when (exception) {
                is LoadDataException -> {
                    LOAD_TASK_ERROR
                }

                is AddTaskException -> {
                    ADD_TASK_ERROR
                }

                is UpdateTaskException -> {
                    UPDATE_TASK_ERROR
                }

                is RemoveTaskException -> {
                    REMOVE_TASK_ERROR
                }

                else -> {
                    UNKNOWN_ERROR
                }
            }
        })
    }

    protected fun renderState(state: UiState) {
        _state.value = state
    }

}