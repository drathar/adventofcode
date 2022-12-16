package dev.drathar.aoc._2022

import dagger.Component
import dev.drathar.aoc.DayComponent
import dev.drathar.aoc.InputModule
import dev.drathar.aoc._2022.calendar.day05.Day05Test
import javax.inject.Singleton


@Singleton
@Component(modules = [InputModule::class])
internal interface TestDayComponent : DayComponent {
    fun inject(day05Test: Day05Test)
}
