package com.github.nothingelsematters.testing.controller

import com.github.nothingelsematters.testing.dao.ToDoListDao
import com.github.nothingelsematters.testing.model.Task
import com.github.nothingelsematters.testing.model.ToDoList
import io.mockk.impl.annotations.MockK
import io.mockk.MockKAnnotations
import io.mockk.MockKVerificationScope
import io.mockk.every
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ToDoListControllerTest {

    @MockK
    private lateinit var toDoListDao: ToDoListDao

    private lateinit var toDoListController: ToDoListController

    @BeforeTest
    fun setupMock() {
        MockKAnnotations.init(this)

        toDoListController = ToDoListController(toDoListDao)

        every { toDoListDao.add(any()) } answers { ToDoList(1, firstArg()) }
    }

    @Test
    fun `controller's addToDoList calls DAO's add`() {
        val name = "name"
        verify(name, toDoListController.addToDoList(name).name) { toDoListDao.add(name) }
    }

    @Test
    fun `controller getToDoLists calls DAO's getAll`() {
        val list = listOf(ToDoList(1, "name"))
        every { toDoListDao.getAll() } returns list
        verify(list, toDoListController.getToDoLists()) { it.getAll() }
    }

    @Test
    fun `controller deleteToDoList calls DAO's getAll`() {
        val id = 1
        every { toDoListDao.delete(id) } returns true
        verify(true, toDoListController.deleteToDoList(id)) { it.delete(id) }
    }

    @Test
    fun `controller addTask calls DAO's addTask`() {
        val listId = 1
        val taskId = 2
        val description = "description"
        val task = Task(taskId, listId, description)
        every { toDoListDao.addTask(listId, description) } returns task
        verify(task, toDoListController.addTask(listId, description)) { it.addTask(listId, description) }
    }

    @Test
    fun `controller markAsCompleted calls DAO's markCompleted`() {
        val id = 1
        every { toDoListDao.markCompleted(id) } returns true
        verify(true, toDoListController.markAsCompleted(id)) { it.markCompleted(id) }
    }

    private fun <T> verify(
        expected: T,
        actual: T,
        verifyBlock: MockKVerificationScope.(toDoListDao: ToDoListDao) -> Unit
    ) {
        assertEquals(expected, actual)
        verify(exactly = 1) { verifyBlock(toDoListDao) }
    }
}
