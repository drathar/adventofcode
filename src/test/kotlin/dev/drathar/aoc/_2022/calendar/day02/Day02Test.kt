package dev.drathar.aoc._2022.calendar.day02


import dev.drathar.aoc._2022.DaggerTestDayComponent
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

internal class Day02Test {
    @Inject
    lateinit var day02: Day02

    @BeforeEach
    fun setup() {
        DaggerTestDayComponent.create().inject(this)
    }

    @Test
    fun testDay02PartOne() {
        assertEquals(11475, day02.partOne(DAY_02))
    }

    @Test
    fun testDay02PartTwo() {
        assertEquals(16862, day02.partTwo(DAY_02))
    }

    companion object {
        private const val DAY_02: String = "advent-of-code-input/2022/day02.input"
    }
}
