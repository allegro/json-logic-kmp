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
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(true, false)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("and" to listOf(true, true)), result = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("and" to listOf(false, true)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(false, false)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(true, true, true)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(true, true, false)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("and" to listOf(false)), result = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("and" to listOf(true)), result = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("and" to listOf(1, 3)), result = JsonLogicResult.Success(3)),
                TestInput(expression = mapOf("and" to listOf(3, false)), result = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("and" to listOf(false, 3)), result = JsonLogicResult.Success(false)),
                TestInput(
                    expression = mapOf("and" to listOf(emptyList<Any>(), true)),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(expression = mapOf("and" to listOf(0, true)), result = JsonLogicResult.Success(0)),
                TestInput(expression = mapOf("and" to listOf("", true)), result = JsonLogicResult.Success("")),
                TestInput(expression = mapOf("and" to listOf("0", true)), result = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), true)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), false)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("!" to true))),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("<" to listOf(1, 3)))),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf("0", listOf("banana"))),
                    result = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf("grapes"), listOf("banana"))),
                    result = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(false, listOf("banana"))),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(true, listOf("banana"))),
                    result = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(null), listOf("banana"))),
                    result = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(null), true)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(null), false)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf("banana"))),
                    result = JsonLogicResult.Success(listOf("banana"))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf(null))),
                    result = JsonLogicResult.Success(listOf(null))
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf(null), listOf("banana"))),
                    result = JsonLogicResult.Success(listOf("banana"))
                ),
            )
        )
    }
})
