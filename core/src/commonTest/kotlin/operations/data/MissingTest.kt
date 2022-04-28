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
                    result = JsonLogicResult.Success(listOf("a"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to emptyList<Any>()),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to null),
                    result = JsonLogicResult.Success(listOf("a"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to 0),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to "0"),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to listOf(null)),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to listOf("")),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to emptyList<Any>()),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    result = JsonLogicResult.Success(listOf("a"))
                ),
                TestInput(
                    expression = mapOf("missing" to "a"),
                    data = mapOf("a" to "apple"),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to "apple"),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    data = mapOf("a" to "apple"),
                    result = JsonLogicResult.Success(listOf("b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    result = JsonLogicResult.Success(listOf("a", "b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    result = JsonLogicResult.Success(listOf("a", "b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    result = JsonLogicResult.Success(listOf("a.b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    data = mapOf("a" to "apple"),
                    result = JsonLogicResult.Success(listOf("a.b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    data = mapOf("a" to mapOf("c" to "apple cake")),
                    result = JsonLogicResult.Success(listOf("a.b"))
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    data = mapOf("a" to mapOf("b" to "apple brownie")),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b", "a.c")),
                    data = mapOf("a" to mapOf("b" to "apple brownie")),
                    result = JsonLogicResult.Success(listOf("a.c"))
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
                    result = JsonLogicResult.Success(
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
                    result = JsonLogicResult.Success(listOf("vin"))
                )
            )
        )
    }
})
