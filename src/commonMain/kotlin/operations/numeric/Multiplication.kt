package operations.numeric

import LogicOperation

object Multiplication : LogicOperation, DoubleTypeSensitiveOperation {
    override val key: String = "*"

    override fun invoke(expression: Any?, data: Any?): Any? = resultOrNull(expression) {
        it.reduce { sum: Double, value: Double ->
            sum * value
        }
    }
}
