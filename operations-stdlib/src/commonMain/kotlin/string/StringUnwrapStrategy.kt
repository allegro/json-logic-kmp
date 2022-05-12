package string

import operation.StandardLogicOperation
import utils.asList


internal interface StringUnwrapStrategy {
    fun unwrapValueAsString(wrappedValue: Any?): String? =
        with(wrappedValue.asList) {
            if (size <= 1) {
                (firstOrNull() as? String)
            } else null
        }
}
