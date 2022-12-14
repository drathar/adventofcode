package dev.drathar.aoc.generators.skeleton

import com.squareup.kotlinpoet.*
import dev.drathar.aoc.generators.InputGenerator
import dev.drathar.aoc.generators.skeleton.SkeletonGenerator.Companion.SRC_DIRECTORY
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.inject.Inject

object DayClass {
    private val INPUT_GENERATOR_FACTORY_PACKAGE = InputGenerator::class.java.let { "${it.packageName}.${it.simpleName}" }
    private val INPUT_GENERATOR_FACTORY_NAME = InputGenerator.InputGeneratorFactory::class.java.simpleName

    fun generateDayClass(year: String, day: String) {
        val factoryClassName = ClassName(INPUT_GENERATOR_FACTORY_PACKAGE, INPUT_GENERATOR_FACTORY_NAME)
        val file = FileSpec.builder("dev.drathar.aoc._$year.calendar.day$day", "Day$day")
            .addImport(
                INPUT_GENERATOR_FACTORY_PACKAGE,
                INPUT_GENERATOR_FACTORY_NAME
            )
            .addType(
                TypeSpec.classBuilder("Day$day")
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addAnnotation(Inject::class)
                            .addParameter("generatorFactory", factoryClassName)
                            .build()
                    ).addProperty(
                        PropertySpec.builder("generatorFactory", factoryClassName)
                            .initializer("generatorFactory")
                            .addModifiers(KModifier.PRIVATE)
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("partOne")
                            .addParameter("filename", String::class)
                            .addModifiers()
                            .addStatement("return·generatorFactory.forFile(filename).readLinesAs(::day$day)·{·input·->\n  -1\n}")
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("partTwo")
                            .addParameter("filename", String::class)
                            .addStatement("return·generatorFactory.forFile(filename).readLinesAs(::day$day)·{·input·->\n  -1\n}")
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("day$day")
                            .addModifiers(KModifier.PRIVATE)
                            .addParameter("line", String::class)
                            .addStatement("return 4")
                            .build()
                    )
                    .build()
            )
            .build()

        file.writeTo(File(SRC_DIRECTORY))

        // remove all the extraneous `public` modifiers
        // and the safety import of `kotlin.String`
        val path = Paths.get("$SRC_DIRECTORY/dev/drathar/aoc/_$year/calendar/day$day/Day$day.kt")
        val content = String(Files.readAllBytes(path))
            .replace("public ".toRegex(), "")
            .replace("import kotlin.String".toRegex(), "")
        Files.write(path, content.toByteArray())
    }
}
