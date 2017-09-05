package eu.vmpay.kotlinlessons.domain.commands

import eu.vmpay.kotlinlessons.data.ForecastRequest
import eu.vmpay.kotlinlessons.domain.mapper.ForecastDataMapper
import eu.vmpay.kotlinlessons.domain.model.ForecastList

/**
 * Created by andrew on 30.08.17.
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}