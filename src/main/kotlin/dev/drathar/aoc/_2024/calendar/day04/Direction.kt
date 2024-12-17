package dev.drathar.aoc._2024.calendar.day04

sealed class Direction(open val xDelta: Int, open val yDelta: Int) {
    data object North : Direction(0, -1)
    data object NorthEast : Direction(1, -1)
    data object East : Direction(1, 0)
    data object SouthEast : Direction(1, 1)
    data object South : Direction(0, 1)
    data object SouthWest : Direction(-1, 1)
    data object West : Direction(-1, 0)
    data object NorthWest : Direction(-1, -1)
    data class Custom(override val xDelta: Int, override val yDelta: Int) : Direction(xDelta, yDelta)
}

operator fun Direction.times(delta: Int): Direction =
    Direction.Custom(delta * xDelta, delta * yDelta)
