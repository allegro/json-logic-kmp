package operations

import LogicEvaluator

//interface LogicOperation {
//    // seems to work as a helper things, these might be omitted and in operation maps use names directly
//    // or extract to some object with const vals with all of the standard operations
//    val key: String
//    val operation
//        get() = key to this
//}

typealias StandardLogicOperation = (expression: Any?, data: Any?) -> Any?
typealias FunctionalLogicOperation = (expression: Any?, data: Any?, evaluator: LogicEvaluator) -> Any?
