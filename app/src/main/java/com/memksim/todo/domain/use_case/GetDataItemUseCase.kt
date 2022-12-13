package com.memksim.todo.domain.use_case

import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.ReminderDto

class GetDataItemUseCase(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(id: Int): ReminderDto =
        localRepository.getReminder(id = id)

}