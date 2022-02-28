package operations.array

import operations.LogicOperation

internal object Map : LogicOperation {
    override val key: String = "map"

    override fun invoke(expression: Any?, data: Any?): Any? {
return "empty"
    }
}
