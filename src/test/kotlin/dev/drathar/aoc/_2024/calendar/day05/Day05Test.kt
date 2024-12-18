package dev.drathar.aoc._2024.calendar.day05

import dev.drathar.aoc.generators.InputGenerator

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day05Test {
    private val day05: Day05 = Day05(InputGenerator.InputGeneratorFactory())

    @Test
    fun testDay05PartOne() {
        assertEquals(5129, day05.partOne(DAY_05))
    }

    @Test
    fun testDay05PartTwo() {
        assertEquals(4077, day05.partTwo(DAY_05))
    }

    companion object {
        private const val DAY_05: String = "advent-of-code-input/2024/day05.input"
    }
}
