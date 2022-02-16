package operations.logic

import operations.numeric.compare.ComparingOperation
import utils.comparableList
import utils.secondOrNull

internal interface StrictComparingOperation : ComparingOperation {
    fun compareListOfTwoOrDefault(values: List<Any?>?, default: Boolean, operator: (Int, Int) -> Boolean) =
        values?.comparableList
            ?.takeIf { it.size >= 2 && nullSafeCompare(it.firstOrNull(), it.secondOrNull()) }
            ?.compare(operator) ?: default

    private fun nullSafeCompare(first: Any?, second: Any?) = first != null && second != null && first::class == second::class
}
