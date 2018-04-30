package ie.moses.recyclergridview.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.Toast

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Double.roundUp(): Int {
    return Math.ceil(this).toInt()
}