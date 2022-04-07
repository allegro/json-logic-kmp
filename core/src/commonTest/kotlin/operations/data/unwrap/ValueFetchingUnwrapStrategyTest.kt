package operations.data.unwrap

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ValueFetchingUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: ValueFetchingUnwrapStrategy = object : ValueFetchingUnwrapStrategy {}

    given("An empty list") {
        val wrappedValue = emptyList<Any>()
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapDataKeys(wrappedValue)
            then("should be the same") {
                unwrapResult shouldBe emptyList<Any>()
            }
        }
    }

    given("A nested empty list") {
        val wrappedValue = listOf(emptyList<Any>())
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapDataKeys(wrappedValue)
            then("should be the same") {
                unwrapResult shouldBe null
            }
        }
    }

    given("A nested null") {
        val wrappedValue = listOf(listOf(null))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapDataKeys(wrappedValue)
            then("should be null") {
                unwrapResult shouldBe null
            }
        }
    }

    given("A null") {
        val wrappedValue = null
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapDataKeys(wrappedValue)
            then("should be an empty list") {
                unwrapResult shouldBe emptyList<Any>()
            }
        }
    }

    given("A multiple values list") {
        val wrappedValue = listOf(1, "")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapDataKeys(wrappedValue)
            then("should be list with the first value in string") {
                unwrapResult shouldBe listOf("1")
            }
        }
    }

    given("A multiple values nested list") {
        val wrappedValue = listOf(listOf(1, "banana"))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapDataKeys(wrappedValue)
            then("should be the same") {
                unwrapResult shouldBe null
            }
        }
    }

    given("A single item list") {
        val wrappedValue = listOf(1)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapDataKeys(wrappedValue)
            then("should be the same with item in string") {
                unwrapResult shouldBe listOf("1")
            }
        }
    }

    given("An empty string list") {
        val wrappedValue = listOf("")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapDataKeys(wrappedValue)
            then("should be an empty list") {
                unwrapResult shouldBe listOf()
            }
        }
    }

    given("A nested empty string list") {
        val wrappedValue = listOf(listOf(""))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapDataKeys(wrappedValue)
            then("should be the same") {
                unwrapResult shouldBe null
            }
        }
    }
})
