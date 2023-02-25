package com.memksim.todo.ui.pages.main

import androidx.lifecycle.*
import com.memksim.todo.domain.utils.enums.TaskDtoKey
import com.memksim.todo.domain.utils.enums.TaskDtoKey.*
import com.memksim.todo.domain.utils.enums.TaskState.*
import com.memksim.todo.domain.interactor.LoadDataInteractor
import com.memksim.todo.domain.interactor.UpdateDataInteractor
import com.memksim.todo.domain.model.TaskDto
import com.memksim.todo.ui.base.BaseViewModel
import com.memksim.todo.ui.converters.convertDtoListToItemUiStateList
import com.memksim.todo.ui.converters.convertItemUiStateListToDtoList
import com.memksim.todo.ui.utils.enums.SearchAppBarState
import com.memksim.todo.ui.utils.enums.SearchAppBarState.*
import com.memksim.todo.ui.utils.enums.SortCondition
import com.memksim.todo.ui.utils.enums.SortCondition.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val loadDataInteractor: LoadDataInteractor,
    private val updateDataInteractor: UpdateDataInteractor
) : BaseViewModel<MainPageUiState>() {

    override val viewState: MutableStateFlow<MainPageUiState>
        get() = TODO("Not yet implemented")

    override fun handleEvent(e: UiEvent) {
        TODO("Not yet implemented")
    }

    override fun render() {
        TODO("Not yet implemented")
    }


}