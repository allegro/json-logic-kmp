package operations.string

import LogicOperation
import asDoubleList
import asList

object Cat : LogicOperation {
    override val key: String = "cat"

    override fun invoke(expression: Any?, data: Any?) = expression.asList.map { if (it is Number && it.toDouble() == it.toInt().toDouble()) it.toInt() else it }
        .joinToString("")
}
