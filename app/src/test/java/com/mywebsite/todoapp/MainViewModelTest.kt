package com.mywebsite.todoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var subject: MainViewModel
    private val roomRepositoryMock: RoomRepository = mock()

    private val mockItemOne = ToDoItem(0, "testTitleOne", "testDescriptionOne")
    private val mockItemTwo = ToDoItem(1, "testTitleTwo", "testDescriptionTwo")
    private val mockItemThree = ToDoItem(2, "testTitleThree", "testDescriptionThree")
    private val mockItemFour = ToDoItem(0, "testTitleFour", "testDescriptionFour")

    private val mockList: List<ToDoItem> = listOf(
        mockItemOne,
        mockItemTwo
    )

    @Before
    fun setup() {
        subject = MainViewModel(roomRepositoryMock)
    }

    @Test
    fun getAllData_success() {
        `when`(roomRepositoryMock.getAllItems()).thenReturn(mockList)
        subject.getAllData()
        val expectedResult = subject.todoItemListResult.value?.size
        val firstItem = subject.todoItemListResult.value?.first()
        assertEquals(2, expectedResult)
        assertEquals("testTitleOne", firstItem?.title)
        assertEquals("testDescriptionOne", firstItem?.description)
    }

    @Test
    fun insertItem_success() {
        `when`(roomRepositoryMock.getAllItems()).thenReturn(mockList)
        subject.getAllData()
        subject.insertItem(mockItemThree)
        val expectedSize = subject.todoItemListResult.value?.size
        assertEquals(3, expectedSize)
        val lastItem = subject.todoItemListResult.value?.last()
        assertEquals("testTitleThree", lastItem?.title)
        assertEquals("testDescriptionThree", lastItem?.description)
    }

    @Test
    fun updateItem_success() {
        `when`(roomRepositoryMock.getAllItems()).thenReturn(mockList)
        subject.getAllData()
        subject.updateItem(mockItemFour)
        val updatedItem = subject.todoItemListResult.value?.first()
        assertEquals("testTitleFour", updatedItem?.title)
        assertEquals("testDescriptionFour", updatedItem?.description)
    }

    @Test
    fun deleteItem_success() {
        `when`(roomRepositoryMock.getAllItems()).thenReturn(mockList)
        subject.getAllData()
        subject.deleteItem(mockItemOne)
        val expectedSize = subject.todoItemListResult.value?.size
        assertEquals(1, expectedSize)
    }
}