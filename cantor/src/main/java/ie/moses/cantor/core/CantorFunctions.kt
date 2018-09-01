package ie.moses.cantor.core

import android.support.annotation.IntRange
import ie.moses.cantor.util.isNaturalNumber

object Cantor {

    const val MAX_INTEGER = 11584

    fun pair(@IntRange(from = 0, to = MAX_INTEGER.toLong()) a: Int,
             @IntRange(from = 0, to = MAX_INTEGER.toLong()) b: Int): Int {
        if (!a.isNaturalNumber()) throw IllegalArgumentException("$a is not a natural number")
        if (!b.isNaturalNumber()) throw IllegalArgumentException("$b is not a natural number")

        if (a > MAX_INTEGER || b > MAX_INTEGER)
            throw IllegalArgumentException("neither value can exceed 11584")

        //Creating an array of the two inputs for comparison later
        val pair = Pair(a, b)

        //Using Cantors paring function to generate unique number
        val result = (0.5 * (a + b).toDouble() * (a + b + 1).toDouble() + b).toInt()

        /* Calling depair function of the result which allows us to compare the
         results of the depair function with the two inputs of the pair function */
        return if (depair(result) == pair) {
            result //Return the result
        } else {
            throw IllegalStateException("Cantor.depair does not provide valid " +
                    "inverse of Cantor.pair, implementation is broken for $a:$b")
        }
    }

    fun depair(z: Int): Pair<Int, Int> {
        val t = Math.floor((Math.sqrt((8 * z + 1).toDouble()) - 1) / 2).toInt()
        val x = t * (t + 3) / 2 - z
        val y = z - t * (t + 1) / 2
        return Pair(x, y)
    }

}