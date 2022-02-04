package operations.numeric

import LogicOperation
import asList

object Min : LogicOperation {
    override val key: String = "min"

    override fun invoke(expression: Any?, data: Any?) = expression.asList
        .filterIsInstance<Number>()
        .minByOrNull { it.toDouble() }
}
