package com.memksim.todo.domain.interactor

import com.memksim.todo.domain.utils.enums.TaskState.*
import com.memksim.todo.domain.model.TaskDto
import com.memksim.todo.domain.use_case.AddDataUseCase
import com.memksim.todo.domain.use_case.RemoveDataUseCase
import com.memksim.todo.domain.use_case.UpdateDataUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UpdateDataInteractor @Inject constructor(
    private val addDataUseCase: AddDataUseCase,
    private val updateDataUseCase: UpdateDataUseCase,
    private val removeDataUseCase: RemoveDataUseCase
) {

    suspend operator fun invoke(dtoList: List<TaskDto>){
        val newItems = arrayListOf<TaskDto>()
        val updatedItems = arrayListOf<TaskDto>()
        val removedItems = arrayListOf<TaskDto>()

        dtoList.forEach {
            when(it.state){
                NEW -> {
                    newItems.add(it)
                }
                UPDATED -> {
                    updatedItems.add(it)
                }
                REMOVED -> {
                    removedItems.add(it)
                }
                SAME ->{
                    //This element remains the same, its row does not need to be changed in the database
                }
            }
        }

        addDataUseCase.invoke(newItems)
        updateDataUseCase.invoke(updatedItems)
        removeDataUseCase.invoke(removedItems)
    }

}