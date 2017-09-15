package eu.vmpay.kotlinlessons.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import eu.vmpay.kotlinlessons.R
import eu.vmpay.kotlinlessons.domain.commands.RequestDayForecastCommand
import eu.vmpay.kotlinlessons.domain.model.Forecast
import eu.vmpay.kotlinlessons.extensions.color
import eu.vmpay.kotlinlessons.extensions.textColor
import eu.vmpay.kotlinlessons.extensions.toDateString
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.text.DateFormat

class DetailActivity : AppCompatActivity() {

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    lateinit var icon: ImageView
    lateinit var weatherDescription: TextView
    lateinit var maxTemperature: TextView
    lateinit var minTemperature: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = intent.getStringExtra(CITY_NAME)

        icon = find(R.id.icon)
        weatherDescription = find(R.id.weatherDescription)
        maxTemperature = find(R.id.maxTemperature)
        minTemperature = find(R.id.minTemperature)


        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread { bindForecast(result) }
        }
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}

