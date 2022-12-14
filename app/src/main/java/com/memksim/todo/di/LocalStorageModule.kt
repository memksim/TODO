package com.memksim.todo.di

import android.content.Context
import androidx.room.Room
import com.memksim.todo.data.local.ReminderDao
import com.memksim.todo.data.local.ReminderDatabase
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
    ): ReminderDatabase = Room.databaseBuilder(
        context,
        ReminderDatabase::class.java,
        "ReminderDb"
    ).build()

    @Provides
    @Singleton
    fun provideDao(
        db: ReminderDatabase
    ): ReminderDao = db.getDao()

    @Provides
    fun provideRepository(
        dao: ReminderDao
    ): LocalRepository = LocalRepository(dao = dao)

}