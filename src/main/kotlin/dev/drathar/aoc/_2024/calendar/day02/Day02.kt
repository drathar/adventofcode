package dev.drathar.aoc._2024.calendar.day02

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory

class Day02(
    private val generatorFactory: InputGeneratorFactory,
) {
    fun partOne(filename: String) =
        generatorFactory.forFile(filename).readLinesAs(::day02) { input ->
            input.count { report -> report.isSafe }
        }

    fun partTwo(filename: String) =
        generatorFactory.forFile(filename).readLinesAs(::day02) { input ->
            input.count { report -> report.isSafeDampened() }
        }

    private fun day02(line: String) =
        line
            .split(" ")
            .map(String::toInt)
            .let(::Report)

}
