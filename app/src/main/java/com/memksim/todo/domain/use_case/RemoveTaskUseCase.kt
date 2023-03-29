package com.memksim.todo.domain.use_case

import com.memksim.todo.utils.exceptions.RemoveTaskException
import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.TaskDto
import javax.inject.Inject

class RemoveTaskUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(task: TaskDto) {
        try {
            localRepository.removeTask(task = task)
        } catch (e: Throwable) {
            throw RemoveTaskException(cause = e)
        }
    }
}