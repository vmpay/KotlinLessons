package eu.vmpay.kotlinlessons.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by andrew on 15.09.17.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}