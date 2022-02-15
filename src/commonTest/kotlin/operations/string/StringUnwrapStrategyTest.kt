package operations.string

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class StringUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: StringUnwrapStrategy = object : StringUnwrapStrategy {}

    given("A false value") {
        val wrappedValue = listOf(false)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValues(wrappedValue)
            then("should be null") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe null
            }
        }
    }
})
