package com.mywebsite.todoapp

import com.mywebsite.todoapp.data.RoomRepositoryImpl
import com.mywebsite.todoapp.room.ToDoDao
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import javax.security.auth.Subject

class RoomRepositoryImplTest {

    private lateinit var subject: RoomRepositoryImpl
    private val toDoDaoMock : ToDoDao = mock()

    private val mockItemOne = ToDoItem(0,"testTitleOne", "testDescriptionOne")
    private val mockItemTwo = ToDoItem(1,"testTitleTwo", "testDescriptionTwo")

    private val mockList: List<ToDoItem> = listOf(
        mockItemOne,
        mockItemTwo
    )

    @Before
    fun setup(){
        subject = RoomRepositoryImpl(toDoDaoMock)
    }

    @Test
    fun getAllItems_success(){
        `when`(toDoDaoMock.getAllItems()).thenReturn(mockList)
        val result = subject.getAllItems()
        assertEquals(2, result.size)
    }

    @Test
    fun insertItem_success(){
        subject.insertItem(mockItemOne)
        verify(toDoDaoMock).insertItem(mockItemOne)
    }

    @Test
    fun updateItem_success(){
        subject.updateItem(mockItemOne)
        verify(toDoDaoMock).updateItem(mockItemOne)
    }

    @Test
    fun deleteItem_success(){
        subject.deleteItem(mockItemOne)
        verify(toDoDaoMock).deleteItem(mockItemOne)
    }
}