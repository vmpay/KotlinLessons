package eu.vmpay.kotlinlessons

/**
 * Created by andrew on 25.08.17.
 */
class Lesson2 {

    /**----2.1----**/
    data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
        override fun compareTo(other: MyDate): Int =
                if (this.year == other.year)
                    if (this.month == other.month)
                        this.dayOfMonth - other.dayOfMonth
                    else
                        this.month - other.month
                else
                    this.year - other.year
    }

    fun compare(date1: MyDate, date2: MyDate) = date1 < date2

    /**----2.2----**/
    class DateRange(val start: MyDate, val endInclusive: MyDate) {
        operator fun contains(myDate: MyDate): Boolean =
                (start <= myDate && myDate <= endInclusive)

        operator fun MyDate.rangeTo(myDate: MyDate) =
                DateRange(this, myDate)
    }

    fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
        return date in DateRange(first, last)
    }

    /**----2.3----**/
    fun checkInRangeOperator(date: MyDate, first: MyDate, last: MyDate): Boolean {
        return date in first..last
    }
}