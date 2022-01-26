package expressions

import CommonJsonLogicEngine
import TestInput
import jsonToMap
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlin.test.Test
import kotlin.test.assertEquals

class MissingTest {

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
        [{"missing":[]}, null, []],
        [{"missing":["a"]}, null, ["a"]],
        [{"missing":"a"}, null, ["a"]],
        [{"missing":"a"}, {"a":"apple"}, []],
        [{"missing":["a"]}, {"a":"apple"}, []],
        [{"missing":["a","b"]}, {"a":"apple"}, ["b"]],
        [{"missing":["a","b"]}, {"b":"banana"}, ["a"]],
        [{"missing":["a","b"]}, {"a":"apple", "b":"banana"}, []],
        [{"missing":["a","b"]}, {}, ["a","b"]],
        [{"missing":["a","b"]}, null, ["a","b"]],
        [{"missing":["a.b"]}, null, ["a.b"]],
        [{"missing":["a.b"]}, {"a":"apple"}, ["a.b"]],
        [{"missing":["a.b"]}, {"a":{"c":"apple cake"}}, ["a.b"]],
        [{"missing":["a.b"]}, {"a":{"b":"apple brownie"}}, []],
        [{"missing":["a.b", "a.c"]}, {"a":{"b":"apple brownie"}}, ["a.c"]]
    ]
    """
}
