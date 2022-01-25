package expressions

import LogicExpression
import asString
import intValue
import unStringify

internal object Var : LogicExpression {
    override val key: String = "var"

    override operator fun invoke(expression: Any?, data: Any?): Any {
        var value: Any? = expression
        val varName = if (data is List<*>) {
            data.firstOrNull()
        } else {
            data
        }.toString()

        when (value) {
            is List<*> -> {
                val indexParts = varName.unStringify.split(".")
                value = if (indexParts.size == 1) value[indexParts[0].intValue] else getRecursive(indexParts, value)
            }
            is Map<*, *> -> varName.unStringify.split(".").forEach {
                value = (value as? Map<*, *>)?.get(it)
            }
        }
        if ((value == data || value == null) && data is List<*> && data.size > 1) {
            return data.getOrNull(1)?.asString.toString()
        }
        return value?.asString.toString()
    }

    private fun getRecursive(indexes: List<String>, data: List<Any?>): Any? = indexes.firstOrNull()?.apply {
        val d = data.getOrNull(intValue) as? List<Any?>
        return if (d is List<*>) getRecursive(indexes.subList(1, indexes.size), d) else data.getOrNull(intValue)
    }
}
