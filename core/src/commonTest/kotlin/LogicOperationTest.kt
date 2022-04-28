import io.kotest.core.spec.style.scopes.ContainerScope
import io.kotest.core.spec.style.scopes.FunSpecContainerScope
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

suspend fun FunSpecContainerScope.testWithInputData(
    logicEngine: JsonLogicEngine,
    data: List<TestInput>,
    nameFunction: (TestInput) -> String = {
        "Should apply ${it.data} on ${it.expression} result in ${it.result}"
    }
) = withData(
    nameFn = nameFunction,
    // given
    ts = data,
    test = testBody(logicEngine)
)

private fun testBody(logicEngine: JsonLogicEngine): ContainerScope.(TestInput) -> Unit = { (expression, data, result) ->
    // when
    val evaluationResult = logicEngine.evaluate(expression, data)

    // then
    evaluationResult valueShouldBe result
}

private infix fun JsonLogicResult.valueShouldBe(other: JsonLogicResult) {
    if(this is JsonLogicResult.Success && other is JsonLogicResult.Success) {
        value shouldBe other.value
    } else {
        this shouldBe other
    }
}
