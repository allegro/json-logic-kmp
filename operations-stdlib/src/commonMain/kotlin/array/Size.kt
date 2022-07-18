package array

import operation.StandardLogicOperation
import utils.asList
import utils.isSingleNullList

object Size : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? = (expression as? List<*>)?.size
}
