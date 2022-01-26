package expressions

import LogicExpression
import intValue

internal object Var : LogicExpression {
    override val key: String = "var"

    override operator fun invoke(expression: Any?, data: Any?): Any? {
        var value: Any? = data
        val varName = if (expression is List<*>) {
            expression.firstOrNull()
        } else {
            expression
        }.toString()

        when (value) {
            is List<*> -> {
                val indexParts = varName.split(".")
                value = if (indexParts.size == 1) value[indexParts[0].intValue] else getRecursive(indexParts, value)
            }
            is Map<*, *> -> varName.split(".").forEach {
                value = (value as? Map<*, *>)?.get(it)
            }
        }
        return  if ((value == expression || value == null) && expression is List<*> && expression.size > 1) {
            expression.getOrNull(1)
        } else {
            value
        }
    }

    private fun getRecursive(indexes: List<String>, data: List<Any?>): Any? = indexes.firstOrNull()?.apply {
        val indexedData = data.getOrNull(intValue) as? List<Any?>
        return if (indexedData is List<*>) getRecursive(indexes.subList(1, indexes.size), indexedData) else data.getOrNull(intValue)
    }
}
