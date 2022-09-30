package encoding

import operation.StandardLogicOperation
import string.StringUnwrapStrategy
import platform.Foundation.NSString
import platform.Foundation.NSStringEncoding
import platform.Foundation.stringByAddingPercentEscapesUsingEncoding
import platform.Foundation.create

actual object Encode : StandardLogicOperation, StringUnwrapStrategy {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return unwrapValueAsString(expression)?.let { NSString.create(string = it).stringByAddingPercentEscapesUsingEncoding(NSStringEncoding.MAX_VALUE) }
    }
}
