import utils.asList

object Length : StandardLogicOperation {

    override fun invoke(expression: Any?, data: Any?) =
        if (expression.asList.size > 1) null
        else {
            val element = expression.asList.firstOrNull()
            if (element is String) element.toString().length
            else null
        }
}
