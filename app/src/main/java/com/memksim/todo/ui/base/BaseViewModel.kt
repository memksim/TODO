package com.memksim.todo.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<T: UiState>: ViewModel() {

    /**
     * Reduce new state
     * @param uiEvent - user intent
     * */
    abstract fun handleEvent(uiEvent: UiEvent)

}