package dev.drathar.aoc._2022.calendar.day02

data class RockPaperScissors(val theirChoice: Choice, val myChoice: Choice) {

    fun score(): Int {
        return myChoice.score + when (myChoice) {
            theirChoice.moveToWinAgainst() -> WINNING_SCORE
            theirChoice.moveToTieAgainst() -> TIE_SCORE
            else -> LOSING_SCORE
        }
    }

    companion object {
        private const val WINNING_SCORE = 6
        private const val TIE_SCORE = 3
        private const val LOSING_SCORE = 0
    }
}