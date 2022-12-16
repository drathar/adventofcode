package dev.drathar.aoc._2022.calendar.day05

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory
import javax.inject.Inject

typealias Stacks = Array<ArrayDeque<Char>>

class Day05 @Inject constructor(private val generatorFactory: InputGeneratorFactory) {
    fun partOne(filename: String) = generatorFactory.forFile(filename).readLines { input ->
        handleBoxMovements(input)
    }

    fun partTwo(filename: String) = generatorFactory.forFile(filename).readLines { input ->
        handleBoxMovements(input) { it.asReversed() }
    }

    private fun boxes(line: String) = line.chunked(4)

    private fun handleBoxMovements(input: Sequence<String>, movementModifier: (List<Char>) -> List<Char> = { it }): String {
        val inputList = input.toList()
        val stacksCountRow = inputList.first { it.any(Char::isDigit) }
        val stacksCountRowIndex = inputList.indexOf(stacksCountRow)
        val stacksToCreate = stacksCountRow.filter(Char::isDigit).length
        val stacks = generateStacks(stacksToCreate, inputList.take(stacksCountRowIndex).map(::boxes))

        val moveInstructions = inputList.drop(stacksCountRowIndex + 2)

        for (moveInstruction in moveInstructions) {
            val (quantityToMove, from, to) = decodeInstructions(moveInstruction)
            stacks.moveBoxes(quantityToMove, from - 1, to - 1, movementModifier)
        }

        return stacks.joinToString("") { it.first().toString() }
    }

    private fun generateStacks(stacksToCreate: Int, input: List<List<String>>): Stacks {
        val stacks = Array(stacksToCreate) { ArrayDeque<Char>() }

        for (boxes in input) {
            for ((stackIndex, box) in boxes.withIndex()) {
                box.firstOrNull(Char::isLetter)?.let { boxId ->
                    stacks[stackIndex].addLast(boxId)
                }
            }
        }

        return stacks
    }

    private fun decodeInstructions(moveInstruction: String) = moveInstruction.split(" ")
        .mapNotNull { it.toIntOrNull() }
        .map { it.toString() }
        .map { it.toInt() }
        .toList()
}

private fun Stacks.moveBoxes(quantityToMove: Int, from: Int, to: Int, stackMovementModifier: (List<Char>) -> List<Char>) {
    val boxesToMove = (0 until quantityToMove).map { this[from].removeFirst() }
    for (box in stackMovementModifier(boxesToMove)) {
        this[to].addFirst(box)
    }
}
