package operations.array.occurrence

import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class NoneTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with None operation") {
       testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf(
                        "none" to emptyList<Any>()
                    ),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(null)
                    ),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to null
                    ),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(
                            mapOf(">=" to listOf(mapOf("var" to ""), 1)),
                            mapOf(">=" to listOf(mapOf("var" to ""), 1))
                        )
                    ),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf("==" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to emptyList<Any>()),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf(
                        "items" to listOf(
                            mapOf("qty" to 1, "sku" to "apple"),
                            mapOf("qty" to 2, "sku" to "banana")
                        )
                    ),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf(">" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf(
                        "items" to listOf(
                            mapOf("qty" to 1, "sku" to "apple"),
                            mapOf("qty" to 2, "sku" to "banana")
                        )
                    ),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf("<" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf(
                        "items" to listOf(
                            mapOf("qty" to 1, "sku" to "apple"),
                            mapOf("qty" to 2, "sku" to "banana")
                        )
                    ),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf("items" to emptyList<Any>()),
                    result = JsonLogicResult.Success(true)
                )
            )
        )
    }
})
