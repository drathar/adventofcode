package dev.drathar.aoc._2022

import dagger.Component
import dev.drathar.aoc.DayComponent
import dev.drathar.aoc.InputModule
import dev.drathar.aoc._2022.calendar.day04.Day04Test
import javax.inject.Singleton


@Singleton
@Component(modules = [InputModule::class])
internal interface TestDayComponent : DayComponent {
    fun inject(day04Test: Day04Test)
}
