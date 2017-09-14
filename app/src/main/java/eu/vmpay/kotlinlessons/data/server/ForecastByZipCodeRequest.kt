package eu.vmpay.kotlinlessons.data.server

import com.google.gson.Gson
import java.net.URL

/**
 * Created by andrew on 28.08.17.
 */
class ForecastByZipCodeRequest(private val zipCode: Long, val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val DOMAIN_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "${DOMAIN_URL}&APPID=${APP_ID}&q="
    }

    fun execute(): ForecastResponse.ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return gson.fromJson(forecastJsonStr, ForecastResponse.ForecastResult::class.java)
    }
}