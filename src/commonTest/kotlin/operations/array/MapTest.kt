package operations.array

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

//   {"map":[
//  [{"var":"integers"}, [1], [[1]], [null, null], 4, 5, [1, 2]],
//  {"*":[{"var":""},2]}
//]},

//{"map":[
//  [{"var":"integers"}, 1],
//  {"*":[{"var":""},2]}
//]}

//{"map":[
//  [{"var":"integers"}],
//  {"*":[{"var":""},2]}
//]}

// {"map":[
//  [1,2,3,4,5],
//  [1, 2]
//]}

//{"map":[
//  [1,2,3,4,5]
//]}

//{"map":[
//  2,
//  {"*":[{"var":""},2]}
//]}

//{"map":[
//  [2, "banana"],
//  {"*":[{"var":""},2]}
//]}
class MapTest : FunSpec({
    context("JsonLogic evaluation with Map operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("map" to listOf(mapOf("var" to "integers"), mapOf("*" to listOf(mapOf("var" to ""), 2)))),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    result = listOf(2, 4, 6, 8, 10)
                ),
//                TestInput(
//                    expression = mapOf("map" to listOf(1, mapOf("var" to "integers"), mapOf("*" to listOf(mapOf("var" to ""), 2)))),
//                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
//                    result = true
//                ),
//                TestInput(
//                    expression = mapOf("map" to listOf(listOf(null), 1, mapOf("var" to "integers"), mapOf("*" to listOf(mapOf("var" to ""), 2)))),
//                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
//                    result = true
//                ),

            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
