import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.4.21"
    val springVersion = "2.4.1"
    val springDependencyManagementVestion = "1.0.10.RELEASE"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version "1.4.30-M1"

    id("org.springframework.boot") version springVersion
    id("io.spring.dependency-management") version springDependencyManagementVestion

    id("io.qameta.allure") version "2.5"
}

group = "com.github.nothingelsematters"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    val kotlinVersion = "1.4.21"
    val springBootVersion = "2.4.1"
    val exposedVersion = "0.28.1"
    val junitVersion = "5.2.0"
    val testContainersVersion = "1.15.1"

    implementation("org.jetbrains.kotlin", "kotlin-stdlib-jdk8", kotlinVersion)
    implementation("org.jetbrains.kotlin", "kotlin-reflect", kotlinVersion)

    implementation("io.github.microutils", "kotlin-logging", "2.0.4")

    implementation("org.springframework.security", "spring-security-config", "5.4.2")
    implementation("org.springframework.security", "spring-security-web", "5.4.2")
    implementation("org.springframework.boot", "spring-boot-starter", springBootVersion)
    implementation("org.springframework.boot", "spring-boot-starter-data-rest", springBootVersion)
    implementation("org.springframework.boot", "spring-boot-starter-web", springBootVersion)
    implementation("org.springframework.boot", "spring-boot-starter-webflux", springBootVersion)
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.12.0")
    kapt("org.springframework.boot", "spring-boot-configuration-processor", springBootVersion)

    implementation("org.jetbrains.exposed", "exposed-core", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-dao", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)

    runtimeOnly("org.springframework.boot", "spring-boot-devtools", springBootVersion)
    runtimeOnly("org.postgresql", "postgresql", "42.2.2")

    testImplementation("org.jetbrains.kotlin", "kotlin-test-junit5", kotlinVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)
    testRuntimeOnly("io.qameta.allure", "allure-junit5", "2.13.8")

    testImplementation("org.springframework.boot", "spring-boot-starter-test", springBootVersion)
    testImplementation("org.testcontainers", "postgresql", testContainersVersion)
    testImplementation("org.testcontainers", "junit-jupiter", testContainersVersion)
    testImplementation("io.mockk", "mockk", "1.10.4")
}

kapt {
    correctErrorTypes = true
}

tasks {
    allure {
        autoconfigure = true
        version = "2.13.8"
        downloadLinkFormat = "https://dl.bintray.com/qameta/maven/io/qameta/allure/allure-commandline/%s/allure-commandline-%<s.zip"
    }
}

tasks.withType<KotlinCompile> {
    println("Configuring KotlinCompile  $name in project ${project.name}...")
    kotlinOptions {
        languageVersion = "1.4"
        apiVersion = "1.4"
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
