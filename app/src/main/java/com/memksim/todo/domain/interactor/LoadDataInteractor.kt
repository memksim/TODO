package com.memksim.todo.domain.interactor

import com.memksim.todo.domain.utils.enums.TaskDtoKey
import com.memksim.todo.domain.utils.enums.TaskDtoKey.*
import com.memksim.todo.domain.model.TaskDto
import com.memksim.todo.domain.use_case.GetCompletedUseCase
import com.memksim.todo.domain.use_case.GetUpcomingUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoadDataInteractor @Inject constructor(
    private val getUpcomingUseCase: GetUpcomingUseCase,
    private val getCompletedUseCase: GetCompletedUseCase
) {

    suspend operator fun invoke(key: TaskDtoKey): Map<TaskDtoKey, List<TaskDto>> {
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