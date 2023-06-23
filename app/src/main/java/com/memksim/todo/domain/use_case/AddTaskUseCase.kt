package com.memksim.todo.domain.use_case

import com.memksim.todo.utils.exceptions.AddTaskException
import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.Task
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(task: Task) {
        try {
            localRepository.insertTask(task = task)
        } catch (e: Throwable) {
            throw AddTaskException(cause = e)
        }
    }

}