package operations.array.occurrence

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class AllTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with All operation") {
        testWithInputData(
            logicEngine,
            listOf(
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to emptyList<Any>()
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(null)
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to null
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(
                            mapOf(">=" to listOf(mapOf("var" to ""), 1)),
                            mapOf(">=" to listOf(mapOf("var" to ""), 1))
                        )
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf("var" to "integers"), mapOf("==" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to emptyList<Any>()),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf(
                        "items" to listOf(
                            mapOf("qty" to 1, "sku" to "apple"),
                            mapOf("qty" to 2, "sku" to "banana")
                        )
                    ),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf("var" to "items"), mapOf(">" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf(
                        "items" to listOf(
                            mapOf("qty" to 1, "sku" to "apple"),
                            mapOf("qty" to 2, "sku" to "banana")
                        )
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf("var" to "items"), mapOf("<" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf(
                        "items" to listOf(
                            mapOf("qty" to 1, "sku" to "apple"),
                            mapOf("qty" to 2, "sku" to "banana")
                        )
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "all" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf("items" to emptyList<Any>()),
                    resultValue = JsonLogicResult.Success(false)
                )
            )
        )
    }
})
