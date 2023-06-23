package com.memksim.todo.di

import com.memksim.todo.data.repository.LocalRepositoryImpl
import com.memksim.todo.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [LocalStorageModule::class])
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideAddDataUseCase(
        repository: LocalRepositoryImpl
    ): AddTaskUseCase = AddTaskUseCase(localRepositoryImpl = repository)

    @Provides
    fun provideUpdateDataUseCase(
        repository: LocalRepositoryImpl
    ): UpdateTaskUseCase = UpdateTaskUseCase(localRepositoryImpl = repository)

    @Provides
    fun provideRemoveDataUseCase(
        repository: LocalRepositoryImpl
    ): RemoveTaskUseCase = RemoveTaskUseCase(localRepositoryImpl = repository)

    @Provides
    fun provideGetTasksUseCase(
        repository: LocalRepositoryImpl
    ): GetTasksUseCase = GetTasksUseCase(localRepositoryImpl = repository)
    @Provides
    fun provideGetDataItemUseCase(
        repository: LocalRepositoryImpl
    ): GetDataItemUseCase = GetDataItemUseCase(localRepositoryImpl = repository)

}