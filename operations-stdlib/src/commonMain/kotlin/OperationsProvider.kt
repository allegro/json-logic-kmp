import array.Distinct
import array.Find
import array.JoinToString
import array.Size
import array.Sort
import encoding.Encode
import format.DecimalFormat
import operation.FunctionalLogicOperation
import operation.StandardLogicOperation
import string.Capitalize
import string.compareToDate.CompareToDate
import string.IsBlank
import string.Length
import string.Lowercase
import string.Replace
import string.ToArray
import string.Trim
import string.Uppercase
import string.Match
import string.Split

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
        "decimalFormat" to DecimalFormat,
        "encode" to Encode,
        "match" to Match,
        "compareToDate" to CompareToDate,
        "split" to Split,

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
