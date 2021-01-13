package com.github.nothingelsematters.testing.selenide

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.Key
import com.natpryce.konfig.PropertyLocation
import com.natpryce.konfig.overriding
import com.natpryce.konfig.stringType
import kotlin.reflect.KProperty

object TestConfiguration {

    private val configuration =
        EnvironmentVariables() overriding ConfigurationProperties.fromResource("properties.properties")

    val URL by Configured("url", stringType)

    private class Configured<C, T>(private val name: String, private val parse: (PropertyLocation, String) -> T) {
        operator fun getValue(thisRef: C, property: KProperty<*>): T = configuration[Key(name, parse)]
    }
}
