package operations

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class InTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    "in" to listOf(
                        mapOf("var" to "filling"),
                        listOf("apple", "cherry")
                    )
                ),
                data = mapOf("filling" to "apple"),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("in" to listOf("Bart", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("in" to listOf("Milhouse", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf("Spring", "Springfield")),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("in" to listOf("i", "team")),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf("t", listOf("team"))),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf("1", 133)),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf(1, "133")),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("in" to listOf(1, 133)),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf("t", true)),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf(true, "true gold")),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("in" to listOf(null, "banana")),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf("n", null)),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf(null, listOf(null, null))),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("in" to listOf(null, listOf(listOf(null), "banana"))),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf(null, listOf("", ""))),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf("t", "true")),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("in" to listOf("j", "apple", "juice")),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("in" to listOf("Spring", "Springfield")),
                result = JsonLogicResult.Success(true)
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
