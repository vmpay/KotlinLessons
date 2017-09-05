package eu.vmpay.kotlinlessons.activities.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import eu.vmpay.kotlinlessons.R
import eu.vmpay.kotlinlessons.adapters.ForecastListAdapter
import eu.vmpay.kotlinlessons.domain.commands.RequestForecastCommand
import eu.vmpay.kotlinlessons.domain.model.Forecast
import eu.vmpay.kotlinlessons.stepik.Lesson1
import eu.vmpay.kotlinlessons.stepik.Lesson2
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    private val TAG = "MainActivityFragment"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        val lesson1 = Lesson1()
        Log.d(TAG, lesson1.useFoo().toString())
        val collection = ArrayList<Int>()
        collection.add(1)
        collection.add(3)
        collection.add(5)
        Log.d(TAG, lesson1.containsEven(collection).toString())
        Log.d(TAG, lesson1.getPattern().containsMatchIn("12 NOV 1992").toString())
        Log.d(TAG, lesson1.getList().toString())

        val lesson2 = Lesson2()
        Log.d(TAG, lesson2.compare(Lesson2.MyDate(1992, 11, 11), Lesson2.MyDate(1992, 11, 12)).toString())
        Log.d(TAG, lesson2.checkInRange(Lesson2.MyDate(1993, 11, 11), Lesson2.MyDate(1991, 12, 1), Lesson2.MyDate(1993, 1, 1)).toString())
        Log.d(TAG, lesson2.checkInRangeOperator(Lesson2.MyDate(1993, 11, 11), Lesson2.MyDate(1991, 12, 1), Lesson2.MyDate(1993, 1, 1)).toString())
        Log.d(TAG, lesson2.task1(Lesson2.MyDate(1991, 1, 1)).toString())
        Log.d(TAG, lesson2.task2(Lesson2.MyDate(1991, 1, 1)).toString())


        val tvHello = rootView.find<TextView>(R.id.tvHello)
        tvHello.text = "Hello, Kotlin!"
//        val forecastList : RecyclerView = rootView.findViewById(R.id.forecast_list) as RecyclerView
//        val forecastList = rootView.find<RecyclerView>(R.id.forecast_list)
        val forecastList: RecyclerView = rootView.find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(activity)
//        forecastList.adapter = ForecastListAdapter(items)
        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result,
                        object : ForecastListAdapter.OnItemClickListener {
                            override fun invoke(forecast: Forecast) {
                                activity.toast(forecast.date)
                            }
                        })
            }
        }
        return rootView
    }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )
}
