package operations.array

import operations.StandardLogicOperation
import utils.asList

internal object Merge : StandardLogicOperation {
    override fun invoke(expression: Any?, data: Any?): Any = expression.asList.mergeOrAdd()

    private fun List<Any?>.mergeOrAdd(): List<Any?> = flatMap {
        when (it) {
            is List<*> -> it
            else -> listOf(it)
        }
    }
}
