package com.mywebsite.todoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CustomDialogViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var subject : CustomDialogViewModel
    private val prefsRepository : PrefsRepository = mock()

    private val todoItemFake : ToDoItem = ToDoItem(0, "testTitle", "testDescription")
    private val keyTestValue : String = "testKey"
    private val valueTestValue : String = "testValue"

    @Before
    fun setup(){
        subject = CustomDialogViewModel(prefsRepository)
    }

    @Test
    fun getToDoItemFromPrefs_success(){
        `when`(prefsRepository.getTodoItem()).thenReturn(todoItemFake)
        subject.getToDoItemFromPrefs()
        val expectedResult = subject.todoItemResult.value?.title
        assertEquals("testTitle", expectedResult)
    }

    @Test
    fun saveDataInPrefs_success(){
        subject.saveDataInPrefs(keyTestValue,valueTestValue)
        verify(prefsRepository).saveDataInPrefs(keyTestValue,valueTestValue)
    }
}