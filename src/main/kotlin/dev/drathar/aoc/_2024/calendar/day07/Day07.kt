package dev.drathar.aoc._2024.calendar.day07

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory


class Day07(
    private val generatorFactory: InputGeneratorFactory,
) {
    fun partOne(filename: String) = generatorFactory.forFile(filename).readLinesAs(::calibration) { input ->
        val operations = listOf(ArithmeticOperation.Addition, ArithmeticOperation.Multiplication)

        input.filter { calibration -> calibration.check(operations) }
            .sumOf { it.testValue }
    }

    fun partTwo(filename: String) = generatorFactory.forFile(filename).readLinesAs(::calibration) { input ->
        val operations =
            listOf(ArithmeticOperation.Addition, ArithmeticOperation.Multiplication, ArithmeticOperation.Concatenation)

        input.filter { calibration -> calibration.check(operations) }
            .sumOf { it.testValue }
    }

}

private fun calibration(line: String) =
    line.split(": ")
        .let { (testValue, numbers) ->
            Calibration(testValue.toLong(), numbers.split(" ").map(String::toLong))
        }

private data class Calibration(val testValue: Long, val numbers: List<Long>)

private fun Calibration.check(operations: List<ArithmeticOperation>): Boolean = when {
    numbers.isEmpty() -> false
    numbers.count() == 1 -> testValue == numbers.first()
    numbers.first() > testValue -> false
    else -> {
        val (n, m) = numbers.take(2)
        val otherNumbers = numbers.drop(2)

        operations.any { op ->
            val newCalibration = Calibration(testValue, listOf(op(n, m)) + otherNumbers)
            newCalibration.check(operations)
        }
    }
}

private sealed interface ArithmeticOperation {
    operator fun invoke(firstOperand: Long, secondOperand: Long): Long

    data object Addition : ArithmeticOperation {
        override fun invoke(firstOperand: Long, secondOperand: Long): Long = firstOperand + secondOperand
    }

    data object Multiplication : ArithmeticOperation {
        override fun invoke(firstOperand: Long, secondOperand: Long): Long = firstOperand * secondOperand
    }

    data object Concatenation : ArithmeticOperation {
        override fun invoke(firstOperand: Long, secondOperand: Long): Long = "$firstOperand$secondOperand".toLong()
    }
}
