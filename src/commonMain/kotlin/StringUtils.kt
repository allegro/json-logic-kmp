internal val String.intOrZero: Int
    get() = doubleOrZero.toInt()

internal val String.doubleOrZero: Double
    get() = toDoubleOrNull() ?: 0.0

