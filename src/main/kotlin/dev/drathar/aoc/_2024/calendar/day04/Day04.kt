package dev.drathar.aoc._2024.calendar.day04

import dev.drathar.aoc._2024.calendar.day04.Direction.*
import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory

class Day04(
    private val generatorFactory: InputGeneratorFactory,
) {
    fun partOne(filename: String) = generatorFactory.forFile(filename).readLines { input ->
        val area = input.map { it.toList() }.toList()

        val directions = listOf(
            North,
            NorthEast,
            East,
            SouthEast,
            South,
            SouthWest,
            West,
            NorthWest,
        )

        val keyword = "XMAS"

        area.findMarkedSpots('X')
            .sumOf { location ->
                directions.map { direction -> area.findTargetAt(keyword, location, direction) }
                    .count { it == keyword }
            }
    }

    fun partTwo(filename: String) = generatorFactory.forFile(filename).readLines { input ->
        val area = input.map { it.toList() }.toList()

        val slashDirections = listOf(
            NorthEast,
            SouthWest,
        )
        val backslashDirections = listOf(
            SouthEast,
            NorthWest,
        )

        val targetChars = setOf('M', 'S')

        area.findMarkedSpots('A')
            .count { location ->
                val slashMatches = slashDirections.mapNotNull { direction ->
                    area.getValueAtLocation(location * direction)
                }.toSet() == targetChars

                val backslashMatches = backslashDirections.mapNotNull { direction ->
                    area.getValueAtLocation(location * direction)
                }.toSet() == targetChars

                slashMatches && backslashMatches
            }
    }

}
