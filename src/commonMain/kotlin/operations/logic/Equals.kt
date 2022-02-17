package operations.logic

import operations.ComparingOperation
import operations.LogicOperation
import utils.asList

internal object Equals : LogicOperation, NoArgumentSafeComparingOperation {
    override val key: String = "=="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asList) {
        sizeSafeCompare(this) { first, second -> first == second }
    }
}

internal interface NoArgumentSafeComparingOperation : ComparingOperation, BinaryValueBooleanUnwrapStrategy {
    fun sizeSafeCompare(values: List<Any?>?, operator: (Int, Int) -> Boolean) = if (values.isEmpty()) {
        true
    } else {
        compareListOfTwo(values?.map(::unwrap)) { first, second -> first == second }
    }

    private fun unwrap(value: Any?): Any? =
        when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull() ?: value
            is List<*> -> value.takeIf { it.size == 1 && it.firstOrNull() !is Boolean }
                ?.let { unwrap(value.firstOrNull()) }
            else -> value
        }

    private fun Any?.isEmpty() = when (this) {
        null -> true
        is List<*> -> isEmpty() || all { it == null }
        else -> false
    }
}
