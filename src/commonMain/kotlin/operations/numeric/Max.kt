package operations.numeric

import LogicOperation
import asList

object Max : LogicOperation {
    override val key: String = "max"

    override fun invoke(expression: Any?, data: Any?) = expression.asList
        .filterIsInstance<Number>()
        .maxByOrNull { it.toDouble() }
}
