package com.memksim.todo.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<T: UiState>: ViewModel() {

    sealed class UiEvent
    sealed class Action

    abstract val viewState: MutableStateFlow<T>

    abstract fun handleEvent(e: UiEvent)

    abstract fun render()

}