package operations.array

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class ReduceTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated reduce expression with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(
                        listOf(1, 5, mapOf("var" to "A")),
                        mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                        9
                    )
                ),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(
                        listOf(1, 5, mapOf("var" to "b")),
                        mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                        9
                    )
                ),
                data = mapOf("b" to "banana"),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(mapOf("var" to "integers"))
                ),
                data = mapOf("integers" to listOf(1, 2, 3, 4)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(
                        mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                        0
                    )
                ),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("reduce" to listOf(0)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("reduce" to emptyList<Any>()),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("reduce" to null),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(listOf(1, 2), null, 4)
                ),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("reduce" to listOf(mapOf("var" to "integers"), 0)),
                data = mapOf("integers" to listOf(1, 2, 3, 4)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(1, 2, 3)
                ),
                result = JsonLogicResult.Success(3)
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(listOf(1, 2), 3, 4)
                ),
                result = JsonLogicResult.Success(3)
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(
                        mapOf("var" to "desserts"),
                        mapOf("+" to listOf(mapOf("var" to "accumulator"), mapOf("var" to "current.qty"))),
                        0
                    )
                ),
                data = mapOf(
                    "desserts" to listOf(
                        mapOf("name" to "apple", "qty" to 1),
                        mapOf("name" to "brownie", "qty" to 2),
                        mapOf("name" to "cupcake", "qty" to 3)
                    )
                ),
                result = JsonLogicResult.Success(6)
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(
                        mapOf("var" to "integers"),
                        mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                        0
                    )
                ),
                data = mapOf("integers" to listOf(1, 2, 3, 4)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(
                        mapOf("var" to "integers"),
                        mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                        1
                    )
                ),
                data = mapOf("integers" to listOf(1, 2, 3, 4)),
                result = JsonLogicResult.Success(24)
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(
                        mapOf("var" to "integers"),
                        mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                        0
                    )
                ),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf(
                    "reduce" to listOf(
                        mapOf("var" to "integers"),
                        mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                        0
                    )
                ),
                data = mapOf("integers" to listOf(1, 2, 3, 4)),
                result = JsonLogicResult.Success(10)
            ),
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult valueShouldBe testInput.result
    }
})
