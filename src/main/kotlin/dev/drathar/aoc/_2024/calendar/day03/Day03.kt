package dev.drathar.aoc._2024.calendar.day03

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory

class Day03(
    private val generatorFactory: InputGeneratorFactory,
) {
    fun partOne(filename: String) = generatorFactory.forFile(filename).readLines { input ->
        input.sumOf { line ->
            val regex = "mul\\((\\d+),(\\d+)\\)".toRegex()
            val matches = regex.findAll(line)
            matches.sumOf { it.getMultiplicationResult() }
        }
    }

    fun partTwo(filename: String) = generatorFactory.forFile(filename).readLines { input ->
        var enabled = true

        input.sumOf { line ->
            val regex = "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don\'t\\(\\)".toRegex()
            val matches = regex.findAll(line)

            matches.fold(0) { acc: Int, match ->
                val matchValue = match.value
                if (matchValue.startsWith("do()")) {
                    enabled = true
                } else if (matchValue.startsWith("don't()")) {
                    enabled = false
                }

                match.takeIf { it.value.startsWith("mul") }
                    ?.takeIf { enabled }
                    ?.getMultiplicationResult()
                    ?.plus(acc)
                    ?: acc
            }
        }
    }

    private fun MatchResult.getMultiplicationResult(): Int =
        groupValues[1].toInt() * groupValues[2].toInt()
}
