package operations.logic

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class OrTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Or operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(expression = mapOf("or" to listOf(true, true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("or" to listOf(false, true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("or" to listOf(true, false)), resultValue = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("or" to listOf(false, false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("or" to listOf(false, false, true)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("or" to listOf(false, false, false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("or" to listOf(false)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("or" to listOf(true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("or" to listOf(1, 3)), resultValue = JsonLogicResult.Success(1)),
                TestInput(expression = mapOf("or" to listOf(3, false)), resultValue = JsonLogicResult.Success(3)),
                TestInput(expression = mapOf("or" to listOf(false, 3)), resultValue = JsonLogicResult.Success(3)),
                TestInput(
                    expression = mapOf("or" to listOf(emptyList<String>(), true)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(expression = mapOf("or" to listOf(0, true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("or" to listOf("", true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("or" to listOf("0", true)), resultValue = JsonLogicResult.Success("0")),
                TestInput(
                    expression = mapOf("or" to listOf("0", listOf("banana"))),
                    resultValue = JsonLogicResult.Success("0")
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf("grapes"), listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf("grapes"))
                ),
                TestInput(
                    expression = mapOf("or" to listOf(false, listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("or" to listOf(true, listOf("banana"))),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(null), listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf(null))
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(null), true)),
                    resultValue = JsonLogicResult.Success(listOf(null))
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(null), false)),
                    resultValue = JsonLogicResult.Success(listOf(null))
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf(emptyList<String>()))
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null))),
                    resultValue = JsonLogicResult.Success(listOf(emptyList<String>()))
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null), listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf(emptyList<String>()))
                ),
            )
        )
    }
})
