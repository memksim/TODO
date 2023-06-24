package com.memksim.todo.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> asyncTryCatching(
    dispatchers: CoroutineDispatcher = Dispatchers.IO,
    cause: Throwable,
    block: suspend () -> T
): T {
    try {
        return withContext(dispatchers) {
            return@withContext block()
        }
    } catch (e: Throwable) {
        throw cause.initCause(e)
    }
}