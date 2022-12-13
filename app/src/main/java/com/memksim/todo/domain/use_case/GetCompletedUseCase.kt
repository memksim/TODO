package com.memksim.todo.domain.use_case

import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.ReminderDto

class GetCompletedUseCase(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(): List<ReminderDto> =
        localRepository.getCompletedReminders()

}