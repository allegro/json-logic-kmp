package operations.numeric.compare

import operations.ComparingOperation
import utils.comparableList
import utils.secondOrNull

//move to the outer package
internal interface RangeComparingOperation : ComparingOperation {

    fun compareOrBetween(
        values: List<Any?>?,
        operator: (Int, Int) -> Boolean
    ) = values?.comparableList?.let { comparableList ->
        when {
            comparableList.size == 2 -> comparableList.compare(operator)
            comparableList.size > 2 -> comparableList.between(operator)
            else -> false
        }
    } ?: false

    private fun List<Comparable<*>?>.between(operator: (Int, Int) -> Boolean): Boolean {
        val firstEvaluation = compareOrNull(firstOrNull(), secondOrNull())
        val secondEvaluation = compareOrNull(secondOrNull(), getOrNull(2))
        return if (firstEvaluation != null && secondEvaluation != null) {
            operator(firstEvaluation, 0) && operator(secondEvaluation, 0)
        } else false
    }
}
