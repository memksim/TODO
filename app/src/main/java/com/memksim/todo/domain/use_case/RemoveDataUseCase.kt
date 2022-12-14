package com.memksim.todo.domain.use_case

import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.ReminderDto
import javax.inject.Inject

class RemoveDataUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(dtoList: List<ReminderDto>) =
        localRepository.deleteReminder(dtoList = dtoList)

}