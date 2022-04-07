package operations.string

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class StringUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: StringUnwrapStrategy = object : StringUnwrapStrategy {}

    given("A non-decimal double") {
        val wrappedValue = listOf(2.0)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should be int string") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe "2"
            }
        }
    }

    given("A double") {
        val wrappedValue = listOf(2.51)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should be double string") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe "2.51"
            }
        }
    }
    given("An empty list") {
        val wrappedValue = emptyList<String>()
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should be empty list") {
                unwrapResult shouldBe emptyList()
            }
        }
    }

    given("A boolean value") {
        val wrappedValue = listOf(false)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should be string") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe "false"
            }
        }
    }

    given("A null") {
        val wrappedValue = listOf(null)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should be empty string") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe ""
            }
        }
    }

    given("A nested list items") {
        val wrappedValue = listOf(listOf(listOf("banana", "apple", "pineapple")))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should be separated with commas") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe "banana,apple,pineapple"
            }
        }
    }

    given("A nested lists items") {
        val wrappedValue = listOf(true, listOf(listOf("banana")), listOf(2.0))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should be flattened") {
                unwrapResult shouldHaveSize 3
                unwrapResult shouldBe listOf("true", "banana", "2")
            }
        }
    }
})
