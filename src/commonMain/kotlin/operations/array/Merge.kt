package operations.array

import operations.LogicOperation
import utils.asList
import kotlin.collections.Map

internal object Merge : LogicOperation, NoInitialValueOperation {
    override val key: String = "merge"

    override fun invoke(expression: Any?, data: Any?): Any = expression.asList.merge()

    private fun List<Any?>.merge(): List<Any?> = mutableListOf<Any?>().apply {
        this@merge.forEach {
            when (it) {
                is List<*> -> addAll(it.merge())
                else -> add(it)
            }
        }
    }
}
