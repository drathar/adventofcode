package dev.drathar.aoc._2022

import dagger.Component
import dev.drathar.aoc.DayComponent
import dev.drathar.aoc.InputModule
import dev.drathar.aoc._2022.calendar.day01.Day01Test
import dev.drathar.aoc._2022.calendar.day02.Day02Test
import dev.drathar.aoc._2022.calendar.day03.Day03Test
import dev.drathar.aoc._2022.calendar.day04.Day04Test
import dev.drathar.aoc._2022.calendar.day05.Day05Test
import dev.drathar.aoc._2022.calendar.day06.Day06Test
import javax.inject.Singleton


@Singleton
@Component(modules = [InputModule::class])
internal interface TestDayComponent : DayComponent {
    fun inject(day01Test: Day01Test)
    fun inject(day02Test: Day02Test)
    fun inject(day03Test: Day03Test)
    fun inject(day04Test: Day04Test)
    fun inject(day05Test: Day05Test)
    fun inject(day06Test: Day06Test)
}
