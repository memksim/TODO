package com.memksim.todo.view.main_page

import androidx.lifecycle.*
import com.memksim.todo.domain.utils.enums.TaskDtoKey
import com.memksim.todo.domain.utils.enums.TaskDtoKey.*
import com.memksim.todo.domain.utils.enums.TaskState.*
import com.memksim.todo.domain.interactor.LoadDataInteractor
import com.memksim.todo.domain.interactor.UpdateDataInteractor
import com.memksim.todo.domain.model.TaskDto
import com.memksim.todo.view.converters.convertDtoListToItemUiStateList
import com.memksim.todo.view.converters.convertItemUiStateListToDtoList
import com.memksim.todo.view.utils.enums.SearchAppBarState
import com.memksim.todo.view.utils.enums.SearchAppBarState.*
import com.memksim.todo.view.utils.enums.SortCondition
import com.memksim.todo.view.utils.enums.SortCondition.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val loadDataInteractor: LoadDataInteractor,
    private val updateDataInteractor: UpdateDataInteractor
) : ViewModel() {

    private val _liveData: MutableLiveData<MainPageUiState> = liveData {
        emit(
            buildState(
                data = loadDataInteractor.invoke(key = BOTH)
            )
        )
    } as MutableLiveData<MainPageUiState>
    val liveData: LiveData<MainPageUiState> = _liveData

    /**
     * call`s once when view model init
     * */
    private fun buildState(
        data: Map<TaskDtoKey, List<TaskDto>>
    ): MainPageUiState {
        return MainPageUiState(
            upcomingRemindersList = convertDtoListToItemUiStateList(data[UPCOMING]),
            completedRemindersList = convertDtoListToItemUiStateList(data[COMPLETED]),
            conditionForSortReminders = DEFAULT,
            searchTopAppBarState = CLOSED,
            searchText = ""
        )
    }

    fun completeReminder(reminder: MainPageItemUiState){
        val state = _liveData.value ?: return
        val upcomingList = state.upcomingRemindersList.toMutableList()
        val completedList = state.completedRemindersList.toMutableList()

        upcomingList.remove(reminder)
        completedList.add(
            index = 0,
            element = reminder.copy(
                isCompleted = true,
                itemState = UPDATED
            )
        )

        updateState(
            upcomingRemindersList = upcomingList,
            completedRemindersList = completedList
        )
    }

    fun updateReminder(reminder: MainPageItemUiState){
        val state = _liveData.value ?: return

        val itemList = if (reminder.isCompleted) {
            state.completedRemindersList.toMutableList()
        } else {
            state.upcomingRemindersList.toMutableList()
        }

        var index = 0
        itemList.forEach {
            if (it.id == reminder.id) return
            index++
        }
        itemList[index] = reminder.copy(itemState = UPDATED)

        if (reminder.isCompleted) {
            updateState(completedRemindersList = itemList)
        } else {
            updateState(upcomingRemindersList = itemList)
        }
    }

    fun saveReminder(reminder: MainPageItemUiState) {
        val state = _liveData.value ?: return

        if (reminder.isCompleted) {
            val itemList = state.completedRemindersList.toMutableList()
            itemList.add(0, reminder.copy(itemState = NEW))
            updateState(completedRemindersList = itemList)
        } else {
            val itemList = state.upcomingRemindersList.toMutableList()
            itemList.add(0, reminder.copy(itemState = NEW))
            updateState(upcomingRemindersList = itemList)
        }
    }

    private fun updateState(
        upcomingRemindersList: List<MainPageItemUiState> = emptyList(),
        completedRemindersList: List<MainPageItemUiState> = emptyList(),
        conditionForSortReminders: SortCondition = DEFAULT,
        searchTopAppBarState: SearchAppBarState = CLOSED,
        searchText: String = ""
    ) {
        val state = _liveData.value ?: return

        _liveData.value = MainPageUiState(
            upcomingRemindersList = if(upcomingRemindersList.isEmpty()) {
                state.upcomingRemindersList
            }else{
                upcomingRemindersList
            },
            completedRemindersList = if(completedRemindersList.isEmpty()){
                state.completedRemindersList
            }else{
                completedRemindersList
            },
            conditionForSortReminders = if(conditionForSortReminders == DEFAULT){
                state.conditionForSortReminders
            }else{
                conditionForSortReminders
            },
            searchTopAppBarState = if(searchTopAppBarState == CLOSED){
                state.searchTopAppBarState
            }else{
                searchTopAppBarState
            },
            searchText = if(searchText.isBlank()) {
                state.searchText
            }else{
                searchText
            }
        )
    }

    private suspend fun saveStateLocally(){
        val state = _liveData.value ?: return
        val stateItemsList = mutableListOf<MainPageItemUiState>()

        stateItemsList.addAll(state.upcomingRemindersList)
        stateItemsList.addAll(state.completedRemindersList)

        updateDataInteractor.invoke(
            dtoList = convertItemUiStateListToDtoList(itemList = stateItemsList)
        )
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            saveStateLocally()
        }
    }

}