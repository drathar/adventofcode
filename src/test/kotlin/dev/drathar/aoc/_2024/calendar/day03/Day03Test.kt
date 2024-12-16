package dev.drathar.aoc._2024.calendar.day03

import dev.drathar.aoc.generators.InputGenerator
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day03Test {
    private val day03: Day03 = Day03(InputGenerator.InputGeneratorFactory())

    @Test
    fun testDay03PartOne() {
        assertEquals(178886550, day03.partOne(DAY_03))
    }

    @Test
    fun testDay03PartTwo() {
        assertEquals(87163705, day03.partTwo(DAY_03))
    }

    companion object {
        private const val DAY_03: String = "advent-of-code-input/2024/day03.input"
    }
}
