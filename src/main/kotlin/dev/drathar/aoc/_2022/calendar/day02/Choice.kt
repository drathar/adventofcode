package dev.drathar.aoc._2022.calendar.day02

enum class Choice(val score: Int) {
    ROCK(1), PAPER(2), SCISSORS(3)
}

fun Choice.moveToWinAgainst(): Choice = when (this) {
    Choice.ROCK -> Choice.PAPER
    Choice.PAPER -> Choice.SCISSORS
    Choice.SCISSORS -> Choice.ROCK
}

fun Choice.moveToLoseAgainst(): Choice = when (this) {
    Choice.ROCK -> Choice.SCISSORS
    Choice.PAPER -> Choice.ROCK
    Choice.SCISSORS -> Choice.PAPER
}

fun Choice.moveToTieAgainst(): Choice = this
