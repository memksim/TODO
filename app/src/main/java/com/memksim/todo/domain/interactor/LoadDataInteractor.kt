package com.memksim.todo.domain.interactor

import android.util.Log
import com.memksim.todo.utils.consts.INTERACTOR_MESSAGE
import com.memksim.todo.utils.consts.TAG
import com.memksim.todo.utils.exceptions.DatabaseException
import com.memksim.todo.utils.exceptions.LoadDataException
import com.memksim.todo.domain.model.Task
import com.memksim.todo.domain.use_case.GetTasksUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class LoadDataInteractor @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) {

    suspend operator fun invoke(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<List<Task>> =
        withContext(dispatcher) {
            val tasks = async { getTasksUseCase() }
            flow {
                try {
                    emit(tasks.await())
                } catch (e: DatabaseException) {
                    Log.e(TAG, INTERACTOR_MESSAGE, e)
                    throw LoadDataException()
                }
            }
        }


}