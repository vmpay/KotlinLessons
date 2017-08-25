package eu.vmpay.kotlinlessons

/**
 * Created by andrew on 25.08.17.
 */
class Lesson2 {

    data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
        override fun compareTo(other: MyDate): Int =
                (year - other.year) * 365 + (month - other.month) * 30 + dayOfMonth - other.dayOfMonth
    }

    fun compare(date1: MyDate, date2: MyDate) = date1 < date2

}