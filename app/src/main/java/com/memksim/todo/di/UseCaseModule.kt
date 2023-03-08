package com.memksim.todo.di

import com.memksim.todo.data.repository.LocalRepository
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
        repository: LocalRepository
    ): AddTaskUseCase = AddTaskUseCase(localRepository = repository)

    @Provides
    fun provideUpdateDataUseCase(
        repository: LocalRepository
    ): UpdateTaskUseCase = UpdateTaskUseCase(localRepository = repository)

    @Provides
    fun provideRemoveDataUseCase(
        repository: LocalRepository
    ): RemoveTaskUseCase = RemoveTaskUseCase(localRepository = repository)

    @Provides
    fun provideGetTasksUseCase(
        repository: LocalRepository
    ): GetTasksUseCase = GetTasksUseCase(localRepository = repository)
    @Provides
    fun provideGetDataItemUseCase(
        repository: LocalRepository
    ): GetDataItemUseCase = GetDataItemUseCase(localRepository = repository)

}