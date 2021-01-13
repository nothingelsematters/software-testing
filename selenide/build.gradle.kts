plugins {
    id("java")
    kotlin("jvm") version "1.4.21"
    id("io.qameta.allure") version "2.8.1"
}

group = "com.github.nothingelsematters"
version = "0.1.0-SNAPSHOT"

val kotlinVersion = "1.4.21"
val allureVersion = "2.13.8"
val junitVersion = "5.7.0"

repositories { mavenCentral() }

dependencies {
    implementation("org.jetbrains.kotlin", "kotlin-stdlib-jdk8", kotlinVersion)
    implementation("org.seleniumhq.selenium", "selenium-java", "3.141.59")
    implementation("com.codeborne", "selenide", "5.15.1")

    testImplementation("io.qameta.allure", "allure-selenide", allureVersion)
    testImplementation("com.natpryce", "konfig", "1.6.10.0")
    testRuntimeOnly("org.slf4j", "slf4j-simple", "1.7.29")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)
}

tasks {
    test { useJUnitPlatform() }

    allure {
        autoconfigure = true
        version = allureVersion
        useJUnit5 { version = allureVersion }
    }
}
