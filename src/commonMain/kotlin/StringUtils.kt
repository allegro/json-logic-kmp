import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal val String.intValue: Int
    get() = doubleValue.toInt()

internal val String.doubleValue: Double
    get() = toDoubleOrNull() ?: 0.0
