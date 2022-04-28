package operations.logic

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class StrictEqualsTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with StrictEquals operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(expression = mapOf("===" to listOf(1, 1)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("===" to listOf(1, "1")), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(1, 2)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(null, 2)), resultValue = JsonLogicResult.Success(false)),
                TestInput(
                    expression = mapOf("===" to listOf(null, true)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(null, false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(null, "false")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(null, "true")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(false, "false")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(false, listOf("false"))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(false, listOf(false))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(emptyList<Any>(), listOf(emptyList<Any>()))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(emptyList<Any>(), null)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(emptyList(), emptyList<Any>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("null", null)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(true, "true")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("===" to listOf(1, null)), resultValue = JsonLogicResult.Success(false)),
                TestInput(
                    expression = mapOf("===" to listOf(listOf("banana"), null)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(listOf("banana"), true)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(listOf("banana"), false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(true, false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(false, true)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("===" to listOf(true, true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("===" to listOf(1, listOf("1"))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(1, listOf(1))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("===" to listOf(1, false)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(-1, false)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(0, false)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(0, true)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(1, true)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(-1, true)), resultValue = JsonLogicResult.Success(false)),
                TestInput(
                    expression = mapOf("===" to listOf(true, true, "false")),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(expression = mapOf("===" to listOf(1, 0, 1)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(1)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(true)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to true), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to false), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf("true")), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf("banana")), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to "banana"), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to listOf(null)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to null), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to ""), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to "     "), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("===" to emptyList<Any>()), resultValue = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("===" to listOf(emptyList<Any>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(1, listOf(listOf("1")))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("===" to listOf(null, null)), resultValue = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("===" to listOf("banana", listOf(listOf("banana")))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("banana", listOf("banana", "banana"))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(listOf(null), listOf(null))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(null, listOf(null))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(1, listOf(null))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(0, listOf(null))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(true, listOf(null))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(false, listOf(null))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(-1, listOf(null))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(0.5, listOf(null))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("===" to listOf("", "")), resultValue = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("===" to listOf("", "    ")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("", emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("", listOf(""))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(listOf(""), listOf(""))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("", listOf(listOf("")))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(false, emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(0, emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("0", emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("0.0", emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(1, emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("1", emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("1.0", emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf(-1, emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("-1", emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("===" to listOf("-1.0", emptyList<String>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
            )
        )
    }
})
