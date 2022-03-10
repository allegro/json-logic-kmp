import operations.In
import operations.array.All
import operations.array.Filter
import operations.array.Merge
import operations.array.None
import operations.array.Reduce
import operations.array.Some
import operations.data.Missing
import operations.data.MissingSome
import operations.data.Var
import operations.logic.And
import operations.logic.DoubleNegation
import operations.logic.Equals
import operations.logic.If
import operations.logic.Negation
import operations.logic.NotEquals
import operations.logic.NotStrictEquals
import operations.logic.Or
import operations.logic.StrictEquals
import operations.numeric.Addition
import operations.numeric.Division
import operations.numeric.Max
import operations.numeric.Min
import operations.numeric.Modulo
import operations.numeric.Multiplication
import operations.numeric.Subtraction
import operations.numeric.compare.GreaterThan
import operations.numeric.compare.GreaterThanOrEqualTo
import operations.numeric.compare.LessThan
import operations.numeric.compare.LessThanOrEqualTo
import operations.string.Cat
import operations.string.Substr

interface JsonLogicEngine {
    fun evaluate(expression: Map<String, Any?>, data: Any?): JsonLogicResult

    companion object {
        val instance: JsonLogicEngine by lazy { CommonJsonLogicEngine() }
        internal val standardOperations: Map<String, (Any?, Any?) -> Any?> = mapOf(
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

            // logic
            Equals.operation,
            NotEquals.operation,
            StrictEquals.operation,
            NotStrictEquals.operation,
            Negation.operation,
            DoubleNegation.operation,
            And.operation,
            Or.operation,
            If.operation,

            // string
            Cat.operation,
            Substr.operation,

            // array
            Merge.operation,

            // string & array
            In.operation
        )
        internal val selfEvaluatingOperations: Map<String, (Any?, Any?) -> Any?> = mapOf(
            // array
            operations.array.Map.operation,
            Filter.operation,
            Reduce.operation,
            All.operation,
            None.operation,
            Some.operation
        )
    }
}
