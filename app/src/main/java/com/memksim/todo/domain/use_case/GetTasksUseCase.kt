package com.memksim.todo.domain.use_case

import com.memksim.todo.base.exceptions.LoadDataException
import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.TaskDto
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(): List<TaskDto> =
        try {
            localRepository.getTasks()
        } catch (e: Throwable) {
            throw LoadDataException(cause = e)
        }

}