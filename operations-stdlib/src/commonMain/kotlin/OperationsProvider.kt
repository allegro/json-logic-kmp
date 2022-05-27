import array.Distinct
import array.Find
import array.Size
import operation.FunctionalLogicOperation
import operation.StandardLogicOperation
import string.Capitalize
import string.IsBlank
import string.Length
import string.Lowercase
import string.Uppercase

object OperationsProvider {
    val standardOperations: Map<String, StandardLogicOperation> = mutableMapOf(
        // string
        "capitalize" to Capitalize,
        "isBlank" to IsBlank,
        "length" to Length,
        "lowercase" to Lowercase,
        "uppercase" to Uppercase,

        // time
        "currentTime" to CurrentTimeMillis,

        // array
        "size" to Size,
        "reverse" to Reverse,
        "distinct" to Distinct
    )

    val functionalOperations: Map<String, FunctionalLogicOperation> = mutableMapOf(
        "find" to Find,
    )
}
