package dev.drathar.aoc.util

data class Location(val x: Int, val y: Int)

operator fun Location.times(direction: Direction): Location =
    Location(x + direction.xDelta, y + direction.yDelta)
