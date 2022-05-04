package operation

import LogicEvaluator

interface FunctionalLogicOperation {
    fun evaluateLogic(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any?
}
