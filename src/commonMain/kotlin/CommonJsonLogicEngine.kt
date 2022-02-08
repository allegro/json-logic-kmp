import expressions.Missing
import expressions.MissingSome
import expressions.Var

internal class CommonJsonLogicEngine : JsonLogicEngine {
    private val operations: Map<String, (Any?, Any?) -> Any?> = mapOf(
        Var.operation,
        MissingSome.operation,
        Missing.operation
    )

    override fun evaluate(expression: Map<String, Any?>, data: Any?): Any? = if (expression.isNotEmpty()) {
        apply(expression, data)
    } else {
        throw JsonLogicException("JsonLogic expression mustn't be empty.")
    }

    private fun apply(logic: Any?, data: Any?): Any? {
        return when {
            logic !is Map<*, *> -> logic
            logic.isEmpty() -> data
            else -> {
                val operator = logic.keys.firstOrNull()
                val values = logic[operator]
                operations[operator]?.invoke(when (values) {
                    is List<*> -> values.map { apply(it, data) }
                    is Map<*, *> -> apply(values, data)
                    else -> apply(listOf(values), data)
                }.asList, data)
            }
        }
    }
}
