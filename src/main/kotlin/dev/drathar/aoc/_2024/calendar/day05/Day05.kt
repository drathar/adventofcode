package dev.drathar.aoc._2024.calendar.day05

import dev.drathar.aoc.generators.InputGenerator.InputGeneratorFactory


class Day05(
    private val generatorFactory: InputGeneratorFactory,
) {
    fun partOne(filename: String): Int = generatorFactory.forFile(filename).readLines { input ->
        val (rules, updates) = input.parse()
        val (validUpdates) = validate(rules, updates)

        sumMiddlePages(validUpdates)
    }

    fun partTwo(filename: String): Int = generatorFactory.forFile(filename).readLines { input ->
        val (rules, updates) = input.parse()
        val (_, invalidUpdates) = validate(rules, updates)

        sumMiddlePages(invalidUpdates.map { it.sorted(rules) })
    }

}

private fun sumMiddlePages(updates: List<Update>) = updates.sumOf { it.pages[it.size / 2] }

private fun validate(rules: Set<Rule>, updates: Set<Update>): Pair<List<Update>, List<Update>> =
    updates.partition { update ->
        update.pages.windowed(2).all { (before, after) ->
            Rule(before, after) in rules
        }
    }

private fun Sequence<String>.parse(): Pair<Set<Rule>, Set<Update>> {
    val rules = mutableSetOf<Rule>()
    val updates = mutableSetOf<Update>()

    var readingRules = true
    forEach { line ->
        if (line.isBlank()) {
            readingRules = false
        } else {
            if (readingRules) {
                val rule = line.split("|")
                    .map(String::toInt)
                    .let { (b, a) -> Rule(b, a) }
                rules.add(rule)
            } else {
                val pages = line.split(",")
                    .map(String::toInt)
                val update = Update(pages)
                updates.add(update)
            }
        }
    }

    return rules.toSet() to updates.toSet()
}

data class Rule(val before: Int, val after: Int)

data class Update(val pages: List<Int>)

private val Update.size: Int
    get() = pages.size

private fun Update.sorted(rules: Set<Rule>): Update {
    val sortedPages = pages.sortedWith { before, after ->
        if (Rule(before, after) in rules) {
            -1
        } else {
            1
        }
    }
    return Update(sortedPages)
}
