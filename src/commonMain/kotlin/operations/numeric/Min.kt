package operations.numeric

import operations.LogicOperation

internal object Min : LogicOperation, DoubleTypeSensitiveOperation {
    override val key: String = "min"

    override fun invoke(expression: Any?, data: Any?): Any? = doubleResultOrNull(expression) { it.minOrNull() }
}
