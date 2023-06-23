package com.memksim.todo.domain.use_case

import com.memksim.todo.data.repository.LocalRepositoryImpl
import com.memksim.todo.domain.model.Task
import javax.inject.Inject

class GetDataItemUseCase @Inject constructor(
    private val localRepositoryImpl: LocalRepositoryImpl
) {

    suspend operator fun invoke(id: Int): Task =
        localRepositoryImpl.getTask(id = id)

}