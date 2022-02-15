package operations.string

import LogicOperation
import asList
import intOrZero
//import operations.numeric.Subtraction.unwrap
//import operations.string.Cat.stringify
import toStringOrEmpty

object Substr : LogicOperation, StringUnwrapStrategy {
    override val key: String = "substr"

    override fun invoke(expression: Any?, data: Any?): String {
        return with(expression.asList) {
            val startIndex = getOrNull(1).toString().intOrZero
            val endIndex = getOrNull(2).toString().intOrZero
            substringOrEmpty(startIndex, endIndex)
        }
    }

    private fun List<Any?>.substringOrEmpty(startIndex: Int, endIndex: Int): String {
//        val baseString = firstOrNull().stringify()
        // not null safe
        val baseString = unwrapAsStrings(firstOrNull()).joinToString(",")
        return runCatching {
            when {
                size == 2 -> baseString.fromStartIndexToEnd(startIndex)
                size >= 3 -> baseString.fromStartIndexToEndIndex(startIndex, endIndex)
                else -> baseString
            }
        }.getOrNull().orEmpty()
    }

//    // common
//    private fun Any?.stringify() = (this as? List<*>)?.flatMap { nestedValue ->
//        nestedValue.flattenNestedLists()
//    }?.joinToString(separator = ",") ?: formatValues()
//
//    // common
//    private fun Any?.flattenNestedLists(): List<String> = (this as? List<*>)?.flatMap {
//        it.flattenNestedLists()
//    } ?: listOf(formatValues())
//
//    // common
//    private fun Any?.formatValues(): String {
//        return when {
//            (this is Number && toDouble() == toInt().toDouble()) -> toInt().toString()
//            else -> toStringOrEmpty()
//        }
//    }

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
