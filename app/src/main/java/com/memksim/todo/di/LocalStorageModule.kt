package com.memksim.todo.di

import android.content.Context
import androidx.room.Room
import com.memksim.todo.data.local.TaskDao
import com.memksim.todo.data.local.TaskDatabase
import com.memksim.todo.data.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TaskDatabase = Room.databaseBuilder(
        context,
        TaskDatabase::class.java,
        "TaskDb"
    ).build()

    @Provides
    @Singleton
    fun provideDao(
        db: TaskDatabase
    ): TaskDao = db.getDao()

    @Provides
    fun provideRepository(
        dao: TaskDao
    ): LocalRepository = LocalRepository(dao = dao)

}