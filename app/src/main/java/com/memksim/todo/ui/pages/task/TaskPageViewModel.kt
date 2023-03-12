package com.memksim.todo.ui.pages.task

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.memksim.todo.base.consts.*
import com.memksim.todo.base.exceptions.AddTaskException
import com.memksim.todo.base.exceptions.LoadDataException
import com.memksim.todo.base.exceptions.RemoveTaskException
import com.memksim.todo.base.exceptions.UpdateTaskException
import com.memksim.todo.domain.interactor.UpdateDataInteractor
import com.memksim.todo.domain.utils.enums.TaskState.COMPLETED
import com.memksim.todo.domain.utils.enums.TaskState.UPDATED
import com.memksim.todo.ui.utils.converters.toDto
import com.memksim.todo.ui.utils.enums.Never
import com.memksim.todo.ui.utils.enums.Repeat
import com.memksim.todo.ui.utils.mvi.BaseViewModel
import com.memksim.todo.ui.utils.model.TaskItemUiState
import com.memksim.todo.ui.utils.mvi.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskPageViewModel @Inject constructor(
    private val updateDataInteractor: UpdateDataInteractor
) : BaseViewModel<TaskPageUiState>() {

    private var _viewState: MutableStateFlow<TaskPageUiState> = MutableStateFlow(TaskPageUiState())
    val viewState: StateFlow<TaskPageUiState> = _viewState

    fun stateTask(task: TaskItemUiState) {
        _viewState.value = _viewState.value.copy(
            task = task
        )
    }

    private fun updateData() {
        Log.d(TAG, "updateData: ${_viewState.value.task.toDto()}")
        viewModelScope.launch {
            updateDataInteractor(_viewState.value.task.toDto())
                .catch {
                    handleException(it)
                }
                .collect()
        }
    }

    override fun handleEvent(uiEvent: UiEvent) {
        when (uiEvent as TaskPageUiEvent) {
            is TaskPageUiEvent.CompleteTask -> {
                _viewState.value = _viewState.value.copy(
                    task = _viewState.value.task.copy(
                        itemState = COMPLETED
                    )
                )
            }
            is TaskPageUiEvent.UpdateTask -> {
                val value = (uiEvent as TaskPageUiEvent.UpdateTask)
                val prevTask = _viewState.value.task
                _viewState.value = _viewState.value.copy(
                    task = _viewState.value.task.copy(
                        title = value.title.ifEmpty { prevTask.title },
                        note = value.note.ifEmpty { prevTask.note },
                        date = value.date.ifEmpty { prevTask.date },
                        time = value.time.ifEmpty { prevTask.time },
                        repeat = value.repeat,
                        itemState = UPDATED
                    )
                )
            }
            is TaskPageUiEvent.DeleteTask -> {
                _viewState.value = _viewState.value.copy(
                    task = _viewState.value.task.copy(
                        itemState = COMPLETED
                    )
                )
            }
        }
        updateData()
    }

    override fun handleException(exception: Throwable) {
        _viewState.value = _viewState.value.copy(
            toast = when (exception) {
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
        )
    }

    sealed class TaskPageUiEvent() : UiEvent {

        object CompleteTask : TaskPageUiEvent()

        class UpdateTask(
            val title: String = "",
            val note: String = "",
            val date: String = "",
            val time: String = "",
            val repeat: Repeat = Never
        ) : TaskPageUiEvent()

        object DeleteTask : TaskPageUiEvent()
    }

}