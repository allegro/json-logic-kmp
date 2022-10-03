package encoding

import operation.StandardLogicOperation
import platform.Foundation.NSCharacterSet
import string.StringUnwrapStrategy
import platform.Foundation.NSString
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters
import platform.Foundation.create

actual object Encode : StandardLogicOperation, StringUnwrapStrategy {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return unwrapValueAsString(expression)?.let {
            NSString.create(string = it)
                .stringByAddingPercentEncodingWithAllowedCharacters(allowedCharacters = NSCharacterSet.alphanumericCharacterSet)
        }
    }
}
