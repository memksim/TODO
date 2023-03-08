package com.memksim.todo.domain.use_case

import com.memksim.todo.base.exceptions.UpdateTaskException
import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.TaskDto
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(task: TaskDto) {
        try {
            localRepository.updateTask(task = task)
        } catch (e: Throwable) {
            throw UpdateTaskException(cause = e)
        }
    }
}