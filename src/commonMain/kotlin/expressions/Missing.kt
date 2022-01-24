package expressions

import LogicExpression

internal object Missing : LogicExpression {
    override val key: String = "missing"

    override fun invoke(expression: Any?, data: Any?): Any? {
        return Any()
    }
}
