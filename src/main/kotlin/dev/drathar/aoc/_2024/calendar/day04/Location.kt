package dev.drathar.aoc._2024.calendar.day04

data class Location(val x: Int, val y: Int)

operator fun Location.times(direction: Direction): Location =
    Location(x + direction.xDelta, y + direction.yDelta)
