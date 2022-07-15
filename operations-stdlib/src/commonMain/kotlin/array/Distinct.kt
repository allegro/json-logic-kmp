package array

import operation.StandardLogicOperation
import utils.asList
import utils.isSingleNullList

object Distinct : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? = (expression as? List<*>)?.distinct()
}
