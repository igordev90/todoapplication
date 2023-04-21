package com.mywebsite.todoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mywebsite.todoapp.ToDoItem

@Database(entities = [ToDoItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}