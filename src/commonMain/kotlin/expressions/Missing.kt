package expressions

import LogicExpression

internal object Missing : LogicExpression {
    override val key: String = "missing"

    override fun invoke(expression: Any?, data: Any?): List<Any?> {
        return arrayListOf<Any?>().apply {
            (expression as? List<Any?>)?.forEach {
                if (Var(it, data) == null) add(it)
            }
        }
    }
}
