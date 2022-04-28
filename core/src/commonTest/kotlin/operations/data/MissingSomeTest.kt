package operations.data

import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class MissingSomeTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with MissingSome operation") {
       testWithInputData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("a" to "apple"),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("b" to "banana"),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("c" to "carrot"),
                    resultValue = listOf("a", "b")
                ),
                Successful(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "c" to "carrot"),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "b" to "banana", "c" to "carrot"),
                    resultValue = emptyList<Any>()
                ),
                Successful(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "d" to "durian"),
                    resultValue = listOf("b", "c")
                ),
                Successful(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("d" to "durian", "e" to "eggplant"),
                    resultValue = listOf("a", "b", "c")
                ),
            )
        )
    }
})
