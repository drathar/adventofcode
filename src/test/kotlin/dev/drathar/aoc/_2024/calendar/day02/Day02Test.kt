package dev.drathar.aoc._2024.calendar.day02


import dev.drathar.aoc.generators.InputGenerator
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day02Test {
    private val day02: Day02 = Day02(InputGenerator.InputGeneratorFactory())


    @Test
    fun testDay02PartOne() {
        assertEquals(663, day02.partOne(DAY_02))
    }

    @Test
    fun testDay02PartTwo() {
        assertEquals(692, day02.partTwo(DAY_02))
    }

    companion object {
        private const val DAY_02: String = "advent-of-code-input/2024/day02.input"
    }
}
