package eu.vmpay.kotlinlessons.domain.commands

import eu.vmpay.kotlinlessons.domain.datasource.ForecastProvider
import eu.vmpay.kotlinlessons.domain.model.ForecastList

/**
 * Created by andrew on 30.08.17.
 */
class RequestForecastCommand(
        private val zipCode: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList = forecastProvider.requestByZipCode(zipCode, DAYS)
}