package expressions

import CommonJsonLogicEngine
import jsonToMap
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlin.test.Test
import kotlin.test.assertEquals

class VarTest {

    @Test
    fun `should evaluate expressions with the provided data`() {
        // given
        val decodedInput = Json.decodeFromString<JsonElement>(input)
        val data = (jsonToMap(decodedInput) as List<List<Any>>).map {
            TestInput(it.getOrNull(0) as Map<String, Any?>, it.getOrNull(1), it.getOrNull(2))
        }

        // when'n'then
        data.forEach {
            val result = CommonJsonLogicEngine().evaluate(it.expression, it.data)
            assertEquals(expected = it.result, actual = result)
        }
    }

    private val input = """
    [
        [ {"var":["a"]},{"a":1},1 ],
        [ {"var":["b"]},{"a":1},null ],
        [ {"var":["a"]},null,null ],
        [ {"var":"a"},{"a":1},1 ],
        [ {"var":"b"},{"a":1},null ],
        [ {"var":"a"},null,null ],
        [ {"var":["a", 1]},null,1 ],
        [ {"var":["b", 2]},{"a":1},2 ],
        [ {"var":"a.b"},{"a":{"b":"c"}},"c" ],
        [ {"var":"a.q"},{"a":{"b":"c"}},null ],
        [ {"var":["a.q", 9]},{"a":{"b":"c"}},9 ],
        [ {"var":1}, ["apple","banana"], "banana" ],
        [ {"var":"1"}, ["apple","banana"], "banana" ],
        [ {"var":"1.1"}, ["apple",["banana","beer"]], "beer" ],
        [ {"var":"a.b.c"}, null, null ],
        [ {"var":"a.b.c"}, {"a":null}, null ],
        [ {"var":"a.b.c"}, {"a":{"b":null}}, null ],
        [ {"var":""}, 1, 1 ],
        [ {"var":null}, 1, 1 ],
        [ {"var":[]}, 1, 1 ]
    ]
    """

    private val unsupportedInput = """
        [ {"and":[{"<":[{"var":"temp"},110]},{"==":[{"var":"pie.filling"},"apple"]}]},{"temp":100,"pie":{"filling":"apple"}},true ],
        [ {"var":[{"?:":[{"<":[{"var":"temp"},110]},"pie.filling","pie.eta"]}]},{"temp":100,"pie":{"filling":"apple","eta":"60s"}},"apple" ],
        [ {"in":[{"var":"filling"},["apple","cherry"]]},{"filling":"apple"},true ]
    """
}
