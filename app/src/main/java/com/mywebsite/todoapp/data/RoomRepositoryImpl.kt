package com.mywebsite.todoapp.data

import com.mywebsite.todoapp.RoomRepository
import com.mywebsite.todoapp.ToDoItem
import com.mywebsite.todoapp.room.ToDoDao
import javax.inject.Inject

/**
 * Repository that handles logic with room data base
 */
class RoomRepositoryImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : RoomRepository {

    override fun getAllItems() : List<ToDoItem> {
       return toDoDao.getAllItems()
    }

    override fun insertItem(item: ToDoItem) {
        toDoDao.insertItem(item)
    }

    override fun updateItem(item: ToDoItem) {
        toDoDao.updateItem(item)
    }

    override fun deleteItem(item: ToDoItem) {
        toDoDao.deleteItem(item)
    }

    companion object {
        const val DATABASE_NAME = "database-name"
    }

}