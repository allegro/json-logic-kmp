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
                // extract
                2 -> if (startIndex > 0) baseString.substring(startIndex) else baseString.substring(baseString.length + startIndex)
                // extract
                3 -> when {
                    startIndex >= 0 && endIndex > 0 -> baseString.substring(startIndex, startIndex + endIndex)
                    startIndex >= 0 && endIndex < 0 -> baseString.substring(startIndex, baseString.length + endIndex)
                    startIndex < 0 && endIndex < 0 -> baseString.substring(baseString.length + startIndex, baseString.length + endIndex)
                    startIndex < 0 -> baseString.substring(baseString.length + startIndex)
                    else -> null
                }
                else -> null
            }
        }
    }
}
