package com.mywebsite.todoapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.mywebsite.todoapp.PrefsRepository
import com.mywebsite.todoapp.RoomRepository
import com.mywebsite.todoapp.data.PrefsRepositoryImpl
import com.mywebsite.todoapp.data.PrefsRepositoryImpl.Companion.PREFS_NAME
import com.mywebsite.todoapp.data.RoomRepositoryImpl
import com.mywebsite.todoapp.data.RoomRepositoryImpl.Companion.DATABASE_NAME
import com.mywebsite.todoapp.room.AppDatabase
import com.mywebsite.todoapp.room.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DATABASE_NAME
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideToDoDao(appDatabase: AppDatabase) = appDatabase.todoDao()

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesRoomRepository(toDoDao: ToDoDao): RoomRepository = RoomRepositoryImpl(toDoDao)

    @Singleton
    @Provides
    fun providesPrefsRepository(sharedPreferences: SharedPreferences): PrefsRepository = PrefsRepositoryImpl(sharedPreferences)
}