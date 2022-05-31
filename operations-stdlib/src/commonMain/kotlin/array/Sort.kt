package array

import operation.StandardLogicOperation
import utils.asDoubleList
import utils.asList
import utils.secondOrNull

object Sort : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        with(expression.asList) {
            val elementsToSort = firstOrNull()?.asList
            val sortingMode = (secondOrNull() as? String).toSortOrder()

            when  {
                elementsToSort.containsOnlyElementsOfType<String>() -> elementsToSort?.sortAsListOfStrings(sortingMode)
                elementsToSort.containsOnlyElementsOfType<Number>() -> elementsToSort?.sortAsListOfDoubles(sortingMode)
                else -> null
            }
        }

    private fun String?.toSortOrder() = when (this) {
        "desc" -> SortOrder.Descending
        "asc" -> SortOrder.Ascending
        else -> SortOrder.Unknown
    }

    private inline fun <reified T> List<Any?>?.containsOnlyElementsOfType() = this?.filterIsInstance<T>()?.size == this?.size

    private fun List<Any?>.sortAsListOfStrings(sortingMode: SortOrder) = (this as? List<String>)?.let {
        modeBasedSort(sortingMode = sortingMode, ascSort = { it.sorted() }, descSort = { it.sortedDescending() })
    }

    private fun List<Any?>.sortAsListOfDoubles(sortingMode: SortOrder) = asDoubleList.filterNotNull().let {
        modeBasedSort(sortingMode = sortingMode, ascSort = { it.sorted() }, descSort = { it.sortedDescending() })
    }

    private fun modeBasedSort(sortingMode: SortOrder, ascSort: (() -> Any?), descSort: (() -> Any?)) =
        when (sortingMode) {
            SortOrder.Descending -> descSort()
            SortOrder.Ascending -> ascSort()
            SortOrder.Unknown -> null
        }
}

private sealed class SortOrder {
    object Descending : SortOrder()
    object Ascending : SortOrder()
    object Unknown : SortOrder()
}
