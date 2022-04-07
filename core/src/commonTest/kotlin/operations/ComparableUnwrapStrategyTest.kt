package operations

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import type.JsonLogicList

class ComparableUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: ComparableUnwrapStrategy = object : ComparableUnwrapStrategy {}

    given("Two numbers") {
        val firstWrappedValue = 2.0
        val secondWrappedValue = -3
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapAsComparable(firstWrappedValue, secondWrappedValue)
            then("should have the same double values") {
                unwrapResult shouldBe listOf(2.0, -3.0)
            }
        }
    }

    given("Number and any string") {
        val firstWrappedValue = 2.0
        val secondWrappedValue = "banana"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapAsComparable(firstWrappedValue, secondWrappedValue)
            then("should be number and null") {
                unwrapResult shouldBe listOf(2.0, null)
            }
        }
    }

    given("Number and number string") {
        val firstWrappedValue = 2.0
        val secondWrappedValue = "-3"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapAsComparable(firstWrappedValue, secondWrappedValue)
            then("should have the same double values") {
                unwrapResult shouldBe listOf(2.0, -3.0)
            }
        }
    }

    given("At least one boolean value") {
        val firstWrappedValue = true
        val secondWrappedValue = "banana"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapAsComparable(firstWrappedValue, secondWrappedValue)
            then("should has the same boolean value") {
                unwrapResult shouldBe listOf(true, null)
            }
        }
    }

    given("Two non-null values of the same type") {
        val firstWrappedValue = "strawberry"
        val secondWrappedValue = "banana"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapAsComparable(firstWrappedValue, secondWrappedValue)
            then("should have the same values") {
                unwrapResult shouldBe listOf("strawberry", "banana")
            }
        }
    }

    given("Two null values") {
        val firstWrappedValue = null
        val secondWrappedValue = null
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapAsComparable(firstWrappedValue, secondWrappedValue)
            then("should have the same values") {
                unwrapResult shouldBe listOf(null, null)
            }
        }
    }

    given("Two values of different types") {
        val firstWrappedValue = "banana"
        val secondWrappedValue = JsonLogicList(listOf("strawberry"))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapAsComparable(firstWrappedValue, secondWrappedValue)
            then("should be null") {
                unwrapResult shouldBe null
            }
        }
    }

    given("Null and any non-null type") {
        val firstWrappedValue = null
        val secondWrappedValue = "banana"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapAsComparable(firstWrappedValue, secondWrappedValue)
            then("should be null") {
                unwrapResult shouldBe null
            }
        }
    }
})
