import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CommonJsonLogicEngineTest : BehaviorSpec({
    val logicEngine = CommonJsonLogicEngine()

    given("An invalid expression") {
        val invalidExpression = mapOf("var" to listOf("var", "a"))

        then("causes exception throw") {
            shouldThrow<JsonLogicException> {
                logicEngine.evaluate(invalidExpression, emptyList<Any>())
            }
        }
    }

    given("An empty logic expression") {
        val logicExpression = emptyMap<String, Any?>()
        val data = listOf("apple", "banana")

        `when`("after evaluation") {
            val result = logicEngine.evaluate(logicExpression, data)

            then("returns untouched data") {
                result shouldBe data
            }
        }
    }
})
