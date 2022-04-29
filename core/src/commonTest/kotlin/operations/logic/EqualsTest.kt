package operations.logic

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class EqualsTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()
    val successTrue = JsonLogicResult.Success(true)
    val successFalse = JsonLogicResult.Success(false)

    context("JsonLogic evaluation with Equals operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(expression = mapOf("==" to listOf(1, 1)), result = successTrue),
                TestInput(expression = mapOf("==" to listOf(1, "1")), result = successTrue),
                TestInput(expression = mapOf("==" to listOf(1, 2)), result = successFalse),
                TestInput(expression = mapOf("==" to listOf(null, 2)), result = successFalse),
                TestInput(expression = mapOf("==" to listOf(null, true)), result = successFalse),
                TestInput(
                    expression = mapOf("==" to listOf(null, false)),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(null, "false")),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(null, "true")),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(false, "false")),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(false, listOf("false"))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(false, listOf(false))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(emptyList<Any>(), listOf(emptyList<Any>()))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(emptyList<Any>(), null)),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(emptyList(), emptyList<Any>())),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf("null", null)),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(true, "true")),
                    result = successFalse
                ),
                TestInput(expression = mapOf("==" to listOf(1, null)), result = successFalse),
                TestInput(
                    expression = mapOf("==" to listOf(listOf("banana"), null)),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(listOf("banana"), true)),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(listOf("banana"), false)),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(true, false)),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(false, true)),
                    result = successFalse
                ),
                TestInput(expression = mapOf("==" to listOf(true, true)), result = successTrue),
                TestInput(
                    expression = mapOf("==" to listOf(1, listOf("1"))),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf(1, listOf(listOf("1")))),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf("banana", listOf(listOf("banana")))),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf("banana", listOf("banana", "banana"))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(1, listOf(1))),
                    result = successTrue
                ),
                TestInput(expression = mapOf("==" to listOf(1, false)), result = successFalse),
                TestInput(expression = mapOf("==" to listOf(-1, false)), result = successFalse),
                TestInput(expression = mapOf("==" to listOf(0, false)), result = successTrue),
                TestInput(expression = mapOf("==" to listOf(0, true)), result = successFalse),
                TestInput(expression = mapOf("==" to listOf(1, true)), result = successTrue),
                TestInput(expression = mapOf("==" to listOf(-1, true)), result = successFalse),
                TestInput(
                    expression = mapOf("==" to listOf(true, true, false)),
                    result = successTrue
                ),
                TestInput(expression = mapOf("==" to listOf(1, 0, 1)), result = successFalse),
                TestInput(expression = mapOf("==" to listOf(1)), result = successFalse),
                TestInput(expression = mapOf("==" to listOf(true)), result = successFalse),
                TestInput(expression = mapOf("==" to true), result = successFalse),
                TestInput(expression = mapOf("==" to false), result = successFalse),
                TestInput(expression = mapOf("==" to listOf("true")), result = successFalse),
                TestInput(expression = mapOf("==" to listOf("banana")), result = successFalse),
                TestInput(expression = mapOf("==" to "banana"), result = successFalse),
                TestInput(expression = mapOf("==" to listOf(null)), result = successTrue),
                TestInput(expression = mapOf("==" to null), result = successTrue),
                TestInput(expression = mapOf("==" to ""), result = successFalse),
                TestInput(expression = mapOf("==" to "     "), result = successFalse),
                TestInput(expression = mapOf("==" to emptyList<Any>()), result = successTrue),
                TestInput(
                    expression = mapOf("==" to listOf(emptyList<Any>())),
                    result = successFalse
                ),
                TestInput(expression = mapOf("==" to listOf(null, null)), result = successTrue),
                TestInput(
                    expression = mapOf("==" to listOf(listOf(null), listOf(null))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(null, listOf(null))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(1, listOf(null))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(0, listOf(null))),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf(true, listOf(null))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(false, listOf(null))),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf(listOf(false), listOf(false))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(-1, listOf(null))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(0.5, listOf(null))),
                    result = successFalse
                ),
                TestInput(expression = mapOf("==" to listOf("", "")), result = successTrue),
                TestInput(expression = mapOf("==" to listOf("", "    ")), result = successFalse),
                TestInput(
                    expression = mapOf("==" to listOf("", listOf(""))),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf(listOf(""), listOf(""))),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf("", listOf(listOf("")))),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf("", emptyList<String>())),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf(false, emptyList<String>())),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf(0, emptyList<String>())),
                    result = successTrue
                ),
                TestInput(
                    expression = mapOf("==" to listOf("0", emptyList<String>())),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf("0.0", emptyList<String>())),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(1, emptyList<String>())),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf("1", emptyList<String>())),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf("1.0", emptyList<String>())),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf(-1, emptyList<String>())),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf("-1", emptyList<String>())),
                    result = successFalse
                ),
                TestInput(
                    expression = mapOf("==" to listOf("-1.0", emptyList<String>())),
                    result = successFalse
                ),
            )
        )
    }
})
