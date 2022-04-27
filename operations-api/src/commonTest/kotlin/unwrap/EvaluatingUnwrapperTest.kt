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

    given("A list") {
        val wrappedValue = listOf("banana", 1)
        `when`("checked") {
            val unwrapResult = strategy.isExpression(wrappedValue)
            then("should not be treated as an expression") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A single value") {
        val wrappedValue = "fake map"
        `when`("checked") {
            val unwrapResult = strategy.isExpression(wrappedValue)
            then("should not be treated as an expression") {
                unwrapResult shouldBe false
            }
        }
    }

    given("An empty map") {
        val wrappedValue = emptyMap<String, Any>()
        `when`("checked") {
            val unwrapResult = strategy.isExpression(wrappedValue)
            then("should not be treated as an expression") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A map with keys other than strings") {
        val wrappedValue = mapOf(1 to "var")
        `when`("checked") {
            val unwrapResult = strategy.isExpression(wrappedValue)
            then("should not be treated as an expression") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A map with string keys") {
        val wrappedValue = mapOf("var" to "a.b")
        `when`("checked") {
            val unwrapResult = strategy.isExpression(wrappedValue)
            then("should be treated as an expression") {
                unwrapResult shouldBe true
            }
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
