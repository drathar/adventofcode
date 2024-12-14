package dev.drathar.aoc._2024.calendar.day01


import dev.drathar.aoc.generators.InputGenerator
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day01Test {
    private val day01: Day01 = Day01(InputGenerator.InputGeneratorFactory())

    @Test
    fun testDay01PartOne() {
        assertEquals(1530215, day01.partOne(DAY_01))
    }

    @Test
    fun testDay01PartTwo() {
        assertEquals(26800609, day01.partTwo(DAY_01))
    }

    companion object {
        private const val DAY_01: String = "advent-of-code-input/2024/day01.input"
    }
}
