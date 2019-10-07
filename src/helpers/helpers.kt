package helpers

/**
 * negative    - Если не null
 * affirmative - Если null
 * customs     - массив дополнительных параметров проверки
 */
fun <V : Any?> V.nullOverChecker(
    negative: (V) -> Unit,
    affirmative: (V) -> Unit,
    vararg customs: ((V) -> Boolean)? = emptyArray()
) {
    if (this == null) {
        return affirmative(this)
    }

    customs.forEach { custom ->
        custom?.invoke(this)?.let {
            it.matchesValue(true, {
                affirmative(this)
            })
        }
    }

    return negative(this)
}

fun <T> T.matchesValue(
    value: T,
    affirmative: () -> Unit,
    negative: (() -> Unit)? = null
) {
    if (this === value) affirmative() else negative?.invoke()
}

fun String.convertToIntArray() = ArrayList(
    this
        .toCharArray()
        .toList()
        .map {
            it.toInt()
        }
)

fun <T> concatenateArrayLists(vararg arrays: ArrayList<T>): ArrayList<T> {
    val resultArray = arrayListOf<T>()
    arrays.forEach {
        resultArray.addAll(it)
    }

    return resultArray
}

fun <K, V> concatenateMaps(vararg maps: Map<K, V>): Map<K, V> {
    val resultMap = mapOf<K, V>()
    maps.forEach {
        resultMap.plus(it)
    }

    return resultMap
}

infix fun <M : Map<K, V>, K, V> M.getKeyByValue(value: V): K {
    val keys = this.filterValues {
        it == value
    }.keys

    return keys.first()
}

infix fun Byte.shl(i: Int): Byte = this.toInt().shl(i).toByte()
infix fun Byte.shr(i: Int): Byte = this.toInt().shr(i).toByte()
