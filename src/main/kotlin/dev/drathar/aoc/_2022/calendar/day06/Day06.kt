package dev.drathar.aoc._2022.calendar.day06

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory
import javax.inject.Inject


class Day06 @Inject constructor(private val generatorFactory: InputGeneratorFactory) {
    fun partOne(filename: String) = generatorFactory.forFile(filename).readOneLine { input ->
        findMarker(input, markerSize = 4)
    }

    fun partTwo(filename: String) = generatorFactory.forFile(filename).readOneLine { input ->
        findMarker(input, markerSize = 14)
    }

    private fun findMarker(input: String, markerSize: Int) = input.windowed(markerSize)
        .map(String::toSet)
        .indexOfFirst { chars -> chars.count() == markerSize } + markerSize
}
