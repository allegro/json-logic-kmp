package operations.logic

import JsonLogicEngine
import JsonLogicResult.Success
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class EqualsTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(expression = mapOf("==" to listOf(1, 1)), result = Success(true)),
            TestInput(expression = mapOf("==" to listOf(1, "1")), result = Success(true)),
            TestInput(expression = mapOf("==" to listOf(1, 2)), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf(null, 2)), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf(null, true)), result = Success(false)),
            TestInput(
                expression = mapOf("==" to listOf(null, false)),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(null, "false")),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(null, "true")),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(false, "false")),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(false, listOf("false"))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(false, listOf(false))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(emptyList<Any>(), listOf(emptyList<Any>()))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(emptyList<Any>(), null)),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(emptyList(), emptyList<Any>())),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf("null", null)),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(true, "true")),
                result = Success(false)
            ),
            TestInput(expression = mapOf("==" to listOf(1, null)), result = Success(false)),
            TestInput(
                expression = mapOf("==" to listOf(listOf("banana"), null)),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(listOf("banana"), true)),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(listOf("banana"), false)),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(true, false)),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(false, true)),
                result = Success(false)
            ),
            TestInput(expression = mapOf("==" to listOf(true, true)), result = Success(true)),
            TestInput(
                expression = mapOf("==" to listOf(1, listOf("1"))),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf(1, listOf(listOf("1")))),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf("banana", listOf(listOf("banana")))),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf("banana", listOf("banana", "banana"))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(1, listOf(1))),
                result = Success(true)
            ),
            TestInput(expression = mapOf("==" to listOf(1, false)), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf(-1, false)), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf(0, false)), result = Success(true)),
            TestInput(expression = mapOf("==" to listOf(0, true)), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf(1, true)), result = Success(true)),
            TestInput(expression = mapOf("==" to listOf(-1, true)), result = Success(false)),
            TestInput(
                expression = mapOf("==" to listOf(true, true, false)),
                result = Success(true)
            ),
            TestInput(expression = mapOf("==" to listOf(1, 0, 1)), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf(1)), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf(true)), result = Success(false)),
            TestInput(expression = mapOf("==" to true), result = Success(false)),
            TestInput(expression = mapOf("==" to false), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf("true")), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf("banana")), result = Success(false)),
            TestInput(expression = mapOf("==" to "banana"), result = Success(false)),
            TestInput(expression = mapOf("==" to listOf(null)), result = Success(true)),
            TestInput(expression = mapOf("==" to null), result = Success(true)),
            TestInput(expression = mapOf("==" to ""), result = Success(false)),
            TestInput(expression = mapOf("==" to "     "), result = Success(false)),
            TestInput(expression = mapOf("==" to emptyList<Any>()), result = Success(true)),
            TestInput(
                expression = mapOf("==" to listOf(emptyList<Any>())),
                result = Success(false)
            ),
            TestInput(expression = mapOf("==" to listOf(null, null)), result = Success(true)),
            TestInput(
                expression = mapOf("==" to listOf(listOf(null), listOf(null))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(null, listOf(null))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(1, listOf(null))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(0, listOf(null))),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf(true, listOf(null))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(false, listOf(null))),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf(listOf(false), listOf(false))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(-1, listOf(null))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(0.5, listOf(null))),
                result = Success(false)
            ),
            TestInput(expression = mapOf("==" to listOf("", "")), result = Success(true)),
            TestInput(expression = mapOf("==" to listOf("", "    ")), result = Success(false)),
            TestInput(
                expression = mapOf("==" to listOf("", listOf(""))),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf(listOf(""), listOf(""))),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf("", listOf(listOf("")))),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf("", emptyList<String>())),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf(false, emptyList<String>())),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf(0, emptyList<String>())),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("==" to listOf("0", emptyList<String>())),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf("0.0", emptyList<String>())),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(1, emptyList<String>())),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf("1", emptyList<String>())),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf("1.0", emptyList<String>())),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf(-1, emptyList<String>())),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf("-1", emptyList<String>())),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf("==" to listOf("-1.0", emptyList<String>())),
                result = Success(false)
            ),
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult shouldBe testInput.result
    }
})
