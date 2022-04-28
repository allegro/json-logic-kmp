package operations.string

import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class CatTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Cat operation") {
       testWithInputData(
            logicEngine,listOf(
                Successful(
                    expression = mapOf("cat" to "ice"),
                    resultValue = "ice"
                ),
                Successful(
                    expression = mapOf("cat" to emptyList<String>()),
                    resultValue = ""
                ),
                Successful(
                    expression = mapOf("cat" to listOf("ice")),
                    resultValue = "ice"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("ice", "cream")),
                    resultValue = "icecream"
                ),
                Successful(
                    expression = mapOf("cat" to listOf(1, 2)),
                    resultValue = "12"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("Robocop", 2)),
                    resultValue = "Robocop2"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("Robocop", 2.0)),
                    resultValue = "Robocop2"
                ),
                Successful(
                    expression = mapOf("cat" to listOf(true, "Robocop")),
                    resultValue = "trueRobocop"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("false", "Robocop")),
                    resultValue = "falseRobocop"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("we all scream for ", "ice", "cream")),
                    resultValue = "we all scream for icecream"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("we all scream for ", listOf("ice", "cream"))),
                    resultValue = "we all scream for ice,cream"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("easy as ", listOf(1, 2.0, "3"))),
                    resultValue = "easy as 1,2,3"
                ),
                Successful(
                    expression = mapOf("cat" to listOf(listOf(2.0))),
                    resultValue = "2"
                ),
                Successful(
                    expression = mapOf("cat" to listOf(2.5, 1)),
                    resultValue = "2.51"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("2.0")),
                    resultValue = "2.0"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("easy as ", listOf(1, 2, "3"))),
                    resultValue = "easy as 1,2,3"
                ),
                Successful(
                    expression = mapOf("cat" to listOf("easy as ", listOf(null, listOf(true), "3"))),
                    resultValue = "easy as ,true,3"
                ),
                Successful(
                    expression = mapOf("cat" to listOf(emptyList(), listOf(emptyList(), listOf(emptyList<String>())))),
                    resultValue = ""
                ),
                Successful(
                    expression = mapOf("cat" to listOf("I love ", mapOf("var" to "filling"), " pie")),
                    data = mapOf("filling" to "apple", "temp" to 110),
                    resultValue = "I love apple pie"
                ),
            ))
    }
})
