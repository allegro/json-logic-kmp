package encoding

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class EncodeTest: FunSpec({
    val operatorName = "encode"
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation(operatorName, Encode)
        .build()

    withData(
        nameFn = { input -> "Should evaluate encode operation into ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    operatorName to listOf("https://test.pl?id=1234&4321")
                ),
                result = JsonLogicResult.Success("https%3A%2F%2Ftest%2Epl%3Fid%3D1234%264321")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Foo&Bar")
                ),
                result = JsonLogicResult.Success("Foo%26Bar")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Foo#Bar")
                ),
                result = JsonLogicResult.Success("Foo%23Bar")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("kalendář")
                ),
                result = JsonLogicResult.Success("kalend%C3%A1%C5%99")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Доброго ранку")
                ),
                result = JsonLogicResult.Success("%D0%94%D0%BE%D0%B1%D1%80%D0%BE%D0%B3%D0%BE%20%D1%80%D0%B0%D0%BD%D0%BA%D1%83")
            )
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult shouldBe testInput.result
    }
})
