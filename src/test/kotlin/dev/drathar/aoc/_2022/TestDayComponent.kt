package dev.drathar.aoc._2022

import dagger.Component
import dev.drathar.aoc.DayComponent
import dev.drathar.aoc.InputModule
import dev.drathar.aoc._2022.calendar.day01.Day01Test
import javax.inject.Singleton


@Singleton
@Component(modules = [InputModule::class])
internal interface TestDayComponent : DayComponent {
    fun inject(day01Test: Day01Test)
}
