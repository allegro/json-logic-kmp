package operations.numeric.compare

import comparableList
import secondOrNull

//move to the outer package
internal interface StrictComparingOperation : ComparingOperation {
    fun compareListOfTwoOrDefault(values: List<Any?>?, default: Boolean, operator: (Int, Int) -> Boolean) =
        values?.comparableList
            ?.takeIf { it.size >= 2 && it.firstOrNull()!!::class == it.secondOrNull()!!::class }
            ?.compare(operator) ?: default
}
