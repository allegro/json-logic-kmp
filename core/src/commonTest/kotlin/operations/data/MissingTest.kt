package operations.data

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class MissingTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Missing operation") {
        testWithInputData(
            logicEngine,
            listOf(
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to ""),
                    resultValue = JsonLogicResult.Success(listOf("a"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to emptyList<Any>()),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to null),
                    resultValue = JsonLogicResult.Success(listOf("a"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to 0),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to "0"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to listOf(null)),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to listOf("")),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to emptyList<Any>()),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    resultValue = JsonLogicResult.Success(listOf("a"))
                ),
                TestInput(
                    expression = mapOf("missing" to "a"),
                    data = mapOf("a" to "apple"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to "apple"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    data = mapOf("a" to "apple"),
                    resultValue = JsonLogicResult.Success(listOf("b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    resultValue = JsonLogicResult.Success(listOf("a", "b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    resultValue = JsonLogicResult.Success(listOf("a", "b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    resultValue = JsonLogicResult.Success(listOf("a.b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    data = mapOf("a" to "apple"),
                    resultValue = JsonLogicResult.Success(listOf("a.b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    data = mapOf("a" to mapOf("c" to "apple cake")),
                    resultValue = JsonLogicResult.Success(listOf("a.b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    data = mapOf("a" to mapOf("b" to "apple brownie")),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b", "a.c")),
                    data = mapOf("a" to mapOf("b" to "apple brownie")),
                    resultValue = JsonLogicResult.Success(listOf("a.c"))
                ),
                TestInput(
                    expression = mapOf(
                        "missing" to mapOf(
                            "merge" to listOf(
                                "vin", mapOf(
                                    "if" to listOf(
                                        mapOf("var" to "financing"), listOf("apr"), emptyList<Any>()
                                    )
                                )
                            )
                        )
                    ),
                    data = mapOf("financing" to true),
                    resultValue = JsonLogicResult.Success(
                        listOf(
                            "vin",
                            "apr"
                        )
                    )
                ),
                TestInput(
                    expression = mapOf(
                        "missing" to mapOf(
                            "merge" to listOf(
                                "vin", mapOf(
                                    "if" to listOf(
                                        mapOf("var" to "financing"), listOf("apr"), emptyList<Any>()
                                    )
                                )
                            )
                        )
                    ),
                    data = mapOf("financing" to false),
                    resultValue = JsonLogicResult.Success(listOf("vin"))
                )
            )
        )
    }
})
