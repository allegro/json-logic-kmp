package operations.array

import operations.LogicOperation
import kotlin.collections.Map

internal object All : LogicOperation, NoInitialValueOperation {
    override val key: String = "all"

    override fun invoke(expression: Any?, data: Any?): Any? = ""
}
