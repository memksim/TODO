package com.memksim.todo.domain.use_case

import com.memksim.todo.utils.exceptions.UpdateTaskException
import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.Task
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(task: Task) {
        try {
            localRepository.updateTask(task = task)
        } catch (e: Throwable) {
            throw UpdateTaskException(cause = e)
        }
    }
}