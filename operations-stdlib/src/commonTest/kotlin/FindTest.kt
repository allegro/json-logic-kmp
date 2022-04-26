import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class FindTest : BehaviorSpec({
    val engine = JsonLogicEngine.Builder().addFunctionalOperation("find", Find).build()

    given("A list") {
        val wrappedValue = listOf("banana", 1)
        `when`("checked") {
            val unwrapResult = 2
            then("should not be treated as an expression") {
//                unwrapResult shouldBe false
                val expression = mapOf("find" to listOf(listOf(-1, 1, 2, 3), mapOf(">" to listOf(mapOf("var" to ""), 0))))
                engine.evaluate(expression, null)

            }
        }
    }

//    @Test
//    private fun `al`() {
//    }

})
