package operations.data

import operations.LogicOperation
import operations.logic.unwrap.SingleNestedValue
import operations.logic.unwrap.SingleNestedValueUnwrapStrategy
import utils.intOrZero
import utils.secondOrNull

// do testow wrzucic to co w playgroundach
internal object Var : LogicOperation , SingleNestedValueUnwrapStrategy {
    override val key: String = "var"

    override operator fun invoke(expression: Any?, data: Any?): Any? {
        val indexParts = if (expression is List<*>) {
           val value = expression.getProperValue()
            if(value is List<*>) {
                // jakas lista, traktowana w jsie jako null, nie ma po co isc dalej
                return@invoke null
            } else value //tutaj moze byc null, ktory oznacza wszystko

        } else {
            expression
        }?.toString()?.split(".").orEmpty()

        val value = if (indexParts.isNotEmpty()) {
            getIndexedValue(data, indexParts)
        } else {
            data
        }

        return if (shouldUseDefaultValue(value, expression)) {
            (expression as? List<*>)?.secondOrNull()
        } else {
            value
        }
    }
    private fun List<*>.getProperValue(): Any? =
        when (val a = firstOrNull()) {
            is List<*> -> if(a.getProperValue() == null) {
                //jazda dalej
                a
            } else if(a.size > 1) {
                a
            } else {
                a.getProperValue()
            }
            null -> null
            emptyList<Any>() -> null
            "" -> null
            else   -> a
        }

//         fun unwrapSingleNestedValueOrDefault(value: Any?) = value.unwrapSingleNestedValue().let {
//            if (it != value) {
//                SingleNestedValue(it)
//            } else value
//        }
//
//        private fun Any?.unwrapSingleNestedValue(): Any? = when {
//            this is List<*> && this.size == 1 -> this.firstOrNull().unwrapSingleNestedValue()
//            else -> this
//        }
    private fun Any?.wezvalueisprawdz() = if(this is SingleNestedValue) {
        when (this.value) {
            null -> null
            "" -> null
            emptyList<Any>() -> null
            else -> this.value
        }
        } else this

    private fun getIndexedValue(value: Any?, indexParts: List<String>): Any? {
        return when (value) {
            is List<*> -> {
                if (indexParts.size == 1) {
                    value[indexParts.first().intOrZero]
                } else {
                    getRecursive(indexParts, value)
                }
            }
            is Map<*, *> -> {
                val initial = value[indexParts.first()]
                indexParts.drop(1).fold(initial) { acc: Any?, indexPart: String ->
                    (acc as? Map<*, *>)?.get(indexPart)
                }
            }
            else -> value
        }
    }

    private fun shouldUseDefaultValue(value: Any?, expression: Any?) = (value == expression || value == null)
        && expression is List<*>
        && expression.size > 1

    private fun getRecursive(indexes: List<String>, data: List<Any?>): Any? = indexes.firstOrNull()?.apply {
        val indexedData = data.getOrNull(intOrZero)
        return if (indexedData is List<*>) {
            getRecursive(indexes.subList(1, indexes.size), indexedData)
        } else {
            data.getOrNull(intOrZero)
        }
    }
}
