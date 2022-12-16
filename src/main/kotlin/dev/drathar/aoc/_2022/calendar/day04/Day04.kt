package dev.drathar.aoc._2022.calendar.day04

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory
import javax.inject.Inject


class Day04 @Inject constructor(private val generatorFactory: InputGeneratorFactory) {
    fun partOne(filename: String) = generatorFactory.forFile(filename).readLinesAs(::ranges) { input ->
        input.count { (rangeA, rangeB) ->
            val commonGround = (rangeA fullyIntersect rangeB).takeUnless { it.isEmpty() } ?: return@count false
            rangeA == commonGround || rangeB == commonGround
        }
    }

    fun partTwo(filename: String) = generatorFactory.forFile(filename).readLinesAs(::ranges) { input ->
        input.count { (rangeA, rangeB) ->
            (rangeA intersect rangeB).isNotEmpty()
        }
    }

    private infix fun IntRange.fullyIntersect(other: IntRange): IntRange {
        val intersection = (this intersect other).takeIf { it.isNotEmpty() } ?: return IntRange.EMPTY
        return intersection.min()..intersection.max()
    }

    private fun ranges(line: String) = line.split(",")
        .map { range -> range.split("-") }
        .map { (min, max) -> min.toInt()..max.toInt() }
}
