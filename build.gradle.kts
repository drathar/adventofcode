import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("kapt") version "1.7.20"
    application
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

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

    // dependency injection library and annotation procressing
    implementation("com.google.dagger:dagger:2.44.2")
    kapt("com.google.dagger:dagger-compiler:2.44.2")
    kaptTest("com.google.dagger:dagger-compiler:2.44.2")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("SKIPPED", "FAILED", "STANDARD_OUT", "STANDARD_ERROR")
        exceptionFormat = TestExceptionFormat.FULL
    }

    finalizedBy(tasks.jacocoTestReport)

    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 3.0 * 2.0).toInt()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        html.required.set(true)
        xml.required.set(true)
    }

    classDirectories.setFrom(files(classDirectories.files.map {
        fileTree(it) {
            exclude("**/skeleton/**", "**/Application*")
        }
    }))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

jacoco {
//    toolVersion = "0.8.7"
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