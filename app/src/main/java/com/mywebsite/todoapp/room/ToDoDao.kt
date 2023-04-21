package com.mywebsite.todoapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mywebsite.todoapp.ToDoItem

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todoitem")
    fun getAllItems(): List<ToDoItem>

    @Insert
     fun insertItem(toDoItem: ToDoItem)

    @Delete
     fun deleteItem(toDoItem: ToDoItem)

    @Update
    fun updateItem(toDoItem: ToDoItem)
}