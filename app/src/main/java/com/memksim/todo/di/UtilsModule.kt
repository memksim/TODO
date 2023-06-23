package com.memksim.todo.di

import android.content.Context
import com.memksim.todo.utils.notifications.AlarmScheduler
import com.memksim.todo.utils.notifications.AlarmSchedulerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    fun providesAlarmSchedulerImpl(@ApplicationContext context: Context): AlarmSchedulerImpl =
        AlarmSchedulerImpl(context)

}

@Module(includes = [UtilsModule::class])
@InstallIn(SingletonComponent::class)
interface UtilsBindsModule {
    @Binds
    fun bindsAlarmScheduler(alarmSchedulerImpl: AlarmSchedulerImpl): AlarmScheduler
}