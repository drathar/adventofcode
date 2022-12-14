package dev.drathar.aoc.generators.skeleton

import dev.drathar.aoc.generators.skeleton.SkeletonGenerator.Companion.SRC_DIRECTORY
import java.nio.file.Files
import java.nio.file.Paths

object Readme {
    fun generateREADME(year: String, day: String) {
        val path = Paths.get("$SRC_DIRECTORY/dev/drathar/aoc/_$year/calendar/day$day/README.md")
        val content = "## [](https://adventofcode.com/$year/day/${day.toInt()})"
        Files.write(path, content.toByteArray())
    }
}
