package eu.vmpay.kotlinlessons.domain.model

/**
 * Created by andrew on 30.08.17.
 */
data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int, val low: Int)