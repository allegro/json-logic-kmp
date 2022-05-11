import utils.asList
import utils.isSingleNullList

object Size : StandardLogicOperation {
    override fun invoke(expression: Any?, data: Any?): Any? {
        return when (val firstItem = expression.asList.firstOrNull()) {
            firstItem.isSingleNullList() -> null
            is Map<*, *> -> firstItem.size
            is List<*> -> firstItem.size
            else -> null
        }
    }
}
