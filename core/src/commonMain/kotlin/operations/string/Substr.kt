package operations.string

import operation.StandardLogicOperation
import utils.asList
import utils.intOrZero
import utils.secondOrNull
import utils.thirdOrNull
import kotlin.math.abs

internal object Substr : StandardLogicOperation, StringUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): String {
        return with(expression.asList) {
            val startIndex = secondOrNull().toString().intOrZero
            val charsCount = thirdOrNull().toString().intOrZero
            substringOrEmpty(startIndex, charsCount)
        }
    }

    private fun List<Any?>.substringOrEmpty(startIndex: Int, charsCount: Int): String {
        val baseString = unwrapValueAsString(firstOrNull()).joinToString(",")
        return runCatching {
            when {
                size == 2 -> baseString.startIndexSubstr(startIndex)
                size > 2 -> baseString.fromStartIndexToEndIndex(startIndex, charsCount)
                else -> baseString
            }
        }.getOrNull().orEmpty()
    }

    // new impl
    // TODO make it prettier
    private fun String.startIndexSubstr(startIndex: Int): String {
        return if (startIndex >= 0) {
            substring(startIndex)
        } else {
            if (abs(startIndex) > length) {
                this
            } else {
                substring(length + startIndex)
            }
        }
    }

    private fun String.fromStartIndexToEndIndex(startIndex: Int, charsCount: Int) = when {
        startIndex >= 0 && charsCount > 0 -> {
            val newCount = (startIndex + charsCount).takeIf { it <= length } ?: length
            substring(startIndex, newCount)
        }
        startIndex >= 0 && charsCount < 0 -> substring(startIndex, length + charsCount)
        startIndex < 0 && charsCount < 0 -> {
            val newStartIndex = (length + startIndex).takeIf { it >= 0 } ?: 0
            val newCount = (length + charsCount).takeIf { it <= length } ?: length
            substring(newStartIndex, newCount)
        }
        startIndex < 0 && charsCount > 0 -> {
            val newStartIndex = (length + startIndex).takeIf { it >= 0 } ?: 0
            val newCount = if(newStartIndex + charsCount > length) {
                length
            } else newStartIndex + charsCount
            substring(newStartIndex, newCount)
        }
        else -> null
    }
}
