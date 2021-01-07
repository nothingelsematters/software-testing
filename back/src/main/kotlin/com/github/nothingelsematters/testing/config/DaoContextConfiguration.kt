package com.github.nothingelsematters.testing.config

import com.github.nothingelsematters.testing.dao.ToDoListDao
import com.github.nothingelsematters.testing.dao.ToDoListDatabaseDao
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "database")
class DaoContextConfiguration {

    var url: String? = null
    var username: String? = null
    var password: String? = null

    @Bean fun toDoListDao(): ToDoListDao = ToDoListDatabaseDao(url, username, password)
}
