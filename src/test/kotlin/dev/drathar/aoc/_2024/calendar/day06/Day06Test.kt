package dev.drathar.aoc._2024.calendar.day06

import dev.drathar.aoc.generators.InputGenerator

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day06Test {
    private val day06: Day06 = Day06(InputGenerator.InputGeneratorFactory())

    @Test
    fun testDay06PartOne() {
        assertEquals(5129, day06.partOne(DAY_06))
    }

    @Test
    fun testDay06PartTwo() {
        assertEquals(1888, day06.partTwo(DAY_06))
    }

    companion object {
        private const val DAY_06: String = "advent-of-code-input/2024/day06.input"
    }
}
