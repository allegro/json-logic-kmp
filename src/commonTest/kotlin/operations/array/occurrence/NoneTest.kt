package operations.array.occurrence

import JsonLogicEngineBuilder
import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithSuccessResultData

class NoneTest : FunSpec({
    val logicEngine = JsonLogicEngineBuilder().build()

    context("JsonLogic evaluation with None operation") {
       testWithSuccessResultData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf(
                        "none" to emptyList<Any>()
                    ),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(null)
                    ),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(
                        "none" to null
                    ),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(
                            mapOf(">=" to listOf(mapOf("var" to ""), 1)),
                            mapOf(">=" to listOf(mapOf("var" to ""), 1))
                        )
                    ),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf("==" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to emptyList<Any>()),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf(
                        "items" to listOf(
                            mapOf("qty" to 1, "sku" to "apple"),
                            mapOf("qty" to 2, "sku" to "banana")
                        )
                    ),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf(">" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf(
                        "items" to listOf(
                            mapOf("qty" to 1, "sku" to "apple"),
                            mapOf("qty" to 2, "sku" to "banana")
                        )
                    ),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf("<" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf(
                        "items" to listOf(
                            mapOf("qty" to 1, "sku" to "apple"),
                            mapOf("qty" to 2, "sku" to "banana")
                        )
                    ),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf("items" to emptyList<Any>()),
                    resultValue = true
                )
            )
        )
    }
})
