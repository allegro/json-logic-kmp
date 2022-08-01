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
                size == 2 -> baseString.startIndexSubstring(startIndex)
                size > 2 -> baseString.fromStartIndexToEndIndex(startIndex, charsCount)
                else -> baseString
            }
        }.getOrNull().orEmpty()
    }

    private fun String.startIndexSubstring(startIndex: Int): String = when {
        startIndex >= 0 -> substring(startIndex)
        abs(startIndex) <= length -> substring(length + startIndex)
        else -> this
    }

    private fun String.fromStartIndexToEndIndex(startIndex: Int, charsCount: Int) = when {
        startIndex >= 0 && charsCount > 0 -> notNegativeArgsSubstring(startIndex, charsCount)
        startIndex >= 0 && charsCount < 0 -> substring(startIndex, length + charsCount)
        startIndex < 0 && charsCount < 0 -> negativeArgsSubstring(startIndex, charsCount)
        startIndex < 0 && charsCount > 0 -> negativeStartIndexSubString(startIndex, charsCount)
        else -> null
    }

    private fun String.notNegativeArgsSubstring(startIndex: Int, charsCount: Int): String {
        val outOfBoundsSafeCount = (startIndex + charsCount).constrainOutOfBoundsCharsCount(length)
        return substring(startIndex, outOfBoundsSafeCount)
    }

    private fun String.negativeArgsSubstring(startIndex: Int, charsCount: Int): String {
        val outOfBoundsSafeStartIndex = constrainNegativeStartIndex(startIndex)
        val outOfBoundsSafeCount = (length + charsCount).constrainOutOfBoundsCharsCount(length)
        return substring(outOfBoundsSafeStartIndex, outOfBoundsSafeCount)
    }

    private fun String.negativeStartIndexSubString(startIndex: Int, charsCount: Int): String {
        val outOfBoundsSafeStartIndex = constrainNegativeStartIndex(startIndex)
        val outOfBoundsSafeCount = (outOfBoundsSafeStartIndex + charsCount).constrainOutOfBoundsCharsCount(length)
        return substring(outOfBoundsSafeStartIndex, outOfBoundsSafeCount)
    }

    private fun String.constrainNegativeStartIndex(startIndex: Int) = (length + startIndex).takeIf { it >= 0 } ?: 0

    private fun Int.constrainOutOfBoundsCharsCount(sourceStringLength: Int) = takeIf { it <= sourceStringLength } ?: sourceStringLength
}
