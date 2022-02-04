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
        }?.toString()

        val indexParts = varName?.split(".").orEmpty()
        if (indexParts.isNotEmpty()) {
            value = getIndexedValue(value, indexParts)
        }

        return if (shouldUseDefaultValue(value, expression)) {
            (expression as? List<*>)?.getOrNull(1)
        } else {
            value
        }
    }

    private fun getIndexedValue(value: Any?, indexParts: List<String>): Any? {
        return when (value) {
            is List<*> -> {
                if (indexParts.size == 1) {
                    value[indexParts.first().intValue]
                } else {
                    getRecursive(
                        indexParts,
                        value
                    )
                }
            }
            is Map<*, *> -> {
                val initial = value[indexParts.first()]
                indexParts.drop(1).fold(initial) { acc: Any?, s: String ->
                    (acc as? Map<*, *>)?.get(s)
                }
            }
            else -> value
        }
    }

    private fun shouldUseDefaultValue(value: Any?, expression: Any?) = (value == expression || value == null)
        && expression is List<*>
        && expression.size > 1

    private fun getRecursive(indexes: List<String>, data: List<Any?>): Any? = indexes.firstOrNull()?.apply {
        val indexedData = data.getOrNull(intValue) as? List<Any?>
        return if (indexedData is List<*>) getRecursive(
            indexes.subList(1, indexes.size),
            indexedData
        ) else data.getOrNull(intValue)
    }
}
