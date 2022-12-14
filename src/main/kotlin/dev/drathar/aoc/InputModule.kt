package dev.drathar.aoc

import dagger.Module
import dagger.Provides
import dev.drathar.aoc.generators.InputGenerator
import dev.drathar.aoc.generators.PermutationGenerator
import javax.inject.Singleton

@Module
internal class InputModule {
    @Provides
    @Singleton
    fun inputGeneratorFactory() = InputGenerator.InputGeneratorFactory()

    @Provides
    @Singleton
    fun permutationGenerator() = PermutationGenerator()
}
