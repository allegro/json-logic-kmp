package operations

import evaluation.LogicEvaluator

typealias StandardLogicOperation = (expression: Any?, data: Any?) -> Any?
typealias FunctionalLogicOperation = (expression: Any?, data: Any?, evaluator: LogicEvaluator) -> Any?
