package operations.logic

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class EqualsTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Equals operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(expression = mapOf("==" to listOf(1, 1)), result = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("==" to listOf(1, "1")), result = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("==" to listOf(1, 2)), result = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("==" to listOf(null, 2)), result = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("==" to listOf(null, true)), result = JsonLogicResult.Success(false)),
                TestInput(
                    expression = mapOf("==" to listOf(null, false)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf(null, "false")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf(null, "true")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf(false, "false")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf(false, listOf("false"))),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf(false, listOf(false))),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf(emptyList<Any>(), listOf(emptyList<Any>()))),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf(emptyList<Any>(), null)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf(emptyList(), emptyList<Any>())),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf("null", null)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("==" to listOf(true, "true")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("==" to listOf(1, null)), result = JsonLogicResult.Success(false)),
                TestInput(
                    expression = mapOf("==" to listOf(listOf("banana"), null)),
                    result = JsonLogicResult.Success(false)
                ),
            )
        )
    }
})
