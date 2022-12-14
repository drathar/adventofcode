package dev.drathar.aoc

import dev.drathar.aoc.generators.skeleton.SkeletonGenerator

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val year: String = args.elementAtOrNull(0) ?: println("Enter year: e.g. 2015, 2021, 2017, etc...: ").run { readln() }

            val day: String = args.elementAtOrNull(1) ?: println("Enter day: e.g. 09, 23, 17, etc...: ").run { readln() }

            var confirmed: String? = null
            if (args.elementAtOrNull(2) == "--force") {
                confirmed = "yes"
            } else {
                while (confirmed != "yes" && confirmed != "no") {
                    confirmed = println("Confirm generation of new Advent Of Code Skeleton for $year/$day? (yes/no)").run { readln() }
                }
            }

            if (confirmed == "yes") {
                SkeletonGenerator(year, day).generateAdventSkeleton()
            } else {
                println("Not creating skeleton.")
            }
        }
    }
}