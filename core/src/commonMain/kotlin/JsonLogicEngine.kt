import evaluation.CommonLogicEvaluator
import evaluation.LogicOperations
import operation.FunctionalLogicOperation
import operation.StandardLogicOperation
import operations.In
import operations.Log
import operations.array.Filter
import operations.array.Merge
import operations.array.Reduce
import operations.array.occurence.All
import operations.array.occurence.None
import operations.array.occurence.Some
import operations.data.Missing
import operations.data.MissingSome
import operations.data.Var
import operations.logic.And
import operations.logic.DoubleNegation
import operations.logic.If
import operations.logic.Negation
import operations.logic.Or
import operations.logic.equals.Equals
import operations.logic.equals.NotEquals
import operations.logic.equals.strict.NotStrictEquals
import operations.logic.equals.strict.StrictEquals
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
import kotlin.collections.Map
import operations.array.Map as LogicMap

interface JsonLogicEngine {
    fun evaluate(expression: Map<String, Any?>, data: Any?): JsonLogicResult

    class Builder {
        private var logger: ((Any?) -> Unit)? = null
        private val standardOperations: MutableMap<String, StandardLogicOperation> = mutableMapOf(
            // data
            "var" to Var,
            "missing_some" to MissingSome,
            "missing" to Missing,

            // numeric
            ">" to GreaterThan,
            ">=" to GreaterThanOrEqualTo,
            "<" to LessThan,
            "<=" to LessThanOrEqualTo,
            "min" to Min,
            "max" to Max,
            "+" to Addition,
            "-" to Subtraction,
            "*" to Multiplication,
            "/" to Division,
            "%" to Modulo,

            // logic
            "==" to Equals,
            "!=" to NotEquals,
            "===" to StrictEquals,
            "!==" to NotStrictEquals,
            "!" to Negation,
            "!!" to DoubleNegation,
            "and" to And,
            "or" to Or,
            "if" to If,

            // string
            "cat" to Cat,
            "substr" to Substr,

            // array
            "merge" to Merge,

            // string & array
            "in" to In,
        )
        private val functionalOperations: MutableMap<String, FunctionalLogicOperation> = mutableMapOf(
            // array
            "map" to LogicMap,
            "filter" to Filter,
            "reduce" to Reduce,
            "all" to All,
            "none" to None,
            "some" to Some
        )

        fun addStandardOperation(operationName: String, operation: StandardLogicOperation) = apply {
            if (isNotOperationDuplicate(operationName)) {
                standardOperations[operationName] = operation
            }
        }

        fun addStandardOperations(operations: Map<String, StandardLogicOperation>) = apply {
            operations.forEach { (name, lambda) -> addStandardOperation(name, lambda) }
        }

        fun addFunctionalOperation(operationName: String, operation: FunctionalLogicOperation) = apply {
            if (isNotOperationDuplicate(operationName)) {
                functionalOperations[operationName] = operation
            }
        }

        fun addFunctionalOperations(operations: Map<String, FunctionalLogicOperation>) = apply {
            operations.forEach { (name, lambda) -> addFunctionalOperation(name, lambda) }
        }

        fun addLogger(loggingCallback: ((Any?) -> Unit)) = apply {
            logger = loggingCallback
        }

        private fun isNotOperationDuplicate(operationName: String) =
            functionalOperations.contains(operationName).not() && standardOperations.contains(operationName).not()

        fun build(): JsonLogicEngine {
            Log(logger).let { standardOperations.put("log", it) }
            val evaluator = CommonLogicEvaluator(LogicOperations(standardOperations, functionalOperations))
            return CommonJsonLogicEngine(evaluator)
        }
    }
}
