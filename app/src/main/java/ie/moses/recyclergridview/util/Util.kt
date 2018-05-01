package ie.moses.recyclergridview.util

fun cantorFunction(k1: Int, k2: Int): Int {
    if (k1 < 0) throw IllegalArgumentException("$k1 is not a natural number")
    if (k2 < 0) throw IllegalArgumentException("$k2 is not a natural number")
    val cantorValue = 0.5 * (k1 + k2) * (k1 + k2 + 1) + k2
    if (!cantorValue.isNaturalNumber()) {
        throw IllegalStateException("impossible output for cantor function, should always be a " +
                "natural number but is instead $cantorValue, implementation must be incorrect")
    }
    return cantorValue.toInt()
}