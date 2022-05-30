package array

import operation.StandardLogicOperation
import utils.asDoubleList
import utils.asList
import utils.secondOrNull

object Sort : StandardLogicOperation {
    private const val DESC_ORDER = "desc"
    private const val ASC_ORDER = "asc"

    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        with(expression.asList) {
            val elementsToSort = firstOrNull()
            val sortingMode = secondOrNull()

            sortIfNumbers(elementsToSort?.asList, sortingMode)
        }

    private fun sortIfNumbers(listToSort: List<Any?>?, mode: Any?) = listToSort?.filterIsInstance<Number>()
        .takeIf { it?.size == listToSort?.size }
        ?.sortWithMode(mode)

    private fun List<Number>.sortWithMode(sortingMode: Any?) = asDoubleList.filterNotNull().let {
        when (sortingMode) {
            DESC_ORDER -> it.sortedDescending()
            ASC_ORDER -> it.sorted()
            else -> null
        }
    }
}
