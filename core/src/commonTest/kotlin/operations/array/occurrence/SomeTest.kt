package operations.array.occurrence

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class SomeTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Some operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf(
                        "some" to emptyList<Any>()
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to listOf(null)
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to null
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to listOf(
                            mapOf(">=" to listOf(mapOf("var" to ""), 1)),
                            mapOf(">=" to listOf(mapOf("var" to ""), 1))
                        )
                    ),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to listOf(mapOf("var" to "integers"), mapOf("==" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to emptyList<Any>()),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "some" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
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
                        "some" to listOf(mapOf("var" to "items"), mapOf(">" to listOf(mapOf("var" to "qty"), 1)))
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
                        "some" to listOf(mapOf("var" to "items"), mapOf("<" to listOf(mapOf("var" to "qty"), 1)))
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
                        "some" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf("items" to emptyList<Any>()),
                    resultValue = JsonLogicResult.Success(false)
                )
            )
        )
    }
})
