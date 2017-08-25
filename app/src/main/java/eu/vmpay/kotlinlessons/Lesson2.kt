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
    class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
        override fun iterator() = object : Iterator<MyDate> {
            private var current = start
            override fun hasNext() = current <= endInclusive
            override fun next() = if (hasNext()) {
                val tmp = current
//                current = current.nextDay()
                tmp
            } else throw NoSuchElementException()
        }

        operator fun contains(myDate: MyDate): Boolean =
                (start <= myDate && myDate <= endInclusive)

        operator fun MyDate.rangeTo(myDate: MyDate) =
                DateRange(this, myDate)

        /**----2.4----**/
        fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
            for (date in firstDate..secondDate) {
                handler(date)
            }
        }
    }

    fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
        return date in DateRange(first, last)
    }

    /**----2.3----**/
    fun checkInRangeOperator(date: MyDate, first: MyDate, last: MyDate): Boolean {
        return date in first..last
    }
}


