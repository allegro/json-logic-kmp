package operations.string

import JsonLogicEngineBuilder
import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithSuccessResultData

class SubstrTest : FunSpec({
    val logicEngine = JsonLogicEngineBuilder().build()

    context("JsonLogic evaluation with Substr operation") {
        testWithSuccessResultData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf("substr" to listOf("jsonlogic", 4)),
                    resultValue = "logic"
                ),
                Successful(
                    expression = mapOf("substr" to listOf("jsonlogic", -5)),
                    resultValue = "logic"
                ),
                Successful(
                    expression = mapOf("substr" to listOf("jsonlogic", 0, 1)),
                    resultValue = "j"
                ),
                Successful(
                    expression = mapOf("substr" to listOf("jsonlogic", -1, 1)),
                    resultValue = "c"
                ),
                Successful(
                    expression = mapOf("substr" to listOf("jsonlogic", 4, 5)),
                    resultValue = "logic"
                ),
                Successful(
                    expression = mapOf("substr" to listOf("jsonlogic", -5, 5)),
                    resultValue = "logic"
                ),
                Successful(
                    expression = mapOf("substr" to listOf("jsonlogic", -5, -2)),
                    resultValue = "log"
                ),
                Successful(
                    expression = mapOf("substr" to listOf("jsonlogic", 1, -5)),
                    resultValue = "son"
                ),
                Successful(
                    expression = mapOf("substr" to listOf("jsonlogic", 1, -5, 8)),
                    resultValue = "son"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(true, 1, -5)),
                    resultValue = ""
                ),
                Successful(
                    expression = mapOf("substr" to listOf(true, 1, 2)),
                    resultValue = "ru"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(true)),
                    resultValue = "true"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(listOf("apple", listOf("banana")))),
                    resultValue = "apple,banana"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true)))),
                    resultValue = "apple,banana,true"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true, listOf(null))))),
                    resultValue = "apple,banana,true,"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(listOf("apple", listOf(null, "banana", listOf(null))))),
                    resultValue = "apple,,banana,"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(133, 1)),
                    resultValue = "33"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(listOf("apple", "banana"), 1)),
                    resultValue = "pple,banana"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(emptyList<String>())),
                    resultValue = ""
                ),
                Successful(
                    expression = mapOf("substr" to listOf("", -3, 15)),
                    resultValue = ""
                ),
                Successful(
                    expression = mapOf("substr" to listOf(2.0, 1)),
                    resultValue = ""
                ),
                Successful(
                    expression = mapOf("substr" to listOf(2.5, 1)),
                    resultValue = ".5"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(2.5)),
                    resultValue = "2.5"
                ),
                Successful(
                    expression = mapOf("substr" to listOf(2.0)),
                    resultValue = "2"
                ),
                Successful(
                    expression = mapOf("substr" to listOf("2.0")),
                    resultValue = "2.0"
                ),
            )
        )
    }
})

