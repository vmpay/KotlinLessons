package eu.vmpay.kotlinlessons.domain.datasource

import eu.vmpay.kotlinlessons.domain.model.ForecastList

/**
 * Created by andrew on 14.09.17.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}