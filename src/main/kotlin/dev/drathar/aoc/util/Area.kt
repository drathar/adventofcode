package dev.drathar.aoc.util

typealias Area <T> = List<List<T>>

val <T> Area<T>.width: Int
    get() = first().size

val <T> Area<T>.height: Int
    get() = size

operator fun <T> Area<T>.contains(location: Location): Boolean =
    location.y in 0..<height && location.x in 0..<width

fun <T> Area<T>.getValueAtLocation(location: Location): T? {
    if (location !in this) return null

    return this[location.y][location.x]
}
