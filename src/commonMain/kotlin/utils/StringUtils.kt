package utils

internal val String.intValue: Int
    get() = doubleValue.toInt()

internal val String.doubleValue: Double
    get() = toDoubleOrNull() ?: 0.0
