package com.github.nothingelsematters.testing.selenide

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.By

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractPageTest(private val relativePath: String) {

    @BeforeAll fun setUp() {
        Configuration.headless = true
        if (TestConfiguration.SELENOID_URL.isNotEmpty()) {
            Configuration.remote = TestConfiguration.SELENOID_URL
            Configuration.driverManagerEnabled = false
        }
        open((TestConfiguration.URL + relativePath).also { println("Testing $it") })
    }

    protected fun get(selector: By) = `$`(selector)

    protected fun all(selector: By) = `$$`(selector)
}
