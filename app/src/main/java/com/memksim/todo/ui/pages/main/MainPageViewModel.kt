package com.memksim.todo.ui.pages.main

import androidx.lifecycle.*
import com.memksim.todo.base.mvi.BaseViewModel
import com.memksim.todo.base.mvi.UiEvent
import com.memksim.todo.base.mvi.UiState
import com.memksim.todo.domain.use_case.CompleteTaskUseCase
import com.memksim.todo.domain.use_case.LoadTasksUseCase
import com.memksim.todo.domain.use_case.SaveTaskUseCase
import com.memksim.todo.domain.use_case.UpdateTaskUseCase
import com.memksim.todo.ui.pages.main.MainPageUiState.MainPageItemUiState
import com.memksim.todo.ui.utils.converters.toTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val loadTasksUseCase: LoadTasksUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val completeTaskUseCase: CompleteTaskUseCase
) : BaseViewModel() {

    override val _state: MutableStateFlow<UiState> = MutableStateFlow(MainPageUiState())

    override fun handleEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is MainPageEvent.InitialEvent -> {
                loadData()
            }

            is MainPageEvent.SaveNewTask -> {
                saveTask(uiEvent.task)
            }

            is MainPageEvent.UpdateTaskInfo -> {
                updateTask(uiEvent.task)
            }

            is MainPageEvent.CompleteTask -> {
                completeTask(uiEvent.task)
            }
        }
    }

    private fun completeTask(task: MainPageItemUiState) {
        val tasks = (_state.value as MainPageUiState).tasks as MutableList
        tasks.remove(task)
        viewModelScope.launch {
            try {
                completeTaskUseCase.execute(task.toTask())
            } catch (e: Throwable) {
                handleException(e)
            }
        }
    }

    private fun updateTask(task: MainPageItemUiState) {
        val tasks = (_state.value as MainPageUiState).tasks as MutableList
        tasks.find {
            it.id == task.id
        }?.also {
            tasks[tasks.indexOf(it)] = task
            renderState(
                (_state.value as MainPageUiState).copy(
                    tasks = tasks
                )
            )
            viewModelScope.launch {
                try {
                    updateTaskUseCase.execute(task.toTask())
                } catch (e: Throwable) {
                    handleException(e)
                }
            }
        }
    }

    private fun saveTask(task: MainPageItemUiState) {
        val tasks = (_state.value as MainPageUiState).tasks as MutableList
        tasks.add(task)
        renderState(
            state = (_state.value as MainPageUiState).copy(
                tasks = tasks
            )
        )
        viewModelScope.launch {
            try {
                saveTaskUseCase.execute(task.toTask())
            } catch (e: Throwable) {
                handleException(e)
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            kotlin.runCatching {
                loadTasksUseCase.execute()
            }.onFailure {
                handleException(it)
            }.onSuccess {

            }
        }
    }

    sealed class MainPageEvent : UiEvent {

        object InitialEvent : MainPageEvent()

        class UpdateTaskInfo(val task: MainPageItemUiState) : MainPageEvent()

        class SaveNewTask(val task: MainPageItemUiState) : MainPageEvent()

        class CompleteTask(val task: MainPageItemUiState) : MainPageEvent()

    }

}