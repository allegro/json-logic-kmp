package operations.numeric

import LogicOperation
import asList

object LessThanOrEqualTo : LogicOperation, ComparingOperation {
    override val key: String = "<="

    override fun invoke(expression: Any?, data: Any?): Any = expression.asList.compareListOfThree { a, b -> a <= b }
}
