package operations.numeric

import asDoubleList

internal interface DoubleTypeSensitiveOperation {
    fun doubleResultOrNull(expression: Any?, operation: (List<Double>) -> Double?): Double? {
        val elements = expression?.asDoubleList
        val notNullElements = elements?.filterNotNull()
        return if (notNullElements?.size == elements?.size) {
            elements?.filterNotNull()?.let { operation(it) }
        } else null
    }
}
