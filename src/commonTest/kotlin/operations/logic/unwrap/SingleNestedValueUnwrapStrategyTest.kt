package operations.logic.unwrap

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class SingleNestedValueUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: SingleNestedValueUnwrapStrategy = object : SingleNestedValueUnwrapStrategy {}

    given("A non list item") {
        val wrappedValue = "banana"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapSingleNestedValueOrDefault(wrappedValue)
            then("should be the same") {
                unwrapResult shouldBe "banana"
            }
        }
    }

    given("A single item list") {
        val wrappedValue = listOf("banana")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapSingleNestedValueOrDefault(wrappedValue)
            then("should be SingleNestedValue with that item") {
                unwrapResult shouldBe SingleNestedValue("banana")
            }
        }
    }

    given("A multiple items list") {
        val wrappedValue = listOf("banana", "apple")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapSingleNestedValueOrDefault(wrappedValue)
            then("should be the same") {
                unwrapResult shouldBe listOf("banana", "apple")
            }
        }
    }

    given("A nested empty list") {
        val wrappedValue = listOf(emptyList<Any>())
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapSingleNestedValueOrDefault(wrappedValue)
            then("should be SingleNestedValue with empty list") {
                unwrapResult shouldBe SingleNestedValue(emptyList<Any>())
            }
        }
    }

    given("A double nested null") {
        val wrappedValue = listOf(listOf(null))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapSingleNestedValueOrDefault(wrappedValue)
            then("should be SingleNestedValue with null") {
                unwrapResult shouldBe SingleNestedValue(null)
            }
        }
    }

    given("A double nested number") {
        val wrappedValue = listOf(listOf(1))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapSingleNestedValueOrDefault(wrappedValue)
            then("should be SingleNestedValue with that number") {
                unwrapResult shouldBe SingleNestedValue(1)
            }
        }
    }

    given("A double nested multiple items list") {
        val wrappedValue = listOf(listOf(listOf(1, 2)))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapSingleNestedValueOrDefault(wrappedValue)
            then("should be SingleNestedValue with that multiple items list") {
                unwrapResult shouldBe SingleNestedValue(listOf(1, 2))
            }
        }
    }
})
