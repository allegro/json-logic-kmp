package array

import operation.StandardLogicOperation
import utils.asDoubleList
import utils.asList
import utils.secondOrNull

object Sort : StandardLogicOperation {
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
            SortOrder.DESC.order -> it.sortedDescending()
            SortOrder.ASC.order -> it.sorted()
            else -> null
        }
    }
}

private enum class SortOrder(val order: String) {
    DESC("desc"),
    ASC("asc")
}
