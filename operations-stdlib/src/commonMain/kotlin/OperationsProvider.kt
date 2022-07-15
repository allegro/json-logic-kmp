import array.Distinct
import array.Find
import array.JoinToString
import array.Size
import array.Sort
import operation.FunctionalLogicOperation
import operation.StandardLogicOperation
import string.Capitalize
import string.IsBlank
import string.Length
import string.Lowercase
import string.Replace
import string.ToArray
import string.Trim
import string.Uppercase

object OperationsProvider {
    val standardOperations: Map<String, StandardLogicOperation> = mutableMapOf(
        // string
        "capitalize" to Capitalize,
        "isBlank" to IsBlank,
        "length" to Length,
        "lowercase" to Lowercase,
        "replace" to Replace,
        "uppercase" to Uppercase,
        "toArray" to ToArray,
        "format" to Format,

        // time
        "currentTime" to CurrentTimeMillis,

        // array
        "size" to Size,
        "sort" to Sort,
        "distinct" to Distinct,
        "joinToString" to JoinToString,

        "drop" to Drop,
        "reverse" to Reverse,
        "trim" to Trim
    )

    val functionalOperations: Map<String, FunctionalLogicOperation> = mutableMapOf(
        "find" to Find,
    )
}
