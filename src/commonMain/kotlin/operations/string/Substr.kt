package operations.string

import LogicOperation
import asList
import intOrZero

object Substr : LogicOperation {
    override val key: String = "substr"

    override fun invoke(expression: Any?, data: Any?): String? {
        return with(expression.asList) {
            val str = firstOrNull().toString()
            val a = getOrNull(1).toString().intOrZero
            val b = getOrNull(2).toString().intOrZero
            when (size) {
                2 -> if (a > 0) str.substring(a) else str.substring(str.length + a)
                3 -> when {
                    a >= 0 && b > 0 -> str.substring(a, a + b)
                    a >= 0 && b < 0 -> str.substring(a, str.length + b)
                    a < 0 && b < 0 -> str.substring(str.length + a, str.length + b)
                    a < 0 -> str.substring(str.length + a)
                    else -> null
                }
                else -> null
            }
        }
    }
}
