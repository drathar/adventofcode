package dev.drathar.aoc._2022.calendar.day03

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory
import javax.inject.Inject

typealias Rucksack = List<Set<Char>>

class Day03 @Inject constructor(private val generatorFactory: InputGeneratorFactory) {
    fun partOne(filename: String) = generatorFactory.forFile(filename).readLinesAs(::items) { items ->
        items.map { it.chunked(it.size / 2, List<Char>::toSet) }
            .map(::singleCommonItem)
            .sumOf { it.priority() }
    }

    fun partTwo(filename: String) = generatorFactory.forFile(filename).readLinesAs(::items) { items ->
        items.chunked(3) { lists -> lists.map(List<Char>::toSet) }
            .map(::singleCommonItem)
            .sumOf { it.priority() }
    }

    private fun singleCommonItem(rucksack: Rucksack) =
        rucksack.reduce { largeCompartment, smallCompartment ->
            largeCompartment.intersect(smallCompartment)
        }.single()

    private fun Char.priority() = when (this) {
        in 'a'..'z' -> this - ('a' - 1)
        in 'A'..'Z' -> this - ('A' - 27)
        else -> 0
    }

    private fun items(line: String) = line.toList()
}
