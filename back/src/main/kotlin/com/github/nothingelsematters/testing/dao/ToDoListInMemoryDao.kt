package com.github.nothingelsematters.testing.dao

import com.github.nothingelsematters.testing.exception.NotFoundException
import com.github.nothingelsematters.testing.model.Task
import com.github.nothingelsematters.testing.model.ToDoList
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class ToDoListInMemoryDao : ToDoListDao {

    private val lastId = AtomicInteger(0)

    private val toDoLists: MutableMap<Int, ToDoList> = ConcurrentHashMap()

    override fun add(listName: String): ToDoList {
        val id = lastId.incrementAndGet()
        val toDoList = ToDoList(id, listName)
        toDoLists[id] = toDoList
        return toDoList
    }

    override fun getAll(): List<ToDoList> = ArrayList(toDoLists.values)

    override fun getById(id: Int): ToDoList? = toDoLists[id]

    override fun delete(id: Int): Boolean = toDoLists.remove(id) != null

    override fun addTask(listId: Int, description: String): Task {
        val toDoList = toDoLists[listId] ?: throw NotFoundException("List with id {$listId} not found")
        val id = lastId.incrementAndGet()
        val task = Task(id, listId, description)
        toDoLists[listId] = toDoList.copy(tasks = toDoList.tasks + task)
        return task
    }

    override fun markCompleted(taskId: Int): Boolean {
        val task = findTaskById(taskId) ?: throw NotFoundException("Task with id {$taskId}")

        val previousState = task.completed
        task.completed = true
        return !previousState
    }        

    private fun findTaskById(id: Int): Task? =
        toDoLists.asSequence().flatMap { it.value.tasks.asSequence() }.find { it.id == id }
}
