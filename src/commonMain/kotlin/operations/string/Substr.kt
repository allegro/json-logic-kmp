package operations.string

import operations.LogicOperation
import utils.asList
import utils.intOrZero

object Substr : LogicOperation, StringUnwrapStrategy {
    override val key: String = "substr"

    override fun invoke(expression: Any?, data: Any?): String {
        return with(expression.asList) {
            val startIndex = getOrNull(1).toString().intOrZero
            val charsCount = getOrNull(2).toString().intOrZero
            substringOrEmpty(startIndex, charsCount)
        }
    }

    private fun List<Any?>.substringOrEmpty(startIndex: Int, charsCount: Int): String {
        val baseString = unwrapValueAsString(firstOrNull()).joinToString(",")
        return runCatching {
            when {
                size == 2 -> baseString.fromStartIndexToEnd(startIndex)
                size > 2 -> baseString.fromStartIndexToEndIndex(startIndex, charsCount)
                else -> baseString
            }
        }.getOrNull().orEmpty()
    }

    private fun String.fromStartIndexToEnd(startIndex: Int) = if (startIndex > 0) {
        substring(startIndex)
    } else {
        substring(length + startIndex)
    }

    private fun String.fromStartIndexToEndIndex(startIndex: Int, charsCount: Int) = when {
        startIndex >= 0 && charsCount > 0 -> substring(startIndex, startIndex + charsCount)
        startIndex >= 0 && charsCount < 0 -> substring(startIndex, length + charsCount)
        startIndex < 0 && charsCount < 0 -> substring(length + startIndex, length + charsCount)
        startIndex < 0 -> substring(length + startIndex)
        else -> null
    }
}
