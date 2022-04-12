package utils

val String.intOrZero: Int
    get() = doubleOrZero.toInt()

val String.longOrZero: Long
    get() = doubleOrZero.toLong()

val String.doubleOrZero: Double
    get() = toDoubleOrNull() ?: 0.0

