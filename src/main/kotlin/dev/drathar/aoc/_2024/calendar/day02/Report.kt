package dev.drathar.aoc._2024.calendar.day02

import kotlin.math.sign

private val ALLOWED_RANGE = 1..3

data class Report(val levels: List<Int>)

fun Report.isSafeDampened(): Boolean =
    isSafe || levels.indices.any { index ->
        levels.toMutableList()
            .apply { removeAt(index) }
            .let(::Report)
            .isSafe
    }

val Report.isSafe: Boolean
    get() = levels.windowed(2)
        .all { (a, b) ->
            when (direction) {
                Direction.INCREASING -> (b - a) in ALLOWED_RANGE
                Direction.DECREASING -> (a - b) in ALLOWED_RANGE
                Direction.NEITHER -> false
            }
        }

val Report.direction: Direction
    get() = when ((levels[0] - levels[1]).sign) {
        -1 -> Direction.INCREASING
        1 -> Direction.DECREASING
        else -> Direction.NEITHER
    }

enum class Direction {
    INCREASING, DECREASING, NEITHER
}