import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
    application
}
group = "me.gabom"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    val jUnitVersion = "5.3.1"

    testImplementation(kotlin("test-junit5"))
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", jUnitVersion)

    // IDEA needs those:
    testCompileOnly("org.junit.jupiter", "junit-jupiter-api", jUnitVersion)
    testCompileOnly("org.junit.jupiter", "junit-jupiter-params", jUnitVersion)
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "MainKt"
}

tasks.test {
    useJUnitPlatform()
}