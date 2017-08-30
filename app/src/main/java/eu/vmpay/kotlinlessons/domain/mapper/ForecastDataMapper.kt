package eu.vmpay.kotlinlessons.domain.mapper

import eu.vmpay.kotlinlessons.domain.model.ForecastList
import eu.vmpay.kotlinlessons.rest.ForecastResponse
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import eu.vmpay.kotlinlessons.domain.model.Forecast as ModelForecast


/**
 * Created by andrew on 30.08.17.
 */
class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResponse.ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<ForecastResponse.Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: ForecastResponse.Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt())
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
}