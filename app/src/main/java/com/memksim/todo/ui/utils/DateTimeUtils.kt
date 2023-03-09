package com.memksim.todo.ui.utils

fun Int.toDateTimeItem(): String =
    if (this / 10 == 0) "0$this" else "$this"

