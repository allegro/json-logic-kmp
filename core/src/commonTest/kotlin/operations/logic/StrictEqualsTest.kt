package operations.logic

import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class StrictEqualsTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with StrictEquals operation") {
       testWithInputData(
            logicEngine,
            listOf(
                Successful(expression = mapOf("===" to listOf(1, 1)), resultValue = true),
                Successful(expression = mapOf("===" to listOf(1, "1")), resultValue = false),
                Successful(expression = mapOf("===" to listOf(1, 2)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(null, 2)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(null, true)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(null, false)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(null, "false")), resultValue = false),
                Successful(expression = mapOf("===" to listOf(null, "true")), resultValue = false),
                Successful(expression = mapOf("===" to listOf(false, "false")), resultValue = false),
                Successful(expression = mapOf("===" to listOf(false, listOf("false"))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(false, listOf(false))), resultValue = false),
                Successful(
                    expression = mapOf("===" to listOf(emptyList<Any>(), listOf(emptyList<Any>()))),
                    resultValue = false
                ),
                Successful(expression = mapOf("===" to listOf(emptyList<Any>(), null)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(emptyList(), emptyList<Any>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf("null", null)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(true, "true")), resultValue = false),
                Successful(expression = mapOf("===" to listOf(1, null)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(listOf("banana"), null)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(listOf("banana"), true)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(listOf("banana"), false)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(true, false)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(false, true)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(true, true)), resultValue = true),
                Successful(expression = mapOf("===" to listOf(1, listOf("1"))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(1, listOf(1))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(1, false)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(-1, false)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(0, false)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(0, true)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(1, true)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(-1, true)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(true, true, "false")), resultValue = true),
                Successful(expression = mapOf("===" to listOf(1, 0, 1)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(1)), resultValue = false),
                Successful(expression = mapOf("===" to listOf(true)), resultValue = false),
                Successful(expression = mapOf("===" to true), resultValue = false),
                Successful(expression = mapOf("===" to false), resultValue = false),
                Successful(expression = mapOf("===" to listOf("true")), resultValue = false),
                Successful(expression = mapOf("===" to listOf("banana")), resultValue = false),
                Successful(expression = mapOf("===" to "banana"), resultValue = false),
                Successful(expression = mapOf("===" to listOf(null)), resultValue = false),
                Successful(expression = mapOf("===" to null), resultValue = false),
                Successful(expression = mapOf("===" to ""), resultValue = false),
                Successful(expression = mapOf("===" to "     "), resultValue = false),
                Successful(expression = mapOf("===" to emptyList<Any>()), resultValue = true),
                Successful(expression = mapOf("===" to listOf(emptyList<Any>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf(1, listOf(listOf("1")))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(null, null)), resultValue = true),
                Successful(
                    expression = mapOf("===" to listOf("banana", listOf(listOf("banana")))),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("===" to listOf("banana", listOf("banana", "banana"))),
                    resultValue = false
                ),
                Successful(expression = mapOf("===" to listOf(listOf(null), listOf(null))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(null, listOf(null))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(1, listOf(null))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(0, listOf(null))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(true, listOf(null))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(false, listOf(null))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(-1, listOf(null))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(0.5, listOf(null))), resultValue = false),
                Successful(expression = mapOf("===" to listOf("", "")), resultValue = true),
                Successful(expression = mapOf("===" to listOf("", "    ")), resultValue = false),
                Successful(expression = mapOf("===" to listOf("", emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf("", listOf(""))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(listOf(""), listOf(""))), resultValue = false),
                Successful(expression = mapOf("===" to listOf("", listOf(listOf("")))), resultValue = false),
                Successful(expression = mapOf("===" to listOf(false, emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf(0, emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf("0", emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf("0.0", emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf(1, emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf("1", emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf("1.0", emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf(-1, emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf("-1", emptyList<String>())), resultValue = false),
                Successful(expression = mapOf("===" to listOf("-1.0", emptyList<String>())), resultValue = false),
            )
        )
    }
})
