package operations.array

import operations.LogicOperation
import utils.asList

internal object Merge : LogicOperation, NoInitialValueOperation {
    override val key: String = "merge"

    override fun invoke(expression: Any?, data: Any?): Any = expression.asList.merge()

    private fun List<Any?>.merge(): List<Any?> = flatMap {
        when (it) {
            is List<*> -> it
            else -> listOf(it)
        }
    }
}
