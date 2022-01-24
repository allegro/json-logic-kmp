package expressions

import LogicExpression

internal object MissingSome : LogicExpression {
    override val key: String = "missing_some"

    override fun invoke(expression: Any?, data: Any?): Any? {
        return Any()
    }
}
