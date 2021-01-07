package com.github.nothingelsematters.testing.dao

import com.github.nothingelsematters.testing.model.Task
import com.github.nothingelsematters.testing.model.ToDoList

interface ToDoListDao {

    fun add(listName: String): ToDoList

    fun getAll(): List<ToDoList>

    fun getById(id: Int): ToDoList?

    fun delete(id: Int): Boolean

    fun addTask(listId: Int, description: String): Task

    fun markCompleted(taskId: Int): Boolean
}
