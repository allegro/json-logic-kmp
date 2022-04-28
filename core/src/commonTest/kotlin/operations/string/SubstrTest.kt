package operations.string

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class SubstrTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Substr operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", 4)),
                    resultValue = JsonLogicResult.Success("logic")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", -5)),
                    resultValue = JsonLogicResult.Success("logic")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", 0, 1)),
                    resultValue = JsonLogicResult.Success("j")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", -1, 1)),
                    resultValue = JsonLogicResult.Success("c")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", 4, 5)),
                    resultValue = JsonLogicResult.Success("logic")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", -5, 5)),
                    resultValue = JsonLogicResult.Success("logic")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", -5, -2)),
                    resultValue = JsonLogicResult.Success("log")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", 1, -5)),
                    resultValue = JsonLogicResult.Success("son")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", 1, -5, 8)),
                    resultValue = JsonLogicResult.Success("son")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(true, 1, -5)),
                    resultValue = JsonLogicResult.Success("")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(true, 1, 2)),
                    resultValue = JsonLogicResult.Success("ru")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(true)),
                    resultValue = JsonLogicResult.Success("true")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(listOf("apple", listOf("banana")))),
                    resultValue = JsonLogicResult.Success("apple,banana")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true)))),
                    resultValue = JsonLogicResult.Success("apple,banana,true")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true, listOf(null))))),
                    resultValue = JsonLogicResult.Success("apple,banana,true,")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(listOf("apple", listOf(null, "banana", listOf(null))))),
                    resultValue = JsonLogicResult.Success("apple,,banana,")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(133, 1)),
                    resultValue = JsonLogicResult.Success("33")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(listOf("apple", "banana"), 1)),
                    resultValue = JsonLogicResult.Success("pple,banana")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(emptyList<String>())),
                    resultValue = JsonLogicResult.Success("")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("", -3, 15)),
                    resultValue = JsonLogicResult.Success("")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(2.0, 1)),
                    resultValue = JsonLogicResult.Success("")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(2.5, 1)),
                    resultValue = JsonLogicResult.Success(".5")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(2.5)),
                    resultValue = JsonLogicResult.Success("2.5")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(2.0)),
                    resultValue = JsonLogicResult.Success("2")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("2.0")),
                    resultValue = JsonLogicResult.Success("2.0")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(mapOf("var" to "A"), 0)),
                    data = mapOf("A" to "x"),
                    resultValue = JsonLogicResult.Success("x")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(mapOf("var" to "A"), 0)),
                    data = mapOf("A" to ""),
                    resultValue = JsonLogicResult.Success("")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(mapOf("var" to "A"), 0)),
                    data = mapOf("A" to "xx"),
                    resultValue = JsonLogicResult.Success("xx")
                ),
                TestInput(
                    expression = mapOf("substr" to listOf(mapOf("var" to "A"), 0, 0)),
                    data = mapOf("A" to "x"),
                    resultValue = JsonLogicResult.Success("")
                ),
            )
        )
    }
})

