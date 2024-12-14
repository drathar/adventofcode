import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
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

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
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