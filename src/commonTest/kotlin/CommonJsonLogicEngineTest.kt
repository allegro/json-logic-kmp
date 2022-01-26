import kotlin.test.Test

class CommonJsonLogicEngineTest {
    @Test
    fun playground() {
        // [ {"var":["a.q", 9]},{"a":{"b":"c"}},9 ]
//        CommonJsonLogicEngine().evaluate(
//            mapOf("var" to listOf("a.q", 9)),
//            mapOf("a" to mapOf("b" to "c"))
//        )

        // [ {"var":"1.1"}, ["apple",["banana","beer"]], "beer" ],
//        CommonJsonLogicEngine().evaluate(
//            mapOf("var" to "1.1"),
//            listOf("apple", listOf("banana", "beer"))
//        )

        // [{"missing":["a","b"]}, {"a":"apple", "b":"banana"}, []],
//        CommonJsonLogicEngine().evaluate(
//            mapOf("missing" to listOf("a", "b")),
//            mapOf("a" to "apple", "b" to "banana")
//        )

        // ["a", "b"]
        CommonJsonLogicEngine().evaluate(
            mapOf(
                "missing_some" to listOf(1, listOf("a", "b"))
            ),
            mapOf("c" to "carrot")
        )
    }

}
