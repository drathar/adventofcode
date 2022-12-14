package dev.drathar.aoc.generators

import java.io.File

class InputGenerator private constructor(private val text: String) {

    private val lines: Sequence<String> = text.lineSequence()

    fun <Out> readOneLine(singleLineHandler: (String) -> Out) = singleLineHandler(lines.first())

    fun <Out> readLines(sequenceHandler: (Sequence<String>) -> Out) = sequenceHandler(lines)

    fun <In, Out> readLinesAs(converter: (String) -> In, handler: (Sequence<In>) -> Out) = handler(lines.map(converter))

    fun <Out> readBlock(delimiter: String = "\n\n", blockHandler: (Sequence<Sequence<String>>) -> Out) =
        blockHandler(text.splitToSequence(delimiter).map(String::lineSequence))

    fun <In, Out> readBlockAs(delimiter: String = "\n\n", converter: (String) -> In, blockHandler: (Sequence<Sequence<In>>) -> Out) =
        blockHandler(text.splitToSequence(delimiter).map { it.lineSequence().map(converter) })

    class InputGeneratorFactory {
        fun forFile(fileName: String): InputGenerator {
            val file = File(fileName)

            val text = file.readText()

            return InputGenerator(text)
        }
    }
}
