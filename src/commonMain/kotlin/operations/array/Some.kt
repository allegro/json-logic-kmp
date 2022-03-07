package operations.array

import operations.LogicOperation
import kotlin.collections.Map

internal object Some : LogicOperation, NoInitialValueOperation {
    override val key: String = "some"

    override fun invoke(expression: Any?, data: Any?): Any? = ""
}
