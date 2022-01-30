package operations.data

import operations.LogicExpression

internal object Missing : LogicExpression {
    override val key: String = "missing"

    override fun invoke(expression: Any?, data: Any?): List<Any?> {
        return (expression as? List<Any?>)?.mapNotNull {
            it.takeIf { Var(it, data) == null }
        }.orEmpty()
    }
}
