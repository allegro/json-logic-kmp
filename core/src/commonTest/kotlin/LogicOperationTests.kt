import io.kotest.core.spec.style.scopes.ContainerScope
import io.kotest.core.spec.style.scopes.FunSpecContainerScope
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

suspend fun FunSpecContainerScope.testWithSuccessResultData(
    logicEngine: JsonLogicEngine,
    data: List<TestInput.Successful>,
    nameFunction: (TestInput.Successful) -> String = {
        "Should apply ${it.data} on ${it.expression} result in ${it.resultValue}"
    }
) = withData(
    nameFn = nameFunction,
    // given
    ts = data,
    test = successResultTestBody(logicEngine)
)

private fun successResultTestBody(logicEngine: JsonLogicEngine): ContainerScope.(TestInput.Successful) -> Unit =
    { (expression, data, result) ->
        // when
        val evaluationResult = logicEngine.evaluate(expression, data)

        // then
        evaluationResult.shouldBeTypeOf<JsonLogicResult.Success>()
        evaluationResult.value shouldBe result
    }

suspend fun FunSpecContainerScope.testWithFailureResultData(
    logicEngine: JsonLogicEngine,
    data: List<TestInput.Unsuccessful>,
    nameFunction: (TestInput.Unsuccessful) -> String = {
        "Should apply ${it.data} on ${it.expression} result in failure"
    }
) = withData(
    nameFn = nameFunction,
    // given
    ts = data,
    test = failureResultTestBody(logicEngine)
)

private fun failureResultTestBody(logicEngine: JsonLogicEngine): ContainerScope.(TestInput.Unsuccessful) -> Unit =
    { (expression, data) ->
        // when
        val evaluationResult = logicEngine.evaluate(expression, data)

        // then
        evaluationResult.shouldBeTypeOf<JsonLogicResult.Failure>()
    }
