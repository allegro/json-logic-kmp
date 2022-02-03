import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CommonJsonLogicEngineTest : BehaviorSpec({
    val logicEngine = CommonJsonLogicEngine()

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
