package operations.data

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class MissingSomeTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with MissingSome operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("a" to "apple"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("b" to "banana"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("c" to "carrot"),
                    resultValue = JsonLogicResult.Success(listOf("a", "b"))
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "c" to "carrot"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "b" to "banana", "c" to "carrot"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "d" to "durian"),
                    resultValue = JsonLogicResult.Success(listOf("b", "c"))
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("d" to "durian", "e" to "eggplant"),
                    resultValue = JsonLogicResult.Success(listOf("a", "b", "c"))
                ),
            )
        )
    }
})
