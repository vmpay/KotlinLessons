package eu.vmpay.kotlinlessons.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by andrew on 15.09.17.
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)