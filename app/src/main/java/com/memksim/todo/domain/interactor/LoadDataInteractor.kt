package com.memksim.todo.domain.interactor

import com.memksim.todo.domain.constants.ReminderDtoKey
import com.memksim.todo.domain.constants.ReminderDtoKey.*
import com.memksim.todo.domain.model.ReminderDto
import com.memksim.todo.domain.use_case.GetCompletedUseCase
import com.memksim.todo.domain.use_case.GetUpcomingUseCase
import javax.inject.Inject

class LoadDataInteractor @Inject constructor(
    private val getUpcomingUseCase: GetUpcomingUseCase,
    private val getCompletedUseCase: GetCompletedUseCase
) {

    suspend operator fun invoke(key: ReminderDtoKey): Map<ReminderDtoKey, List<ReminderDto>> {
        return when (key) {
            UPCOMING -> {
                mapOf(
                    key to getUpcomingUseCase.invoke()
                )
            }
            COMPLETED ->{
                mapOf(
                    key to getCompletedUseCase.invoke()
                )
            }
            BOTH -> {
                mapOf(
                    UPCOMING to getUpcomingUseCase.invoke(),
                    COMPLETED to getCompletedUseCase.invoke()
                )
            }
        }
    }

}