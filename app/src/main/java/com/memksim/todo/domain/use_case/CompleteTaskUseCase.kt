package com.memksim.todo.domain.use_case

import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.Task
import javax.inject.Inject

class CompleteTaskUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend fun execute(task: Task) = localRepository.deleteTask(task)


}