package com.baubuddy.v2.di

import android.content.Context
import androidx.room.Room
import com.baubuddy.v2.model.TaskDao
import com.baubuddy.v2.model.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesTaskDao(taskDatabase: TaskDatabase): TaskDao = taskDatabase.taskDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): TaskDatabase
    = Room.databaseBuilder(
        context,
        TaskDatabase::class.java,
        "task_db")
        .fallbackToDestructiveMigration()
        .build()
}