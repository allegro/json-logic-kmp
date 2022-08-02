package operations.string

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class SubstrTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()
    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("substr" to listOf("hey", -4, -4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -4, -3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -4, -2)),
                result = JsonLogicResult.Success("h")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -4, -1)),
                result = JsonLogicResult.Success("he")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -4, 0)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -4, 1)),
                result = JsonLogicResult.Success("h")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -4, 2)),
                result = JsonLogicResult.Success("he")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -4, 3)),
                result = JsonLogicResult.Success("hey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -4, 4)),
                result = JsonLogicResult.Success("hey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -3, -4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -3, -3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -3, -2)),
                result = JsonLogicResult.Success("h")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -3, -1)),
                result = JsonLogicResult.Success("he")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -3, 0)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -3, 1)),
                result = JsonLogicResult.Success("h")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -3, 2)),
                result = JsonLogicResult.Success("he")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -3, 3)),
                result = JsonLogicResult.Success("hey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -3, 4)),
                result = JsonLogicResult.Success("hey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -2, -4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -2, -3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -2, -2)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -2, -1)),
                result = JsonLogicResult.Success("e")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -2, 0)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -2, 1)),
                result = JsonLogicResult.Success("e")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -2, 2)),
                result = JsonLogicResult.Success("ey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -2, 3)),
                result = JsonLogicResult.Success("ey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -2, 4)),
                result = JsonLogicResult.Success("ey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -1, -4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -1, -3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -1, -2)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -1, -1)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -1, 0)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -1, 1)),
                result = JsonLogicResult.Success("y")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -1, 2)),
                result = JsonLogicResult.Success("y")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -1, 3)),
                result = JsonLogicResult.Success("y")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", -1, 4)),
                result = JsonLogicResult.Success("y")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 0, -4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 0, -3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 0, -2)),
                result = JsonLogicResult.Success("h")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 0, -1)),
                result = JsonLogicResult.Success("he")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 0, 0)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 0, 1)),
                result = JsonLogicResult.Success("h")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 0, 2)),
                result = JsonLogicResult.Success("he")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 0, 3)),
                result = JsonLogicResult.Success("hey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 0, 4)),
                result = JsonLogicResult.Success("hey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 1, -4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 1, -3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 1, -2)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 1, -1)),
                result = JsonLogicResult.Success("e")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 1, 0)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 1, 1)),
                result = JsonLogicResult.Success("e")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 1, 2)),
                result = JsonLogicResult.Success("ey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 1, 3)),
                result = JsonLogicResult.Success("ey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 1, 4)),
                result = JsonLogicResult.Success("ey")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 2, -4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 2, -3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 2, -2)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 2, -1)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 2, 0)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 2, 1)),
                result = JsonLogicResult.Success("y")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 2, 2)),
                result = JsonLogicResult.Success("y")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 2, 3)),
                result = JsonLogicResult.Success("y")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 2, 4)),
                result = JsonLogicResult.Success("y")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 3, -4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 3, -3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 3, -2)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 3, -1)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 3, 0)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 3, 1)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 3, 2)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 3, 3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 3, 4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 4, -4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 4, -3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 4, -2)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 4, -1)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 4, 0)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 4, 1)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 4, 2)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 4, 3)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("hey", 4, 4)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", -2)),
                result = JsonLogicResult.Success("st")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", -5)),
                result = JsonLogicResult.Success("test")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", -10)),
                result = JsonLogicResult.Success("test")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", -4)),
                result = JsonLogicResult.Success("test")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", -5, 2)),
                result = JsonLogicResult.Success("te")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", -2, -1)),
                result = JsonLogicResult.Success("s")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", -2, 1)),
                result = JsonLogicResult.Success("s")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", 1, -1)),
                result = JsonLogicResult.Success("es")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", 1, -2)),
                result = JsonLogicResult.Success("e")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", -5, -5)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", -5, -3)),
                result = JsonLogicResult.Success("t")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", 2, -5)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", 2, 5)),
                result = JsonLogicResult.Success("st")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("test", 5, 7)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("jsonlogic", 4)),
                result = JsonLogicResult.Success("logic")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("jsonlogic", -5)),
                result = JsonLogicResult.Success("logic")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("jsonlogic", 0, 1)),
                result = JsonLogicResult.Success("j")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("jsonlogic", -1, 1)),
                result = JsonLogicResult.Success("c")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("jsonlogic", 4, 5)),
                result = JsonLogicResult.Success("logic")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("jsonlogic", -5, 5)),
                result = JsonLogicResult.Success("logic")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("jsonlogic", -5, -2)),
                result = JsonLogicResult.Success("log")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("jsonlogic", 1, -5)),
                result = JsonLogicResult.Success("son")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("jsonlogic", 1, -5, 8)),
                result = JsonLogicResult.Success("son")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(true, 1, -5)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(true, 1, 2)),
                result = JsonLogicResult.Success("ru")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(true)),
                result = JsonLogicResult.Success("true")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(listOf("apple", listOf("banana")))),
                result = JsonLogicResult.Success("apple,banana")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true)))),
                result = JsonLogicResult.Success("apple,banana,true")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true, listOf(null))))),
                result = JsonLogicResult.Success("apple,banana,true,")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(listOf("apple", listOf(null, "banana", listOf(null))))),
                result = JsonLogicResult.Success("apple,,banana,")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(133, 1)),
                result = JsonLogicResult.Success("33")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(listOf("apple", "banana"), 1)),
                result = JsonLogicResult.Success("pple,banana")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(emptyList<String>())),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("", -3, 15)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(2.0, 1)),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(2.5, 1)),
                result = JsonLogicResult.Success(".5")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(2.5)),
                result = JsonLogicResult.Success("2.5")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(2.0)),
                result = JsonLogicResult.Success("2")
            ),
            TestInput(
                expression = mapOf("substr" to listOf("2.0")),
                result = JsonLogicResult.Success("2.0")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(mapOf("var" to "A"), 0)),
                data = mapOf("A" to "x"),
                result = JsonLogicResult.Success("x")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(mapOf("var" to "A"), 0)),
                data = mapOf("A" to ""),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(mapOf("var" to "A"), 0)),
                data = mapOf("A" to "xx"),
                result = JsonLogicResult.Success("xx")
            ),
            TestInput(
                expression = mapOf("substr" to listOf(mapOf("var" to "A"), 0, 0)),
                data = mapOf("A" to "x"),
                result = JsonLogicResult.Success("")
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
