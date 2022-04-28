package operations.array

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class MapTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Map operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            listOf(1, 5, mapOf("var" to "A")),
                            mapOf("+" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    resultValue = JsonLogicResult.Success(listOf(3, 7, null))
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            mapOf("var" to "desserts"),
                            mapOf("var" to "qty")
                        )
                    ),
                    data = mapOf(
                        "desserts" to listOf(
                            mapOf("name" to "apple", "qty" to 1),
                            mapOf("name" to "brownie", "qty" to 2),
                            mapOf("name" to "cupcake", "qty" to 3)
                        )
                    ),
                    resultValue = JsonLogicResult.Success(listOf(1, 2, 3))
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("*" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("*" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = JsonLogicResult.Success(listOf(2, 4, 6, 8, 10))
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            1,
                            mapOf("var" to "integers"),
                            mapOf("*" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            listOf(null),
                            1,
                            mapOf("var" to "integers"),
                            mapOf("*" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = JsonLogicResult.Success(listOf(1))
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            listOf(2, "banana"),
                            mapOf("*" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    resultValue = JsonLogicResult.Success(listOf(4, null))
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            2,
                            mapOf("var" to "integers"),
                            mapOf("*" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("map" to listOf(1, 2, 3, 4, 5)),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("map" to listOf(listOf(1, 2, 3, 4, 5))),
                    resultValue = JsonLogicResult.Success(listOf(null, null, null, null, null))
                ),
                TestInput(
                    expression = mapOf("map" to listOf(listOf(1, 2, 3), listOf(1, 2))),
                    resultValue = JsonLogicResult.Success(listOf(listOf(1, 2), listOf(1, 2), listOf(1, 2)))
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            listOf(mapOf("var" to "integers"), 1),
                            mapOf("*" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = JsonLogicResult.Success(listOf(2, 2))
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("%" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = JsonLogicResult.Success(listOf(1, 0, 1, 0, 1))
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            mapOf("var" to "integers", "var" to "integers"),
                            mapOf("%" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = JsonLogicResult.Success(listOf(1, 0, 1, 0, 1))
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            mapOf("%" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("map" to emptyList<Any>()),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("map" to null),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("map" to "banana"),
                    resultValue = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf(
                        "map" to listOf(
                            mapOf("var" to "integers", "var" to "integers"),
                            mapOf("%" to listOf(mapOf("var" to ""), 2)),
                            mapOf("var" to "integers", "var" to "integers"),
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = JsonLogicResult.Success(listOf(1, 0, 1, 0, 1))
                ),
            )
        )
    }
})
