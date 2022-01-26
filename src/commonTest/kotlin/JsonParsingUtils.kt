import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun jsonToMap(json: JsonElement): Any? {
    return when (json) {
        // Here even if the primitive is double or int its casted to String because of content return type
//        is JsonPrimitive -> json.content
        is JsonPrimitive -> json.toRealValue()
        is JsonArray -> toList(json)
        is JsonObject -> toMap(json)
    }
}

private fun JsonPrimitive.toRealValue() = if(isString) {
    content
} else {
    content.toIntOrNull() ?: content.toDoubleOrNull() ?: content.toBooleanStrictOrNull()
}

private fun toMap(jsonObject: JsonObject): Map<String?, Any?> {
    val map = mutableMapOf<String?, Any?>()
    jsonObject.keys.forEach { key ->
        val value: JsonElement? = jsonObject[key]
        val parsed = value?.let { jsonToMap(value) }
        map[key] = parsed
    }
    return map
}

private fun toList(array: JsonArray): List<Any?> {
    val list = mutableListOf<Any?>()
    for (i in 0 until array.size) {
        val value = array[i]
        val parsed = jsonToMap(value)
        list.add(parsed)
    }
    return list
}
