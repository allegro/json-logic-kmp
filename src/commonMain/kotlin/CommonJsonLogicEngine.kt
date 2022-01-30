import operations.data.Missing
import operations.data.MissingSome
import operations.data.Var
import operations.logic.Equals
import operations.logic.NotEquals
import operations.logic.NotStrictEquals
import operations.logic.StrictEquals
import utils.asList

internal class CommonJsonLogicEngine : JsonLogicEngine {
    private val operations: Map<String, (Any?, Any?) -> Any?> = mapOf(
        // data access
        Var.operation,
        MissingSome.operation,
        Missing.operation,

        // logic & boolean
        Equals.operation,
        NotEquals.operation,
        StrictEquals.operation,
        NotStrictEquals.operation
    )

    override fun evaluate(expression: Map<String, Any?>, data: Any?): Any? = apply(expression, data)

    private fun apply(logic: Any?, data: Any?): Any? {
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
