package com.github.nothingelsematters.testing.selenide

import com.codeborne.selenide.Condition
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginPageTest : AbstractPageTest("login") {

    @Test fun wrongUserLoginAttempt() = testLogin("userr", "password", "Bad Request")

    @Test fun wrongPasswordLoginAttempt() = testLogin("user", "passsss", "Unauthorized")

    @Test fun successLoginAttempt() = testLogin("user", "password", "OK")

    private fun testLogin(user: String, password: String, result: String) {
        get(id("login")).value = user
        get(id("password")).setValue(password).pressEnter()
        get(className("result")).shouldBe(Condition.text(result))
    }
}
