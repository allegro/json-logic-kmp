package array

import operation.StandardLogicOperation
import utils.asDoubleList
import utils.asList
import utils.secondOrNull

object Sort : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        with(expression.asList) {
            val elementsToSort = firstOrNull()
            val sortingMode = (secondOrNull() as? String).toSortOrder()

            sortIfNumbers(elementsToSort?.asList, sortingMode)
        }

    private fun String?.toSortOrder() = when (this) {
        SortOrder.DESC.order -> SortOrder.DESC
        SortOrder.ASC.order -> SortOrder.ASC
        else -> null
    }

    private fun sortIfNumbers(listToSort: List<Any?>?, mode: SortOrder?) = listToSort?.filterIsInstance<Number>()
        .takeIf { it?.size == listToSort?.size }
        ?.sortWithMode(mode)

    private fun List<Number>.sortWithMode(sortingMode: SortOrder?) = asDoubleList.filterNotNull().let {
        when (sortingMode) {
            SortOrder.DESC -> it.sortedDescending()
            SortOrder.ASC -> it.sorted()
            else -> null
        }
    }

    private enum class SortOrder(val order: String) {
        DESC("desc"),
        ASC("asc")
    }
}
