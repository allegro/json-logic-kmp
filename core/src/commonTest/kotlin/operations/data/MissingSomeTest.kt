package operations.data

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class MissingSomeTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                data = mapOf("a" to "apple"),
                result = JsonLogicResult.Success(emptyList<Any>())
            ),
            TestInput(
                expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                data = mapOf("b" to "banana"),
                result = JsonLogicResult.Success(emptyList<Any>())
            ),
            TestInput(
                expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                data = mapOf("a" to "apple", "b" to "banana"),
                result = JsonLogicResult.Success(emptyList<Any>())
            ),
            TestInput(
                expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                data = mapOf("c" to "carrot"),
                result = JsonLogicResult.Success(listOf("a", "b"))
            ),
            TestInput(
                expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                data = mapOf("a" to "apple", "b" to "banana"),
                result = JsonLogicResult.Success(emptyList<Any>())
            ),
            TestInput(
                expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                data = mapOf("a" to "apple", "c" to "carrot"),
                result = JsonLogicResult.Success(emptyList<Any>())
            ),
            TestInput(
                expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                data = mapOf("a" to "apple", "b" to "banana", "c" to "carrot"),
                result = JsonLogicResult.Success(emptyList<Any>())
            ),
            TestInput(
                expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                data = mapOf("a" to "apple", "d" to "durian"),
                result = JsonLogicResult.Success(listOf("b", "c"))
            ),
            TestInput(
                expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                data = mapOf("d" to "durian", "e" to "eggplant"),
                result = JsonLogicResult.Success(listOf("a", "b", "c"))
            ),
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult valueShouldBe testInput.result
    }
})
