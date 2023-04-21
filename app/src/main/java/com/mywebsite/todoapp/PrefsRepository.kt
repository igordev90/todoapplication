package com.mywebsite.todoapp

interface PrefsRepository {

    /**
     * Return todo item from prefs
     */
    fun getTodoItem() : ToDoItem

    /**
     * Saving data in prefs
     */
    fun saveDataInPrefs(key: String, value: String)
}