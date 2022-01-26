package expressions

import CommonJsonLogicEngine
import TestInput
import jsonToMap
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlin.test.Test
import kotlin.test.assertEquals

class MissingSomeTest {

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
        [{"missing_some":[1, ["a", "b"]]}, {"a":"apple"}, [] ],
        [{"missing_some":[1, ["a", "b"]]}, {"b":"banana"}, [] ],
        [{"missing_some":[1, ["a", "b"]]}, {"a":"apple", "b":"banana"}, [] ],
        [{"missing_some":[1, ["a", "b"]]}, {"c":"carrot"}, ["a", "b"]],
        [{"missing_some":[2, ["a", "b", "c"]]}, {"a":"apple", "b":"banana"}, [] ],
        [{"missing_some":[2, ["a", "b", "c"]]}, {"a":"apple", "c":"carrot"}, [] ],
        [{"missing_some":[2, ["a", "b", "c"]]}, {"a":"apple", "b":"banana", "c":"carrot"}, [] ],
        [{"missing_some":[2, ["a", "b", "c"]]}, {"a":"apple", "d":"durian"}, ["b", "c"] ],
        [{"missing_some":[2, ["a", "b", "c"]]}, {"d":"durian", "e":"eggplant"}, ["a", "b", "c"] ]
    ]
    """
}
