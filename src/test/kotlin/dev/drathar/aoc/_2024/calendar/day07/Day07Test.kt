package dev.drathar.aoc._2024.calendar.day07

import dev.drathar.aoc.generators.InputGenerator

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day07Test {
    private val day07: Day07 = Day07(InputGenerator.InputGeneratorFactory())

    @Test
    fun testDay07PartOne() {
        assertEquals(1430271835320, day07.partOne(DAY_07))
    }

    @Test
    fun testDay07PartTwo() {
        assertEquals(456565678667482, day07.partTwo(DAY_07))
    }

    companion object {
        private const val DAY_07: String = "advent-of-code-input/2024/day07.input"
    }
}
