package operations.logic

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class AndTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with And operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf(
                        "and" to listOf(
                            mapOf(
                                "<" to listOf(mapOf("var" to "temp"), 110)
                            ),
                            mapOf("==" to listOf(mapOf("var" to "pie.filling"), "apple"))
                        )
                    ),
                    data = mapOf(
                        "temp" to 100,
                        "pie" to mapOf("filling" to "apple")
                    ),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(true, false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("and" to listOf(true, true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("and" to listOf(false, true)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(false, false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(true, true, true)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(true, true, false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("and" to listOf(false)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("and" to listOf(true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("and" to listOf(1, 3)), resultValue = JsonLogicResult.Success(3)),
                TestInput(expression = mapOf("and" to listOf(3, false)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("and" to listOf(false, 3)), resultValue = JsonLogicResult.Success(false)),
                TestInput(
                    expression = mapOf("and" to listOf(emptyList<Any>(), true)),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(expression = mapOf("and" to listOf(0, true)), resultValue = JsonLogicResult.Success(0)),
                TestInput(expression = mapOf("and" to listOf("", true)), resultValue = JsonLogicResult.Success("")),
                TestInput(expression = mapOf("and" to listOf("0", true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), true)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("!" to true))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("<" to listOf(1, 3)))),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf("0", listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf("grapes"), listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(false, listOf("banana"))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(true, listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(null), listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(null), true)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(null), false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf(null))),
                    resultValue = JsonLogicResult.Success(listOf(null))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf(null), listOf("banana"))),
                    resultValue = JsonLogicResult.Success(listOf("banana"))
                ),
            )
        )
    }
})
