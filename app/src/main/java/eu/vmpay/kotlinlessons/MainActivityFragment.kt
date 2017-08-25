package eu.vmpay.kotlinlessons

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*
import kotlin.collections.ArrayList

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    private val TAG = "MainActivityFragment"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

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

        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }
}
