package com.memksim.todo.domain.use_case

import com.memksim.todo.utils.exceptions.UpdateTaskException
import com.memksim.todo.data.repository.LocalRepositoryImpl
import com.memksim.todo.domain.model.Task
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val localRepositoryImpl: LocalRepositoryImpl
) {

    suspend operator fun invoke(task: Task) {
        try {
            localRepositoryImpl.updateTask(task = task)
        } catch (e: Throwable) {
            throw UpdateTaskException(cause = e)
        }
    }
}