package com.memksim.todo.di

import com.memksim.todo.domain.interactor.LoadDataInteractor
import com.memksim.todo.domain.interactor.UpdateDataInteractor
import com.memksim.todo.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [UseCaseModule::class])
@InstallIn(SingletonComponent::class)
class InteractorModule {

    @Provides
    fun provideLoadDataInteractor(
        getTasksUseCase: GetTasksUseCase
    ): LoadDataInteractor = LoadDataInteractor(
        getTasksUseCase = getTasksUseCase
    )

    @Provides
    fun provideUpdateDataInteractor(
        addTaskUseCase: AddTaskUseCase,
        updateTaskUseCase: UpdateTaskUseCase,
        removeTaskUseCase: RemoveTaskUseCase
    ): UpdateDataInteractor = UpdateDataInteractor(
        addTaskUseCase = addTaskUseCase,
        updateTaskUseCase = updateTaskUseCase,
        removeTaskUseCase = removeTaskUseCase
    )

}