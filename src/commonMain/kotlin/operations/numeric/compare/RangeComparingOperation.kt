package operations.numeric.compare

import operations.ComparingOperation
import utils.comparableList

internal interface RangeComparingOperation : ComparingOperation {
    fun compareOrBetween(
        values: List<Any?>?,
        operator: (Int, Int) -> Boolean
    ) = values?.comparableList?.let { comparableList ->
        when {
            comparableList.size == 2 -> compareListOfTwo(comparableList, operator)
            comparableList.size > 2 -> comparableList.between(operator)
            else -> false
        }
    } ?: false

    private fun List<Comparable<*>?>.between(operator: (Int, Int) -> Boolean): Boolean {
        val firstEvaluation = compareListOfTwo(this.subList(0, 2), operator)
        val secondEvaluation = compareListOfTwo(this.subList(1, 3), operator)
        return firstEvaluation && secondEvaluation
    }
}
