package operations.array

import operations.LogicOperation
import utils.asList
import utils.secondOrNull
import kotlin.collections.Map as MapCollection

internal object Map : LogicOperation, ArrayOperation {
    override val key: String = "map"

    override fun invoke(expression: Any?, data: Any?): Any? =
        expression.asList.takeIf { it.size >= 2 }?.let { args ->
            val evaluatedOperationData = (args.firstOrNull().unwrapOperationData(data) as? List<*>) ?: emptyList<Any>()
            // evaluated data music byc lista bo inaczej return emptyArray - tak mowi js
            evaluatedOperationData.map { argumencik ->
                args.secondOrNull().takeIf { it.isNotEmptyExpression() }?.let {
                    evaluate(it as MapCollection<String, Any>, argumencik)
                } ?: args.secondOrNull()
            }
        }

    private fun Any?.unwrapOperationData(data: Any?): Any? =
        when {
            this is List<*> -> unwrapOperationData(data)
            isNotEmptyExpression() -> evaluate(this as MapCollection<String, Any?>, data)
            else -> this
        }

    private fun Any?.isNotEmptyExpression() = (this as? MapCollection<*, *>)?.let {
        it.isNotEmpty() && it.keys.all { key -> key is String }
    } ?: false
}
// jezeli data to tablica to bierze tylko pierwsze wartosci z kolejnych kolekcji
/*
{"map":[
[{"var":"integers"}, [1], [[1]], [null, null], 4, 5, [1, 2]],
{"*":[{"var":""},2]}
]},

{"map":[
[{"var":"integers"}, 1],
{"*":[{"var":""},2]}
]}

{"map":[
[{"var":"integers"}],
{"*":[{"var":""},2]}
]}
 */
