package com.mywebsite.todoapp.data

import android.content.SharedPreferences
import com.mywebsite.todoapp.PrefsRepository
import com.mywebsite.todoapp.ToDoItem
import javax.inject.Inject

/**
 * Repository that handles logic with shared preferences
 */
class PrefsRepositoryImpl @Inject constructor(
    private val sharedPref: SharedPreferences
) : PrefsRepository {

    override fun getTodoItem(): ToDoItem {
        val title =
            sharedPref.getString(PREFS_TITLE_KEY, PREFS_DEFAULT_VALUE) ?: PREFS_DEFAULT_VALUE
        val description =
            sharedPref.getString(PREFS_DESCRIPTION_KEY, PREFS_DEFAULT_VALUE) ?: PREFS_DEFAULT_VALUE
        return ToDoItem(0, title, description)
    }

    override fun saveDataInPrefs(key: String, value: String) {
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    companion object {
        const val PREFS_TITLE_KEY = "titleKey"
        const val PREFS_DESCRIPTION_KEY = "descriptionKey"
        const val PREFS_NAME = "preferences"
        const val PREFS_DEFAULT_VALUE = ""
    }
}