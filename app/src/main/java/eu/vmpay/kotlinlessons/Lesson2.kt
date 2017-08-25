package eu.vmpay.kotlinlessons

/**
 * Created by andrew on 25.08.17.
 */
class Lesson2 {

    /**----2.1----**/
    data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
        override fun compareTo(other: MyDate): Int =
                (year - other.year) * 365 + (month - other.month) * 30 + dayOfMonth - other.dayOfMonth
    }

    fun compare(date1: MyDate, date2: MyDate) = date1 < date2

    /**----2.2----**/
    class DateRange(val start: MyDate, val endInclusive: MyDate) {
        operator fun contains(myDate: MyDate): Boolean =
                (myDate.year in start.year + 1 until endInclusive.year)
                        || (myDate.year == start.year && myDate.month > start.month)
                        || (myDate.year == start.year && myDate.month == start.month && myDate.dayOfMonth >= start.dayOfMonth)
                        || (myDate.year == endInclusive.year && myDate.month < endInclusive.month)
                        || (myDate.year == endInclusive.year && myDate.month == endInclusive.month && myDate.dayOfMonth <= endInclusive.dayOfMonth)
    }

    fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
        return date in DateRange(first, last)
    }
}