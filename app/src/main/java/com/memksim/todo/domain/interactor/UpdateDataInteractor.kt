package com.memksim.todo.domain.interactor

import com.memksim.todo.domain.constants.ReminderState.*
import com.memksim.todo.domain.model.ReminderDto
import com.memksim.todo.domain.use_case.AddDataUseCase
import com.memksim.todo.domain.use_case.RemoveDataUseCase
import com.memksim.todo.domain.use_case.UpdateDataUseCase
import javax.inject.Inject

class UpdateDataInteractor @Inject constructor(
    private val addDataUseCase: AddDataUseCase,
    private val updateDataUseCase: UpdateDataUseCase,
    private val removeDataUseCase: RemoveDataUseCase
) {

    suspend operator fun invoke(dtoList: List<ReminderDto>){
        val newItems = arrayListOf<ReminderDto>()
        val updatedItems = arrayListOf<ReminderDto>()
        val removedItems = arrayListOf<ReminderDto>()

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