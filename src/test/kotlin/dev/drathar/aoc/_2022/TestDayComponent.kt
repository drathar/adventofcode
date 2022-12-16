package dev.drathar.aoc._2022

import dagger.Component
import dev.drathar.aoc.DayComponent
import dev.drathar.aoc.InputModule
import dev.drathar.aoc._2022.calendar.day02.Day02Test
import javax.inject.Singleton


@Singleton
@Component(modules = [InputModule::class])
internal interface TestDayComponent : DayComponent {
    fun inject(day02Test: Day02Test)
}
