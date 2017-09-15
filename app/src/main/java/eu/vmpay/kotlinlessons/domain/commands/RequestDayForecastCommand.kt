package eu.vmpay.kotlinlessons.domain.commands

import eu.vmpay.kotlinlessons.domain.datasource.ForecastProvider
import eu.vmpay.kotlinlessons.domain.model.Forecast

/**
 * Created by andrew on 15.09.17.
 */
class RequestDayForecastCommand(
        val id: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}