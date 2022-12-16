package dev.drathar.aoc._2022.calendar.day01

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory
import javax.inject.Inject


class Day01 @Inject constructor(private val generatorFactory: InputGeneratorFactory) {
    fun partOne(filename: String) = generatorFactory.forFile(filename).readBlockAs(converter = ::calorieCount) { input ->
        input.maxOf { it.sum() }
    }

    fun partTwo(filename: String) = generatorFactory.forFile(filename).readBlockAs(converter = ::calorieCount) { input ->
        input.map { it.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    }

    private fun calorieCount(line: String) = line.toInt()
}
