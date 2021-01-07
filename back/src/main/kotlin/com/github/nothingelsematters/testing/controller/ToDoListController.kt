package com.github.nothingelsematters.testing.controller

import com.github.nothingelsematters.testing.dao.ToDoListDao
import com.github.nothingelsematters.testing.model.ToDoList
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ToDoListController(private val toDoListDao: ToDoListDao) {

    @GetMapping fun getToDoLists(): List<ToDoList> = toDoListDao.getAll()

    @PostMapping fun addToDoList(@Param("listName") listName: String) = toDoListDao.add(listName)

    @PostMapping("/delete/{list-id}")
    fun deleteToDoList(@PathVariable("list-id") listId: Int): Boolean = toDoListDao.delete(listId)

    @PostMapping("/{list-id}/add")
    fun addTask(@PathVariable("list-id") listId: Int, @RequestBody description: String) =
        toDoListDao.addTask(listId, description)

    @PostMapping("/check/{task-id}")
    fun markAsCompleted(@PathVariable("task-id") taskId: Int) = toDoListDao.markCompleted(taskId)
}
