package dev.drathar.aoc._2024.calendar.day04

import dev.drathar.aoc.generators.InputGenerator
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day04Test {
    private val day04: Day04 = Day04(InputGenerator.InputGeneratorFactory())

    @Test
    fun testDay04PartOne() {
        assertEquals(2532, day04.partOne(DAY_04))
    }

    @Test
    fun testDay04PartTwo() {
        assertEquals(1941, day04.partTwo(DAY_04))
    }

    companion object {
        private const val DAY_04: String = "advent-of-code-input/2024/day04.input"
    }
}
