package dev.drathar.aoc.util

typealias CharArea = Area<Char>

fun CharArea.findMarkedSpots(target: Char): List<Location> =
    buildList {
        this@findMarkedSpots.mapIndexed { row, line ->
            line.mapIndexed { column, letter ->
                if (letter == target) {
                    add(Location(column, row))
                }
            }
        }
    }

fun CharArea.findTargetAt(target: String, location: Location, direction: Direction): String =
    target.indices
        .mapNotNull { delta ->
            val directedLocation = location move (direction * delta)
            getValueAtLocation(directedLocation)
        }
        .joinToString("")