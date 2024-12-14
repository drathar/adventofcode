package dev.drathar.aoc._2024.calendar.day01

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory
import kotlin.math.abs


class Day01(
    private val generatorFactory: InputGeneratorFactory,
) {
    fun partOne(filename: String): Long =
        generatorFactory.forFile(filename).readLinesAs(::locationLists) { input ->
            val firstList = mutableListOf<Long>()
            val secondList = mutableListOf<Long>()

            input.forEach { (first, second) ->
                firstList.add(first)
                secondList.add(second)
            }

            firstList.sort()
            secondList.sort()

            firstList.zip(secondList)
                .sumOf { (first, second) -> abs(first - second) }
        }

    fun partTwo(filename: String) =
        generatorFactory.forFile(filename).readLinesAs(::locationLists) { input ->
            val firstList = mutableListOf<Long>()
            val secondListOccurrences = mutableMapOf<Long, Long>()

            input.forEach { (first, second) ->
                firstList.add(first)
                secondListOccurrences.merge(second, 1) { previous, one -> previous + one }
            }

            firstList.sumOf { item -> secondListOccurrences.getOrDefault(item, 0) * item }
        }

    private fun locationLists(line: String) =
        line
            .split("   ")
            .let { (first, second) -> first.toLong() to second.toLong() }
}
