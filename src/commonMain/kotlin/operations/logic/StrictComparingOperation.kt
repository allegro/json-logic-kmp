package operations.logic

import utils.secondOrNull

internal interface StrictComparingOperation : NoArgumentSafeComparingOperation {
//    fun compareListOfTwoOrDefault(values: List<Any?>?, default: Boolean, operator: (Int, Int) -> Boolean) =
//        values?.comparableList
//            ?.takeIf { it.size >= 2 && nullSafeTypeCompare(it.firstOrNull(), it.secondOrNull()) }
//            ?.let { compare(it, operator) } ?: default

//    override fun compareListOfTwo(values: List<Any?>?, operator: (Int, Int) -> Boolean) = values?.comparableList
//        ?.takeIf { it.size >= 2 }
//        ?.let { compare(it, operator) } ?: false

    override fun sizeSafeCompare(values: List<Any?>?, operator: (Int, Int) -> Boolean): Boolean {
        return if (isValueEmpty(values)) {
            true
        } else {
           if (!nullSafeTypeCompare(values?.firstOrNull(), values?.secondOrNull())) {
                false
            } else {
                compareListOfTwo(values?.map(::unwrapValues)) { first, second -> first == second }
            }
        }
    }

    private fun nullSafeTypeCompare(first: Any?, second: Any?) =
        first != null && second != null && first::class == second::class

    override fun isValueEmpty(value: Any?) = when (value) {
        is List<*> -> value.isEmpty() || value.all { it == null }
        null -> false
        else -> false
    }
}
