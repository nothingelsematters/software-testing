package com.github.nothingelsematters.testing.model

data class Task(val id: Int, val parentId: Int, val description: String, var completed: Boolean = false)

data class ToDoList(val id: Int, val name: String, val tasks: List<Task> = listOf())
