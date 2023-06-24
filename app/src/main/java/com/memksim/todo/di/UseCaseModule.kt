package com.memksim.todo.di

import com.memksim.todo.data.repository.LocalRepository
import com.memksim.todo.domain.model.Task
import com.memksim.todo.domain.use_case.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module(includes = [LocalStorageModule::class])
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideLoadTasksUseCase(localRepository: LocalRepository): LoadTasksUseCase =
        LoadTasksUseCase(localRepository)

    @Provides
    fun provideSaveTaskUseCase(localRepository: LocalRepository): SaveTaskUseCase =
        SaveTaskUseCase(localRepository)

    @Provides
    fun provideUpdateTaskUseCase(localRepository: LocalRepository): UpdateTaskUseCase =
        UpdateTaskUseCase(localRepository)

    @Provides
    fun provideCompleteTaskUseCase(localRepository: LocalRepository): CompleteTaskUseCase =
        CompleteTaskUseCase(localRepository)


}
