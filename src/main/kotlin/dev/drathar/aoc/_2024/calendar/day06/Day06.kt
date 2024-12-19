package dev.drathar.aoc._2024.calendar.day06

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory
import dev.drathar.aoc.util.*

class Day06(
    private val generatorFactory: InputGeneratorFactory,
) {
    fun partOne(filename: String): Int = generatorFactory.forFile(filename).readLines { input ->
        val (area, guard) = setup(input)

        walk(area, guard).size
    }

    fun partTwo(filename: String): Int = generatorFactory.forFile(filename).readLines { input ->
        val (area, guardStart) = setup(input)

        val guard = guardStart.copy()
        val steps = walk(area, guard)

        steps.count { location ->
            if (guardStart.location == location) {
                false
            } else {
                var stepCount = 0

                walk(area.alter(location, Space.BLOCKED), guard, totalStepHandler = { stepCount = it })

                stepCount == MAX_STEPS
            }
        }
    }
}

private fun setup(lines: Sequence<String>): Pair<SpaceArea, Guard> {
    val area = mutableListOf<List<Space>>()
    var guard = Guard(Direction.North, Location(-1, -1))

    lines.forEachIndexed { yIndex, line ->
        val row = mutableListOf<Space>()
        line.forEachIndexed { xIndex, char ->
            when (char) {
                '.' -> row.add(Space.EMPTY)
                '#' -> row.add(Space.BLOCKED)
                '^' -> {
                    row.add(Space.EMPTY)
                    guard = Guard(Direction.North, Location(xIndex, yIndex))
                }
            }
        }
        area.add(row)
    }

    return area to guard
}

private const val MAX_STEPS = 10_000

private fun walk(
    area: SpaceArea,
    guardAtStart: Guard,
    totalStepHandler: (Int) -> Unit = { },
): Set<Location> {
    var guard = guardAtStart.copy()

    var stepCount = 0

    val steps = buildSet {
        while (stepCount < MAX_STEPS && area.contains(guard.location)) {
            val nextLocation = guard.stepForward().location

            add(guard.location)

            guard = if (area.contains(nextLocation) && area.getValueAtLocation(nextLocation) == Space.BLOCKED) {
                guard.turnRight()
            } else {
                guard.stepForward()
            }

            stepCount++
        }
    }

    totalStepHandler(stepCount)

    return steps
}

private typealias SpaceArea = Area<Space>

private data class Guard(val direction: Direction, val location: Location)

private fun Guard.stepForward(): Guard = copy(location = location move direction)

private fun Guard.turnRight(): Guard = copy(direction = direction.turnRight())

private fun Direction.turnRight(): Direction = when (this) {
    Direction.North -> Direction.East
    Direction.East -> Direction.South
    Direction.South -> Direction.West
    Direction.West -> Direction.North
    else -> error("Unknown direction")
}

private enum class Space { EMPTY, BLOCKED }