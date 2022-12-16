package dev.drathar.aoc._2022.calendar.day02

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory
import javax.inject.Inject


class Day02 @Inject constructor(private val generatorFactory: InputGeneratorFactory) {
    fun partOne(filename: String) = generatorFactory.forFile(filename).readLinesAs(::codedRound) { input ->
        input.sumOf { (them, me) ->
            val theirChoice = theirChoice(them)
            val myChoice = myChoice(me)

            RockPaperScissors(theirChoice, myChoice).score()
        }
    }

    fun partTwo(filename: String) = generatorFactory.forFile(filename).readLinesAs(::codedRound) { input ->
        input.sumOf { (them, myTactic) ->
            val theirChoice = theirChoice(them)
            val myChoice = myTacticalChoice(theirChoice, myTactic)

            RockPaperScissors(theirChoice, myChoice).score()
        }
    }

    private fun theirChoice(choice: String): Choice = when (choice) {
        THEIR_ROCK -> Choice.ROCK
        THEIR_PAPER -> Choice.PAPER
        THEIR_SCISSORS -> Choice.SCISSORS
        else -> error("Unknown Choice for them [$choice]")
    }

    private fun myChoice(choice: String): Choice = when (choice) {
        MY_ROCK -> Choice.ROCK
        MY_PAPER -> Choice.PAPER
        MY_SCISSORS -> Choice.SCISSORS
        else -> error("Unknown Choice for me [$choice]")
    }

    private fun myTacticalChoice(theirChoice: Choice, tactic: String): Choice = when (tactic) {
        LOSE -> theirChoice.moveToLoseAgainst()
        DRAW -> theirChoice.moveToTieAgainst()
        WIN -> theirChoice.moveToWinAgainst()
        else -> error("Unknown Choice for me [$tactic]")
    }

    private fun codedRound(line: String) = line.split(" ")

    companion object {
        private const val THEIR_ROCK = "A"
        private const val THEIR_PAPER = "B"
        private const val THEIR_SCISSORS = "C"

        private const val MY_ROCK = "X"
        private const val MY_PAPER = "Y"
        private const val MY_SCISSORS = "Z"

        private const val LOSE = "X"
        private const val DRAW = "Y"
        private const val WIN = "Z"
    }
}
