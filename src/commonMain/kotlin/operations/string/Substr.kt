package operations.string

import LogicOperation
import asList
import intOrZero

object Substr : LogicOperation {
    override val key: String = "substr"

    override fun invoke(expression: Any?, data: Any?): String? {
        return with(expression.asList) {
            val baseString = firstOrNull().toString()
            val startIndex = getOrNull(1).toString().intOrZero
            val endIndex = getOrNull(2).toString().intOrZero
            when (size) {
                2 -> baseString.fromStartIndexToEnd(startIndex)
                3 -> baseString.fromStartIndexToEndIndex(startIndex, endIndex)
                else -> null
            }
        }
    }

    private fun String.fromStartIndexToEnd(startIndex: Int) = if (startIndex > 0) {
        substring(startIndex)
    } else {
        substring(length + startIndex)
    }

    private fun String.fromStartIndexToEndIndex(startIndex: Int, endIndex: Int) = when {
        startIndex >= 0 && endIndex > 0 -> substring(startIndex, startIndex + endIndex)
        startIndex >= 0 && endIndex < 0 -> substring(startIndex, length + endIndex)
        startIndex < 0 && endIndex < 0 -> substring(length + startIndex, length + endIndex)
        startIndex < 0 -> substring(length + startIndex)
        else -> null
    }
}
