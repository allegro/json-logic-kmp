import operations.data.Missing
import operations.data.MissingSome
import operations.data.Var
import operations.numeric.Addition
import operations.numeric.Division
import operations.numeric.GreaterThan
import operations.numeric.GreaterThanOrEqualTo
import operations.numeric.LessThan
import operations.numeric.LessThanOrEqualTo
import operations.numeric.Max
import operations.numeric.Min
import operations.numeric.Modulo
import operations.numeric.Multiplication
import operations.numeric.Subtraction

internal class CommonJsonLogicEngine : JsonLogicEngine {
    private val operations: Map<String, (Any?, Any?) -> Any?> = mapOf(
        // data
        Var.operation,
        MissingSome.operation,
        Missing.operation,

        // numeric
        GreaterThan.operation,
        GreaterThanOrEqualTo.operation,
        LessThan.operation,
        LessThanOrEqualTo.operation,
        Min.operation,
        Max.operation,
        Addition.operation,
        Subtraction.operation,
        Multiplication.operation,
        Division.operation,
        Modulo.operation,
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
