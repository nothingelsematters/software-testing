package com.github.nothingelsematters.testing.selenide

import com.codeborne.selenide.CollectionCondition
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.openqa.selenium.By.name

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListsPageTest : AbstractPageTest("lists") {

    @Test fun listManipulations() {
        val listName = "listName"
        val taskDescription = "description"

        get(id("new-list")).setValue(listName).pressEnter()
        get(name("new-task")).setValue(taskDescription).pressEnter()
        get(className("check")).pressEnter()
        all(className("task"))
            .shouldHaveSize(1)
            .shouldBe(CollectionCondition.allMatch("everything's checked") { """✔️""" in it.text })
        get(className("delete")).pressEnter()
        all(className("list")).shouldHaveSize(0)
    }

    @AfterEach fun deleteLists() {
        all(className("delete")).forEach { it.pressEnter() }
    }
}
