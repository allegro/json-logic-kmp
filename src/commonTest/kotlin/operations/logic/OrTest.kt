package operations.logic

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

// TODO extend unit tests cases
class OrTest : FunSpec({
    context("JsonLogic evaluation with only Or operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(mapOf("or" to listOf(true, true)), null, true),
                TestInput(mapOf("or" to listOf(false, true)), null, true),
                TestInput(mapOf("or" to listOf(true, false)), null, true),
                TestInput(mapOf("or" to listOf(false, false)), null, false),
                TestInput(mapOf("or" to listOf(false, false, true)), null, true),
                TestInput(mapOf("or" to listOf(false, false, false)), null, false),
                TestInput(mapOf("or" to listOf(false)), null, false),
                TestInput(mapOf("or" to listOf(true)), null, true),
                TestInput(mapOf("or" to listOf(1, 3)), null, 1),
                TestInput(mapOf("or" to listOf(3, false)), null, 3),
                TestInput(mapOf("or" to listOf(false, 3)), null, 3),
                TestInput(mapOf("or" to listOf(emptyList<String>(), true)), null, true),
                TestInput(mapOf("or" to listOf(0, true)), null, true),
                TestInput(mapOf("or" to listOf("", true)), null, true),
                TestInput(mapOf("or" to listOf("0", true)), null, "0"),
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
