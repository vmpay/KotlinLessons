package eu.vmpay.kotlinlessons.data.server

import eu.vmpay.kotlinlessons.data.db.ForecastDb
import eu.vmpay.kotlinlessons.domain.datasource.ForecastDataSource
import eu.vmpay.kotlinlessons.domain.model.ForecastList

/**
 * Created by andrew on 14.09.17.
 */
class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}