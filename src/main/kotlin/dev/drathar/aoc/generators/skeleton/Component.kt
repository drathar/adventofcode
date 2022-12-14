package dev.drathar.aoc.generators.skeleton

import com.squareup.kotlinpoet.*
import dev.drathar.aoc.DayComponent
import dev.drathar.aoc.InputModule
import dev.drathar.aoc.generators.skeleton.SkeletonGenerator.Companion.TEST_DIRECTORY
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.inject.Singleton
import dagger.Component as DaggerComponent

object Component {
    fun setupTestComponent(year: String, day: String) {
        val path = Paths.get("$TEST_DIRECTORY/kotlin/dev/drathar/aoc/_$year/TestDayComponent.kt")
        if (Files.exists(path)) {
            alterTestDayComponent(year, day)
        } else {
            createTestDayComponent(year, day)
        }
    }

    private fun alterTestDayComponent(year: String, day: String) {
        val path = Paths.get("$TEST_DIRECTORY/kotlin/dev/drathar/aoc/_$year/TestDayComponent.kt")
        val content = String(Files.readAllBytes(path))
            .replace("}".toRegex(), "  fun inject(day${day}Test: Day${day}Test)\n}")
            .replace("import javax.inject.Singleton".toRegex(), "import dev.drathar.aoc._$year.calendar.day$day.Day${day}Test\nimport javax.inject.Singleton")
        Files.write(path, content.toByteArray())
    }

    private fun createTestDayComponent(year: String, day: String) {
        val file = FileSpec.builder("dev.drathar.aoc._$year", "TestDayComponent")
            .addType(
                TypeSpec.interfaceBuilder("TestDayComponent")
                    .addAnnotation(Singleton::class)
                    .addAnnotation(
                        AnnotationSpec.builder(DaggerComponent::class)
                            .addMember("modules = [%T::class]", InputModule::class)
                            .build())
                    .addModifiers(KModifier.INTERNAL)
                    .addSuperinterface(DayComponent::class)
                    .addFunction(
                        FunSpec.builder("inject")
                            .addModifiers(KModifier.ABSTRACT)
                            .addParameter("day${day}Test", ClassName("dev.drathar.aoc._$year.calendar.day$day", "Day${day}Test"))
                            .build()
                    )
                    .build()
            )
            .build()

        file.writeTo(File("$TEST_DIRECTORY/kotlin"))

        // remove all the extraneous `public` modifiers
        // and extraeous `Unit`
        val path = Paths.get("${TEST_DIRECTORY}/kotlin/dev/drathar/aoc/_$year/TestDayComponent.kt")
        val content = String(Files.readAllBytes(path))
            .replace("public ".toRegex(), "")
            .replace("import kotlin.Unit".toRegex(), "")
            .replace(": Unit".toRegex(), "")
        Files.write(path, content.toByteArray())
    }
}
