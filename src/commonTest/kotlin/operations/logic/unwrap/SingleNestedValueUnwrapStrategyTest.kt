//package operations.logic.unwrap
//
//import io.kotest.core.spec.style.BehaviorSpec
//import io.kotest.matchers.shouldBe
//
//// TODO requires unit tests
//class SingleNestedValueUnwrapStrategyTest : BehaviorSpec({
//    val strategyImplementation: SingleNestedValueUnwrapStrategy = object : SingleNestedValueUnwrapStrategy {}
//
//    given("A string zero") {
//        val wrappedValue = "0"
//        `when`("unwrapped") {
//            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
//            then("should be true") {
//                unwrapResult shouldBe true
//            }
//        }
//    }
//
//})
