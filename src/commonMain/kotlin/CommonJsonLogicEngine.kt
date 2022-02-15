import operations.data.Missing
import operations.data.MissingSome
import operations.data.Var
import operations.logic.DoubleNegation
import operations.logic.Equals
import operations.logic.Negation
import operations.logic.NotEquals
import operations.logic.NotStrictEquals
import operations.logic.StrictEquals
import operations.numeric.Addition
import operations.numeric.Division
import operations.numeric.compare.GreaterThan
import operations.numeric.compare.GreaterThanOrEqualTo
import operations.numeric.compare.LessThan
import operations.numeric.compare.LessThanOrEqualTo
import operations.numeric.Max
import operations.numeric.Min
import operations.numeric.Modulo
import operations.numeric.Multiplication
import operations.numeric.Subtraction
import operations.string.Cat
import operations.string.In
import operations.string.Substr
import utils.JsonLogicException
import utils.asList

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

        // string
        Cat.operation,
        In.operation,
        Substr.operation,

        // logic
        Equals.operation,
        NotEquals.operation,
        StrictEquals.operation,
        NotStrictEquals.operation,
        Negation.operation,
        DoubleNegation.operation,
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
