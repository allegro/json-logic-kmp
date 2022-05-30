package array

import operation.StandardLogicOperation
import utils.asDoubleList
import utils.asList
import utils.secondOrNull

object Sort : StandardLogicOperation {
    private const val DESC_ORDER = "desc"

    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        with(expression.asList) {
            val elementsToSort = firstOrNull().asList
            val sortingMode = secondOrNull()

            sortIfNumbers(elementsToSort, sortingMode)
        }

    private fun sortIfNumbers(listToSort: List<Any?>, mode: Any?) = listToSort.filterIsInstance<Number>()
        .takeIf { it.size == listToSort.size }
        ?.sortWithMode(mode) ?: listToSort

    private fun List<Number>.sortWithMode(sortingMode: Any?) = asDoubleList.filterNotNull().let {
        if (sortingMode == DESC_ORDER) {
            it.sortedDescending()
        } else {
            it.sorted()
        }
    }
}
