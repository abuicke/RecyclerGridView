package ie.moses.recyclergridview.util

import android.support.annotation.IntRange

/**
 * @see <a href="https://gist.github.com/ben-howe/db4cd03ef0149725f5a5d71b95a0f72a"/>
 * */
object Cantor {

    fun pair(@IntRange(from = 0) a: Long, @IntRange(from = 0) b: Long): Long {
        if (!a.isNaturalNumber()) throw IllegalArgumentException("$a is not a natural number")
        if (!b.isNaturalNumber()) throw IllegalArgumentException("$b is not a natural number")

        //Creating an array of the two inputs for comparison later
        val input = Pair(a, b)

        //Using Cantors paring function to generate unique number
        val result = (0.5 * (a + b).toDouble() * (a + b + 1).toDouble() + b).toLong()

        /*Calling depair function of the result which allows us to compare
         the results of the depair function with the two inputs of the pair
         function*/
        return if (depair(result) == input) {
            result //Return the result
        } else {
            throw IllegalStateException("Cantor.depair no longer provides " +
                    "valid inverse of Cantor.pair, implementation is broken")
        }
    }

    fun depair(z: Long): Pair<Long, Long> {
        /*Depair function is the reverse of the pairing function. It takes a
         single input and returns the two corespoding values. This allows
         us to perform a check. As well as getting the orignal values*/

        //Cantors depairing function:
        val t = Math.floor((Math.sqrt((8 * z + 1).toDouble()) - 1) / 2).toInt().toLong()
        val x = t * (t + 3) / 2 - z
        val y = z - t * (t + 1) / 2
        return Pair(x, y)
    }

    fun dectohex(dec: Long): String {

        //As the pair value can get quite large im converting it to hex
        return java.lang.Long.toHexString(dec).toUpperCase()
    }

    fun hextodec(hex: String): Long {

        /*To get the two initial values from the hex value it needs to be
         converted back to base 10. The value can then be depaired.*/
        return java.lang.Long.parseLong(hex, 16)
    }
}