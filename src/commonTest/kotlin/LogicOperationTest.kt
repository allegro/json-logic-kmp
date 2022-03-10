import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

abstract class LogicOperationTest(
    testName: String,
    successResultTestInput: List<TestInput> = emptyList(),
    failureResultTestInput: List<TestInput> = emptyList(),
    nameFunction: (TestInput) -> String = { "Should apply ${it.data} on ${it.expression} result in ${it.resultValue}" }
) : FunSpec({
    context("$testName - success results") {
        withData(
            nameFn = nameFunction,
            // given
            ts = successResultTestInput
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult.shouldBeTypeOf<JsonLogicResult.Success>()
            evaluationResult.value shouldBe result
        }
    }

    context("$testName - failure results") {
        withData(
            nameFn = nameFunction,
            // given
            ts = failureResultTestInput
        ) { (expression, data, _) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult.shouldBeTypeOf<JsonLogicResult.Failure>()
        }
    }
})
