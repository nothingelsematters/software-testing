package com.github.nothingelsematters.testing.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.nothingelsematters.testing.model.Task
import com.github.nothingelsematters.testing.model.ToDoList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test
import kotlin.test.assertEquals

internal class KPostgreSQLContainer : PostgreSQLContainer<KPostgreSQLContainer>("postgres")

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
internal class ComponentToDoListControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val mapper = jacksonObjectMapper()

    companion object {
        @Container
        private val container = KPostgreSQLContainer().withDatabaseName("database")

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("database.url") { container.jdbcUrl }
            registry.add("database.username") { container.username }
            registry.add("database.password") { container.password }
        }
    }

    @Test
    fun `simple component container test`() {
        val firstListName = "name"
        val secondListName = "name2"
        val thirdListName = "name3"
        val taskDescription = "some description"

        mockMvc.perform(post("/api/v1?listName=$firstListName")).andExpect(status().isOk())
        mockMvc.perform(post("/api/v1?listName=$secondListName")).andExpect(status().isOk())
        mockMvc.perform(post("/api/v1?listName=$thirdListName")).andExpect(status().isOk())
        mockMvc.perform(post("/api/v1/delete/2")).andExpect(status().isOk())
        mockMvc.perform(post("/api/v1/3/add").content("$taskDescription")).andExpect(status().isOk())

        val expectedJson = mapper.writeValueAsString(
            listOf(
                ToDoList(1, firstListName),
                ToDoList(3, thirdListName, listOf(Task(1, 3, taskDescription)))
            )
        )

        mockMvc.perform(get("/api/v1").accept(MediaType.APPLICATION_JSON)).andExpect(content().string(expectedJson))
    }
}
