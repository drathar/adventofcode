package dev.drathar.aoc._2024.calendar.day04

typealias Area = List<List<Char>>

val Area.width: Int
    get() = first().size

val Area.height: Int
    get() = size

fun Area.findMarkedSpots(target: Char): List<Location> =
    buildList {
        this@findMarkedSpots.mapIndexed { row, line ->
            line.mapIndexed { column, letter ->
                if (letter == target) {
                    add(Location(column, row))
                }
            }
        }
    }

fun Area.findTargetAt(target: String, location: Location, direction: Direction): String =
    target.indices
        .mapNotNull { delta ->
            val directedLocation = location * (direction * delta)
            getValueAtLocation(directedLocation)
        }
        .joinToString("")

operator fun Area.contains(location: Location): Boolean =
    location.y in 0..<height && location.x in 0..<width

fun Area.getValueAtLocation(location: Location): Char? {
    if (location !in this) return null

    return this[location.y][location.x]
}
