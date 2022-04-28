package operations

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class InTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with In operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf(
                        "in" to listOf(
                            mapOf("var" to "filling"),
                            listOf("apple", "cherry")
                        )
                    ),
                    data = mapOf("filling" to "apple"),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("Bart", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("Milhouse", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("Spring", "Springfield")),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("i", "team")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("t", listOf("team"))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("1", 133)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf(1, "133")),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("in" to listOf(1, 133)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("t", true)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf(true, "true gold")),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("in" to listOf(null, "banana")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("n", null)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf(null, listOf(null, null))),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("in" to listOf(null, listOf(listOf(null), "banana"))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf(null, listOf("", ""))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("t", "true")),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("j", "apple", "juice")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("in" to listOf("Spring", "Springfield")),
                    resultValue = JsonLogicResult.Success(true)
                ),
            )
        )
    }
})


