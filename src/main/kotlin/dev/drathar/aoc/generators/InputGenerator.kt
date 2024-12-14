package dev.drathar.aoc.generators

import java.io.File

class InputGenerator private constructor(private val lines: Sequence<String>) {

    fun <Out> readOneLine(singleLineHandler: (String) -> Out) = singleLineHandler(lines.first())

    fun <Out> readLines(sequenceHandler: (Sequence<String>) -> Out) = sequenceHandler(lines)

    fun <In, Out> readLinesAs(converter: (String) -> In, handler: (Sequence<In>) -> Out) = handler(lines.map(converter))

    class InputGeneratorFactory {
        fun forFile(fileName: String): InputGenerator {
            val file = File(fileName)
            val bufferedReader = file.bufferedReader()
            val lines = bufferedReader.lineSequence()
            return InputGenerator(lines)
        }
    }
}
