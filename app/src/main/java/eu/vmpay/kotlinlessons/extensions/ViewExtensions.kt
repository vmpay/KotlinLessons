package eu.vmpay.kotlinlessons.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by andrew on 05.09.17.
 */
val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v) 