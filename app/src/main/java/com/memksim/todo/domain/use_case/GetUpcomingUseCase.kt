package com.memksim.todo.domain.use_case

import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.TaskDto
import javax.inject.Inject

class GetUpcomingUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(): List<TaskDto> =
        localRepository.getUpcomingTasks()

}