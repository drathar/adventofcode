package dev.drathar.aoc.util

data class Location(val x: Int, val y: Int)

infix fun Location.move(direction: Direction): Location =
    Location(x + direction.xDelta, y + direction.yDelta)
