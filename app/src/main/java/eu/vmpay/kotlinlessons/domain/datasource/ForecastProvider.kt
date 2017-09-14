package eu.vmpay.kotlinlessons.domain.datasource

import eu.vmpay.kotlinlessons.data.db.ForecastDb
import eu.vmpay.kotlinlessons.data.server.ForecastServer
import eu.vmpay.kotlinlessons.domain.model.ForecastList
import eu.vmpay.kotlinlessons.extensions.firstResult

/**
 * Created by andrew on 14.09.17.
 */
class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList
            = sources.firstResult { requestSource(it, days, zipCode) }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}