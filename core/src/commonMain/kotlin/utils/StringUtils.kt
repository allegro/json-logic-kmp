package utils

internal val String.intOrZero: Int
    get() = doubleOrZero.toInt()

internal val String.longOrZero: Long
    get() = doubleOrZero.toLong()

internal val String.doubleOrZero: Double
    get() = toDoubleOrNull() ?: 0.0

