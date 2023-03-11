package com.memksim.todo.ui.utils.mvi

import androidx.lifecycle.ViewModel
import com.memksim.todo.ui.utils.mvi.UiEvent
import com.memksim.todo.ui.utils.mvi.UiState

abstract class BaseViewModel<T: UiState>: ViewModel() {

    /**
     * Reduce new state
     * @param uiEvent - user intent
     * */
    abstract fun handleEvent(uiEvent: UiEvent)

}