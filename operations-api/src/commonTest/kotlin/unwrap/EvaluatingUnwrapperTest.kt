package unwrap

import LogicEvaluator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class EvaluatingUnwrapperTest : BehaviorSpec({
    val strategy: EvaluatingUnwrapper = object : EvaluatingUnwrapper {}
    var logicEvaluator: LogicEvaluator = object : LogicEvaluator {
        override fun evaluateLogic(expression: Map<String, Any?>, data: Any?): Any? {
            return data
        }
    }

    given("A simple values input") {
        val wrappedValue = listOf(listOf(1, 2, 3))
        `when`("unwrapped") {
            val unwrapResult = strategy.unwrapDataByEvaluation(wrappedValue, null, logicEvaluator)
            then("should be extracted") {
                unwrapResult shouldBe listOf(1, 2, 3)
            }
        }
    }

    given("A nested values input") {
        val wrappedValue = listOf(listOf(1, 2, 3, listOf(listOf("banana"))))
        `when`("unwrapped") {
            val unwrapResult = strategy.unwrapDataByEvaluation(wrappedValue, null, logicEvaluator)
            then("should be extracted") {
                unwrapResult shouldBe listOf(1, 2, 3, listOf(listOf("banana")))
            }
        }
    }

    given("An input with operation") {
        val wrappedValue = listOf(listOf(1, 2, 3, mapOf("var" to "integers")))
        val operationData = mapOf("integers" to listOf(4, 5))
        logicEvaluator = object : LogicEvaluator {
            override fun evaluateLogic(expression: Map<String, Any?>, data: Any?): Any? {
                return listOf(4, 5)
            }
        }
        `when`("unwrapped") {
            val unwrapResult = strategy.unwrapDataByEvaluation(wrappedValue, operationData, logicEvaluator)
            then("should be evaluated and extracted") {
                unwrapResult shouldBe listOf(1, 2, 3, listOf(4, 5))
            }
        }
    }
})
