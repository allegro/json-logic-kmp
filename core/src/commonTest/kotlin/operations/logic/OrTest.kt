package operations.logic

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class OrTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(expression = mapOf("or" to listOf(true, true)), result = JsonLogicResult.Success(true)),
            TestInput(expression = mapOf("or" to listOf(false, true)), result = JsonLogicResult.Success(true)),
            TestInput(expression = mapOf("or" to listOf(true, false)), result = JsonLogicResult.Success(true)),
            TestInput(
                expression = mapOf("or" to listOf(false, false)),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("or" to listOf(false, false, true)),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("or" to listOf(false, false, false)),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(expression = mapOf("or" to listOf(false)), result = JsonLogicResult.Success(false)),
            TestInput(expression = mapOf("or" to listOf(true)), result = JsonLogicResult.Success(true)),
            TestInput(expression = mapOf("or" to listOf(1, 3)), result = JsonLogicResult.Success(1)),
            TestInput(expression = mapOf("or" to listOf(3, false)), result = JsonLogicResult.Success(3)),
            TestInput(expression = mapOf("or" to listOf(false, 3)), result = JsonLogicResult.Success(3)),
            TestInput(
                expression = mapOf("or" to listOf(emptyList<String>(), true)),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(expression = mapOf("or" to listOf(0, true)), result = JsonLogicResult.Success(true)),
            TestInput(expression = mapOf("or" to listOf("", true)), result = JsonLogicResult.Success(true)),
            TestInput(expression = mapOf("or" to listOf("0", true)), result = JsonLogicResult.Success("0")),
            TestInput(
                expression = mapOf("or" to listOf("0", listOf("banana"))),
                result = JsonLogicResult.Success("0")
            ),
            TestInput(
                expression = mapOf("or" to listOf(listOf("grapes"), listOf("banana"))),
                result = JsonLogicResult.Success(listOf("grapes"))
            ),
            TestInput(
                expression = mapOf("or" to listOf(false, listOf("banana"))),
                result = JsonLogicResult.Success(listOf("banana"))
            ),
            TestInput(
                expression = mapOf("or" to listOf(true, listOf("banana"))),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("or" to listOf(listOf(null), listOf("banana"))),
                result = JsonLogicResult.Success(listOf(null))
            ),
            TestInput(
                expression = mapOf("or" to listOf(listOf(null), true)),
                result = JsonLogicResult.Success(listOf(null))
            ),
            TestInput(
                expression = mapOf("or" to listOf(listOf(null), false)),
                result = JsonLogicResult.Success(listOf(null))
            ),
            TestInput(
                expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf("banana"))),
                result = JsonLogicResult.Success(listOf(emptyList<String>()))
            ),
            TestInput(
                expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null))),
                result = JsonLogicResult.Success(listOf(emptyList<String>()))
            ),
            TestInput(
                expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null), listOf("banana"))),
                result = JsonLogicResult.Success(listOf(emptyList<String>()))
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
