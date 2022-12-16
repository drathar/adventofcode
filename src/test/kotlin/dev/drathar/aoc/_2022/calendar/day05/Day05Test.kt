package dev.drathar.aoc._2022.calendar.day05


import dev.drathar.aoc._2022.DaggerTestDayComponent
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

internal class Day05Test {
    @Inject
    lateinit var day05: Day05

    @BeforeEach
    fun setup() {
        DaggerTestDayComponent.create().inject(this)
    }

    @Test
    fun testDay05PartOne() {
        assertEquals("VGBBJCRMN", day05.partOne(DAY_05))
    }

    @Test
    fun testDay05PartTwo() {
        assertEquals("LBBVJBRMH", day05.partTwo(DAY_05))
    }

    companion object {
        private const val DAY_05: String = "advent-of-code-input/2022/day05.input"
    }
}
