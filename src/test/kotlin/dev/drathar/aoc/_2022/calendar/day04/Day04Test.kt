package dev.drathar.aoc._2022.calendar.day04


import dev.drathar.aoc._2022.DaggerTestDayComponent
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

internal class Day04Test {
    @Inject
    lateinit var day04: Day04

    @BeforeEach
    fun setup() {
        DaggerTestDayComponent.create().inject(this)
    }

    @Test
    fun testDay04PartOne() {
        assertEquals(441, day04.partOne(DAY_04))
    }

    @Test
    fun testDay04PartTwo() {
        assertEquals(861, day04.partTwo(DAY_04))
    }

    companion object {
        private const val DAY_04: String = "advent-of-code-input/2022/day04.input"
    }
}
