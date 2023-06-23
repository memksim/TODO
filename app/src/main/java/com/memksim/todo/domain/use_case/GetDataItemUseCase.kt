package com.memksim.todo.domain.use_case

import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.Task
import javax.inject.Inject

class GetDataItemUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(id: Int): Task =
        localRepository.getTask(id = id)

}