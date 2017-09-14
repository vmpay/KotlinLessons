package eu.vmpay.kotlinlessons

import android.app.Application
import eu.vmpay.kotlinlessons.extensions.DelegatesExt

/**
 * Created by andrew on 05.09.17.
 */
class WeatherApp : Application() {
    companion object {
        var instance: WeatherApp by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}