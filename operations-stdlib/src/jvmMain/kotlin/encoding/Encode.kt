package encoding

import operation.StandardLogicOperation
import string.StringUnwrapStrategy
import java.net.URLEncoder

actual object Encode : StandardLogicOperation, StringUnwrapStrategy {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return  unwrapValueAsString(expression)?.let {  URLEncoder.encode(it, Charsets.UTF_8) }
    }
}
