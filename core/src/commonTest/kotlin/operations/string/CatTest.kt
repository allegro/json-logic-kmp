package operations.string

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class CatTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Cat operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("cat" to "ice"),
                    resultValue = JsonLogicResult.Success("ice")
                ),
                TestInput(
                    expression = mapOf("cat" to emptyList<String>()),
                    resultValue = JsonLogicResult.Success("")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("ice")),
                    resultValue = JsonLogicResult.Success("ice")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("ice", "cream")),
                    resultValue = JsonLogicResult.Success("icecream")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf(1, 2)),
                    resultValue = JsonLogicResult.Success("12")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("Robocop", 2)),
                    resultValue = JsonLogicResult.Success("Robocop2")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("Robocop", 2.0)),
                    resultValue = JsonLogicResult.Success("Robocop2")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf(true, "Robocop")),
                    resultValue = JsonLogicResult.Success("trueRobocop")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("false", "Robocop")),
                    resultValue = JsonLogicResult.Success("falseRobocop")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("we all scream for ", "ice", "cream")),
                    resultValue = JsonLogicResult.Success("we all scream for icecream")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("we all scream for ", listOf("ice", "cream"))),
                    resultValue = JsonLogicResult.Success("we all scream for ice,cream")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("easy as ", listOf(1, 2.0, "3"))),
                    resultValue = JsonLogicResult.Success("easy as 1,2,3")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf(listOf(2.0))),
                    resultValue = JsonLogicResult.Success("2")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf(2.5, 1)),
                    resultValue = JsonLogicResult.Success("2.51")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("2.0")),
                    resultValue = JsonLogicResult.Success("2.0")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("easy as ", listOf(1, 2, "3"))),
                    resultValue = JsonLogicResult.Success("easy as 1,2,3")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("easy as ", listOf(null, listOf(true), "3"))),
                    resultValue = JsonLogicResult.Success("easy as ,true,3")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf(emptyList(), listOf(emptyList(), listOf(emptyList<String>())))),
                    resultValue = JsonLogicResult.Success("")
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("I love ", mapOf("var" to "filling"), " pie")),
                    data = mapOf("filling" to "apple", "temp" to 110),
                    resultValue = JsonLogicResult.Success("I love apple pie")
                ),
            )
        )
    }
})
