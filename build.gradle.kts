plugins {
    kotlin("jvm") version "2.1.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("AttendanceApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.dropwizard:dropwizard-core:4.0.5")
    implementation("io.dropwizard:dropwizard-jackson:4.0.5") // Add this dependency
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}