import operations.In
import operations.Log
import operations.LogicOperation
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
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableMap
import kotlin.collections.contains
import kotlin.collections.forEach
import kotlin.collections.mapOf
import kotlin.collections.mutableMapOf
import operations.array.Map as LogicMap

//operacje nie moga przechowywac zbioru akcji w swoim stanie, bo musialby byc modyfikowalny, uzytkownik tworzac instancje operacji nie moze zapewnic setu operacji, co najwyzej moze przekazac konstruktor/przepis na stworzneie operacji
// stad operacje raczej beda przesylane jako parametr, wtedy trzeba zmienic typy operations, tak, zeby te drugie przyjmowaly mape z operacjami dodatkowo
// TODO sprawdzic czy js impl pozwala na dodawanie akcji unkcyjncyh
// jezeli nie to przegadac w teamie czy chcemy tak zrobic

interface JsonLogicEngine {
    fun evaluate(expression: Map<String, Any?>, data: Any?): JsonLogicResult

    class Builder() {
        private var logger: ((Any?) -> Unit)? = null
        private val standardOperations: MutableMap<String, LogicOperation> = mutableMapOf(
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
            In.operation,
        )
        private val functionalOperations: MutableMap<String, LogicOperation> = mutableMapOf()

        private fun initializeFunctionalOperations(operations: LogicOperations) {
            val functionalOnes = mapOf(
                // array
                LogicMap(operations).operation,
                Filter(operations).operation,
                Reduce(operations).operation,
                All(operations).operation,
                None(operations).operation,
                Some(operations).operation
            )
            functionalOperations.putAll(functionalOnes)
        }

        fun addStandardOperation(operation: LogicOperation) = apply {
            standardOperations.putNew(operation)
        }

        fun addStandardOperations(vararg operations: LogicOperation) = apply {
            operations.forEach { addStandardOperation(it) }
        }

        fun addStandardOperations(operations: List<LogicOperation>) = apply {
            operations.forEach { addStandardOperation(it) }
        }

        fun addLogger(loggingCallback: ((Any?) -> Unit)) {
            logger = loggingCallback
        }

        // podczas budowanai selfEvaluating functions beda potrzebowac dostepnych operacji i dodatkowo samych siebie nawzajem tez
        fun build(): JsonLogicEngine {
            Log(logger).let { standardOperations.put(it.key, it) }
            val operations = LogicOperations(standardOperations, functionalOperations)
            initializeFunctionalOperations(operations)
            return CommonJsonLogicEngine(operations)
        }

        private fun MutableMap<String, LogicOperation>.putNew(operation: LogicOperation) {
            if (contains(operation.key).not()) {
                put(operation.key, operation)
            }
        }
    }
}

internal abstract class FunctionalLogicOperation(protected val operations: LogicOperations) : LogicOperation

object CustomOperation : LogicOperation {
    override val key: String = "custom"
    override fun invoke(p1: Any?, p2: Any?): Any? {
        return "custom result"
    }
}

internal data class LogicOperations(
    val standardOperations: Map<String, LogicOperation> = emptyMap(),
    val functionalOperations: Map<String, LogicOperation> = emptyMap() // testing purpose
)
