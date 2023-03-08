package com.memksim.todo.ui.pages.main

import androidx.lifecycle.*
import com.memksim.todo.base.consts.*
import com.memksim.todo.base.exceptions.AddTaskException
import com.memksim.todo.base.exceptions.LoadDataException
import com.memksim.todo.base.exceptions.RemoveTaskException
import com.memksim.todo.base.exceptions.UpdateTaskException
import com.memksim.todo.domain.utils.enums.TaskDtoKey.*
import com.memksim.todo.domain.utils.enums.TaskState.*
import com.memksim.todo.domain.interactor.LoadDataInteractor
import com.memksim.todo.domain.interactor.UpdateDataInteractor
import com.memksim.todo.ui.base.BaseViewModel
import com.memksim.todo.ui.base.UiEvent
import com.memksim.todo.ui.converters.toDto
import com.memksim.todo.ui.converters.toItemUiState
import com.memksim.todo.ui.utils.enums.SearchAppBarState.*
import com.memksim.todo.ui.utils.enums.SortCondition.*
import dagger.hilt.android.lifecycle.HiltViewModel
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

    override fun handleEvent(uiEvent: UiEvent) {
        when(uiEvent){
            is MainPageEvent.InitialEvent -> loadData()
            is MainPageEvent.CreateNewTask -> saveTask(uiEvent.task)
        }
    }

    private fun saveTask(task: MainPageItemUiState){
        viewModelScope.launch {
            updateDataInteractor(task = task.toDto())
                .catch {
                    handleException(it)
                }
                .collect{
                    loadData()
                }
        }
    }

    private fun handleException(exception: Throwable){
        _viewState.value = _viewState.value.copy(
            isLoading = false,
            toast = when(exception){
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

    private fun loadData(){
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(
                isLoading = true
            )
            loadDataInteractor()
                .catch {
                    handleException(it)
                }
                .collect{
                    _viewState.value = _viewState.value.copy(
                        tasks = it.map { item ->
                            item.toItemUiState()
                        },
                        isLoading = false
                    )
                }
        }
    }

    sealed class MainPageEvent: UiEvent{

        object InitialEvent: MainPageEvent()
        class CreateNewTask(val task: MainPageItemUiState): MainPageEvent()

    }

}