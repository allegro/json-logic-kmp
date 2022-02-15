package operations.logic

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

// TODO extend unit tests cases
class AndTest : FunSpec({
    context("JsonLogic evaluation with And operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(mapOf("and" to listOf(true, true)), null, true),
                TestInput(mapOf("and" to listOf(true, false)), null, false),
                TestInput(mapOf("and" to listOf(false, true)), null, false),
                TestInput(mapOf("and" to listOf(false, false)), null, false),
                TestInput(mapOf("and" to listOf(true, true, true)), null, true),
                TestInput(mapOf("and" to listOf(true, true, false)), null, false),
                TestInput(mapOf("and" to listOf(false)), null, false),
                TestInput(mapOf("and" to listOf(true)), null, true),
                TestInput(mapOf("and" to listOf(1, 3)), null, 3),
                TestInput(mapOf("and" to listOf(3, false)), null, false),
                TestInput(mapOf("and" to listOf(false, 3)), null, false),
                TestInput(mapOf("and" to listOf(emptyList<Any>(), true)), null, emptyList<Any>()),
                TestInput(mapOf("and" to listOf(0, true)), null, 0),
                TestInput(mapOf("and" to listOf("", true)), null, ""),
                TestInput(mapOf("and" to listOf("0", true)), null, true),
                TestInput(mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), true)), null, true),
                TestInput(mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), false)), null, false),
                TestInput(mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("!" to true))), null, false),
                TestInput(mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("<" to listOf(1, 3)))), null, true),
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
