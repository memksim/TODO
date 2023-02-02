package com.memksim.todo.domain.use_case

import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.TaskDto
import javax.inject.Inject

class GetDataItemUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(id: Int): TaskDto =
        localRepository.getTask(id = id)

}