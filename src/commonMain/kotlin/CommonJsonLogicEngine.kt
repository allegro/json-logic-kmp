import expressions.Missing
import expressions.MissingSome
import expressions.Var
import expressions.Var.operation

internal class CommonJsonLogicEngine : JsonLogicEngine {
    private val operations: Map<String, (Any?, Any?) -> Any?> = mapOf(
        Var.operation,
        MissingSome.operation,
        Missing.operation
    )

    override fun evaluate(expression: Map<String, Any?>, data: Map<String, Any>): String {
        return apply(expression, data).toString()
    }

    private fun apply(logic: Any?, data: Map<String, Any>): Any? {
        if (logic !is Map<*, *>) return logic
        if (logic.isEmpty()) return data
        val operator = logic.keys.firstOrNull()
        val values = logic[operator]
        return operations[operator]?.invoke(when (values) {
            is List<*> -> values.map { apply(it, data) }
            is Map<*, *> -> apply(values, data)
            else -> apply(listOf(values), data)
        }.asList, data)
    }
}
