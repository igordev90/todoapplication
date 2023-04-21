package com.mywebsite.todoapp

import android.content.SharedPreferences
import com.mywebsite.todoapp.data.PrefsRepositoryImpl
import com.mywebsite.todoapp.data.PrefsRepositoryImpl.Companion.PREFS_DEFAULT_VALUE
import com.mywebsite.todoapp.data.PrefsRepositoryImpl.Companion.PREFS_DESCRIPTION_KEY
import com.mywebsite.todoapp.data.PrefsRepositoryImpl.Companion.PREFS_TITLE_KEY
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class PrefsRepositoryImplTest {

    private lateinit var subject:PrefsRepositoryImpl
    private val sharedPreferencesMock : SharedPreferences = mock()
    private val editorMock : SharedPreferences.Editor = mock()
    private val titleTest = "titleTest"
    private val descriptionTest = "descriptionTest"
    private val keyTest = "keyTest"
    private val valueTest = "valueTest"

    @Before
    fun setup(){
        subject = PrefsRepositoryImpl(sharedPreferencesMock)
    }

    @Test
    fun getToDoItem_success(){
        `when`(sharedPreferencesMock.getString(PREFS_TITLE_KEY,PREFS_DEFAULT_VALUE)).thenReturn(titleTest)
        `when`(sharedPreferencesMock.getString(PREFS_DESCRIPTION_KEY,PREFS_DEFAULT_VALUE)).thenReturn(descriptionTest)
        val result = subject.getTodoItem()
        assertEquals("titleTest",result.title)
        assertEquals("descriptionTest",result.description)
    }

    @Test
    fun saveDataInPrefs_success(){
        `when`(sharedPreferencesMock.edit()).thenReturn(editorMock)
        subject.saveDataInPrefs(keyTest,valueTest)
        verify(editorMock).putString(keyTest,valueTest)
    }
}