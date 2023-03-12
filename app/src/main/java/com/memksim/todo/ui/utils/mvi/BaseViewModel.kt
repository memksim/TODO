package com.memksim.todo.ui.utils.mvi

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T: UiState>: ViewModel() {

    /**
     * Reduce new state
     * @param uiEvent - user intent
     * */
    abstract fun handleEvent(uiEvent: UiEvent)

    abstract fun handleException(exception: Throwable)

}