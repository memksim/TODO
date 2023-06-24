package com.memksim.todo.domain.use_case

import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadTasksUseCase @Inject constructor(
    private val repository: LocalRepository
) {

    suspend fun execute(dispatcher: CoroutineDispatcher = Dispatchers.IO): List<Task> =
        withContext(dispatcher) { repository.getAllTasksList() }

}