package operations.array

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class FilterTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Filter operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            listOf(1, 2, "banana"),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    result = JsonLogicResult.Success(listOf(2))
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 2)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("filter" to emptyList<Any>()),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("filter" to null),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("filter" to "banana"),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            listOf(1, 2, 3, 4, 5),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    result = JsonLogicResult.Success(listOf(2, 3, 4, 5))
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            listOf(1, 2, 3, 4, 5),
                            listOf(1, 2, 3, 4, 5),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    result = JsonLogicResult.Success(listOf(1, 2, 3, 4, 5))
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            mapOf(">=" to listOf(mapOf("var" to ""), 2)),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2)),
                        )
                    ),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            mapOf("var" to "integers"),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    result = JsonLogicResult.Success(listOf(2, 3, 4, 5))
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(mapOf("var" to "integers"), false)
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(mapOf("var" to "integers"), true)
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    result = JsonLogicResult.Success(listOf(1, 2, 3))
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("%" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    result = JsonLogicResult.Success(listOf(1, 3, 5))
                ),
            )
        )
    }
})
