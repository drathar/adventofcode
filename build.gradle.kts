import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    kotlin("jvm") version "2.0.21"
    application
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
}

dependencies {
    // used for generating skeletons
    implementation("com.squareup:kotlinpoet:1.12.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("SKIPPED", "FAILED", "STANDARD_OUT", "STANDARD_ERROR")
        exceptionFormat = TestExceptionFormat.FULL
    }

    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 3.0 * 2.0).toInt()
}

sourceSets {
    test {
        java {
            setSrcDirs(listOf("build/generated/source/kapt/test"))
        }
    }
}

application {
    mainClass.set("dev.drathar.aoc.Application")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}