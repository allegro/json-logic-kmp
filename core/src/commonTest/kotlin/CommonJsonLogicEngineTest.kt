import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class CommonJsonLogicEngineTest : BehaviorSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    given("An expression with unknown operation") {
        val logicExpression = mapOf("unknown" to listOf(1, 2))

        `when`("on evaluation") {
            val result = logicEngine.evaluate(logicExpression, null)

            then("returns missing operation failure result") {
                result shouldBe JsonLogicResult.MissingOperationFailure
            }
        }
    }

    given("An empty logic expression") {
        val logicExpression = emptyMap<String, Any?>()

        `when`("on evaluation") {
            val result = logicEngine.evaluate(logicExpression, null)

            then("returns empty expression failure result") {
                result shouldBe JsonLogicResult.EmptyExpressionFailure
            }
        }
    }

    given("A null result expression") {
        val logicExpression = mapOf("var" to null)

        `when`("on evaluation") {
            val result = logicEngine.evaluate(logicExpression, null)

            then("returns null failure result") {
                result shouldBe JsonLogicResult.NullResultFailure
            }
        }
    }

    given("A not null result expression") {
        val logicExpression = mapOf("var" to "int")
        val data = mapOf("int" to 2)

        `when`("on evaluation") {
            val result = logicEngine.evaluate(logicExpression, data)

            then("returns success result") {
                result.shouldBeTypeOf<JsonLogicResult.Success>()
            }
        }
    }
})
