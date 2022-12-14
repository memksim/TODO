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
    ): AddDataUseCase = AddDataUseCase(localRepository = repository)

    @Provides
    fun provideUpdateDataUseCase(
        repository: LocalRepository
    ): UpdateDataUseCase = UpdateDataUseCase(localRepository = repository)

    @Provides
    fun provideRemoveDataUseCase(
        repository: LocalRepository
    ): RemoveDataUseCase = RemoveDataUseCase(localRepository = repository)

    @Provides
    fun provideGetUpcomingUseCase(
        repository: LocalRepository
    ): GetUpcomingUseCase = GetUpcomingUseCase(localRepository = repository)

    @Provides
    fun provideGetCompletedUseCase(
        repository: LocalRepository
    ): GetCompletedUseCase = GetCompletedUseCase(localRepository = repository)

    @Provides
    fun provideGetDataItemUseCase(
        repository: LocalRepository
    ): GetDataItemUseCase = GetDataItemUseCase(localRepository = repository)

}