package operations.logic

import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class OrTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Or operation") {
       testWithInputData(
            logicEngine,
            listOf(
                Successful(expression = mapOf("or" to listOf(true, true)), resultValue = true),
                Successful(expression = mapOf("or" to listOf(false, true)), resultValue = true),
                Successful(expression = mapOf("or" to listOf(true, false)), resultValue = true),
                Successful(expression = mapOf("or" to listOf(false, false)), resultValue = false),
                Successful(expression = mapOf("or" to listOf(false, false, true)), resultValue = true),
                Successful(expression = mapOf("or" to listOf(false, false, false)), resultValue = false),
                Successful(expression = mapOf("or" to listOf(false)), resultValue = false),
                Successful(expression = mapOf("or" to listOf(true)), resultValue = true),
                Successful(expression = mapOf("or" to listOf(1, 3)), resultValue = 1),
                Successful(expression = mapOf("or" to listOf(3, false)), resultValue = 3),
                Successful(expression = mapOf("or" to listOf(false, 3)), resultValue = 3),
                Successful(expression = mapOf("or" to listOf(emptyList<String>(), true)), resultValue = true),
                Successful(expression = mapOf("or" to listOf(0, true)), resultValue = true),
                Successful(expression = mapOf("or" to listOf("", true)), resultValue = true),
                Successful(expression = mapOf("or" to listOf("0", true)), resultValue = "0"),
                Successful(
                    expression = mapOf("or" to listOf("0", listOf("banana"))),
                    resultValue = "0"
                ),
                Successful(
                    expression = mapOf("or" to listOf(listOf("grapes"), listOf("banana"))),
                    resultValue = listOf("grapes")
                ),
                Successful(
                    expression = mapOf("or" to listOf(false, listOf("banana"))),
                    resultValue = listOf("banana")
                ),
                Successful(
                    expression = mapOf("or" to listOf(true, listOf("banana"))),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("or" to listOf(listOf(null), listOf("banana"))),
                    resultValue = listOf(null)
                ),
                Successful(expression = mapOf("or" to listOf(listOf(null), true)), resultValue = listOf(null)),
                Successful(expression = mapOf("or" to listOf(listOf(null), false)), resultValue = listOf(null)),
                Successful(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf("banana"))),
                    resultValue = listOf(emptyList<String>())
                ),
                Successful(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null))),
                    resultValue = listOf(emptyList<String>())
                ),
                Successful(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null), listOf("banana"))),
                    resultValue = listOf(emptyList<String>())
                ),
            )
        )
    }
})
