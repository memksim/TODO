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
        getUpcomingUseCase: GetUpcomingUseCase,
        getCompletedUseCase: GetCompletedUseCase
    ): LoadDataInteractor = LoadDataInteractor(
        getUpcomingUseCase = getUpcomingUseCase,
        getCompletedUseCase = getCompletedUseCase
    )

    @Provides
    fun provideUpdateDataInteractor(
        addDataUseCase: AddDataUseCase,
        updateDataUseCase: UpdateDataUseCase,
        removeDataUseCase: RemoveDataUseCase
    ): UpdateDataInteractor = UpdateDataInteractor(
        addDataUseCase = addDataUseCase,
        updateDataUseCase = updateDataUseCase,
        removeDataUseCase = removeDataUseCase
    )

}