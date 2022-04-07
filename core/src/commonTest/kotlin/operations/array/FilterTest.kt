package operations.array

import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithSuccessResultData

class FilterTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Filter operation") {
       testWithSuccessResultData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf(
                        "filter" to listOf(
                            listOf(1, 2, "banana"),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    resultValue = listOf(2)
                ),
                Successful(
                    expression = mapOf(
                        "filter" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 2)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf("filter" to emptyList<Any>()),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf("filter" to null),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf("filter" to "banana"),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf(
                        "filter" to listOf(
                            listOf(1, 2, 3, 4, 5),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    resultValue = listOf(2, 3, 4, 5)
                ),
                Successful(
                    expression = mapOf(
                        "filter" to listOf(
                            listOf(1, 2, 3, 4, 5),
                            listOf(1, 2, 3, 4, 5),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    resultValue = listOf(1, 2, 3, 4, 5)
                ),
                Successful(
                    expression = mapOf(
                        "filter" to listOf(
                            mapOf(">=" to listOf(mapOf("var" to ""), 2)),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2)),
                        )
                    ),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf(
                        "filter" to listOf(
                            mapOf("var" to "integers"),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = listOf(2, 3, 4, 5)
                ),
                Successful(
                    expression = mapOf(
                        "filter" to listOf(mapOf("var" to "integers"), false)
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf(
                        "filter" to listOf(mapOf("var" to "integers"), true)
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    resultValue = listOf(1, 2, 3)
                ),
                Successful(
                    expression = mapOf(
                        "filter" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("%" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    resultValue = listOf(1, 3, 5)
                ),
            )
        )
    }
})
