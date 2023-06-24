package com.memksim.todo.base.mvi

interface UiState {
    var error: Boolean
    var loading: Boolean
    var toastMessage: String?
}