package operations.array

import operations.LogicOperation
import kotlin.collections.Map

internal object None : LogicOperation, NoInitialValueOperation {
    override val key: String = "map"

    override fun invoke(expression: Any?, data: Any?): Any? = ""
}
