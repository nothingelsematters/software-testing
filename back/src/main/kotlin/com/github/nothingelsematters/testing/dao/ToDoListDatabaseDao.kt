package com.github.nothingelsematters.testing.dao

import com.github.nothingelsematters.testing.exception.NotFoundException
import com.github.nothingelsematters.testing.model.Task
import com.github.nothingelsematters.testing.model.ToDoList
import mu.KLogging
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class ToDoListDatabaseDao(url: String?, username: String?, password: String?) : ToDoListDao {

    init {
        logger.info("Connection to $url with $username/$password")
        Database.connect(
            url ?: throw RuntimeException("Null Database URL"),
            user = username ?: "",
            password = password ?: ""
        )
        transaction { SchemaUtils.create(ToDoLists, Tasks) }
    }

    override fun add(listName: String): ToDoList = transaction { ToDoListEntity.new { name = listName } }.toModel()

    override fun getAll(): List<ToDoList> = transaction { ToDoListEntity.all().map { it.toModel() } }

    override fun getById(id: Int): ToDoList? = getRowById(id)?.toModel()

    override fun delete(id: Int): Boolean = transaction {
        val row = getRowById(id) ?: return@transaction false
        row.delete()
        true
    }

    override fun addTask(listId: Int, description: String): Task =
        transaction {
            TaskEntity.new {
                parent = getRowById(listId) ?: throw NotFoundException("List with id {$listId} not found")
                this.description = description
                completed = false
            }
        }
        .toModel()

    override fun markCompleted(taskId: Int): Boolean = transaction {
        val taskEntity = TaskEntity.findById(taskId) ?: throw NotFoundException("Task with id {$taskId}")

        val previousState = taskEntity.completed
        taskEntity.completed = true
        !previousState
    }

    private fun getRowById(id: Int): ToDoListEntity? = transaction { ToDoListEntity.findById(id) }

    companion object : KLogging()
}

private object ToDoLists : IntIdTable() {

    val name = varchar("description", 50)
}

private object Tasks : IntIdTable() {

    val parentId = reference("parent_id", ToDoLists)
    val description = text("description")
    val completed = bool("completed")
}

class ToDoListEntity(id: EntityID<Int>) : IntEntity(id) {

    var name by ToDoLists.name
    val tasks by TaskEntity referrersOn Tasks.parentId

    fun toModel(): ToDoList = transaction { ToDoList(this@ToDoListEntity.id.value, name, tasks.map { it.toModel() }) }

    companion object : IntEntityClass<ToDoListEntity>(ToDoLists)
}

class TaskEntity(id: EntityID<Int>) : IntEntity(id) {

    var parent by ToDoListEntity referencedOn Tasks.parentId
    var description by Tasks.description
    var completed by Tasks.completed

    fun toModel(): Task = transaction { Task(this@TaskEntity.id.value, parent.id.value, description, completed) }

    companion object : IntEntityClass<TaskEntity>(Tasks)
}
