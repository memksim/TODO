package com.memksim.todo.domain.use_case

import com.memksim.todo.utils.exceptions.LoadDataException
import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.Task
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(): List<Task> =
        try {
            localRepository.getTasks()
        } catch (e: Throwable) {
            throw LoadDataException(cause = e)
        }

}