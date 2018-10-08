package ie.moses.testrecyclergridview.util

import android.content.Context
import android.widget.Toast

internal fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) =
        Toast.makeText(this, message, duration).show()