package com.memksim.todo.ui.pages.main

import android.util.Log
import androidx.lifecycle.*
import com.memksim.todo.utils.consts.*
import com.memksim.todo.utils.exceptions.AddTaskException
import com.memksim.todo.utils.exceptions.LoadDataException
import com.memksim.todo.utils.exceptions.RemoveTaskException
import com.memksim.todo.utils.exceptions.UpdateTaskException
import com.memksim.todo.domain.interactor.LoadDataInteractor
import com.memksim.todo.domain.interactor.UpdateDataInteractor
import com.memksim.todo.domain.utils.enums.TaskState.COMPLETED
import com.memksim.todo.ui.utils.mvi.BaseViewModel
import com.memksim.todo.ui.utils.mvi.UiEvent
import com.memksim.todo.ui.utils.converters.toDto
import com.memksim.todo.ui.utils.converters.toItemUiState
import com.memksim.todo.ui.utils.model.TaskItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val loadDataInteractor: LoadDataInteractor,
    private val updateDataInteractor: UpdateDataInteractor
) : BaseViewModel<MainPageUiState>() {

    private var _viewState: MutableStateFlow<MainPageUiState> = MutableStateFlow(MainPageUiState())
    val viewState: StateFlow<MainPageUiState> = _viewState

    init {
        loadData()
    }

    override fun handleEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is MainPageEvent.SaveNewTask -> saveTask(uiEvent.task)
            is MainPageEvent.UpdateNewTaskInfo -> {
                updateNewTaskInfo(
                    title = uiEvent.title,
                    note = uiEvent.note,
                    date = uiEvent.date,
                    time = uiEvent.time
                )
            }
            is MainPageEvent.CompleteTask -> {
                completeTask(uiEvent.task)
            }
        }
    }

    private fun completeTask(task: TaskItemUiState) {
        viewModelScope.launch {
            updateDataInteractor(
                task = task.copy(
                    itemState = COMPLETED
                ).toDto()
            ).catch {
                handleException(it)
            }.collect{
                delay(COMPLETE_TASK_DELAY)
                loadData()
            }
        }
    }

    private fun updateNewTaskInfo(
        title: String,
        note: String,
        date: String,
        time: String
    ) {
        val task = _viewState.value.newTask
        _viewState.value = _viewState.value.copy(
            newTask = task.copy(
                title = title.ifEmpty { task.title },
                note = note.ifEmpty { task.note },
                date = date.ifEmpty { task.date },
                time = time.ifEmpty { task.time }
            )
        )
    }

    private fun saveTask(task: TaskItemUiState) {
        viewModelScope.launch {
            updateDataInteractor(task = task.toDto())
                .catch {
                    handleException(it)
                }
                .collect {
                    loadData()
                }
        }
    }

    override fun handleException(exception: Throwable) {
        _viewState.value = _viewState.value.copy(
            isLoading = false,
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

    private fun loadData(
        toast: String? = null
    ) {
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(
                isLoading = true
            )
            loadDataInteractor()
                .catch {
                    handleException(it)
                }
                .collect {
                    Log.d(TAG, "loadData: $it")
                    _viewState.value = _viewState.value.copy(
                        tasks = it.map { item ->
                            item.toItemUiState()
                        },
                        isLoading = false,
                        newTask = TaskItemUiState(),
                        toast = toast
                    )
                }
        }
    }

    sealed class MainPageEvent : UiEvent {

        class UpdateNewTaskInfo(
            val title: String = "",
            val note: String = "",
            val date: String = "",
            val time: String = ""
        ) : MainPageEvent()

        class SaveNewTask(val task: TaskItemUiState) : MainPageEvent()
        class CompleteTask(val task: TaskItemUiState) : MainPageEvent()

    }

}