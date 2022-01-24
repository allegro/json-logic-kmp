package expressions

import LogicExpression

internal object Var : LogicExpression {
    override val key: String = "var"

    override operator fun invoke(expression: Any?, data: Any?): Any? {
        return Any()
    }
}
