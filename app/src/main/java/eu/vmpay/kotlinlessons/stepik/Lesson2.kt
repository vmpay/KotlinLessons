package eu.vmpay.kotlinlessons.stepik

/**
 * Created by andrew on 25.08.17.
 */
class Lesson2 {

    /**----2.1----**/
    data class MyDate(var year: Int, var month: Int, var dayOfMonth: Int) : Comparable<MyDate> {
        override fun compareTo(other: MyDate): Int =
                if (this.year == other.year)
                    if (this.month == other.month)
                        this.dayOfMonth - other.dayOfMonth
                    else
                        this.month - other.month
                else
                    this.year - other.year

        operator fun plus(timeInterval: TimeInterval): MyDate {
            when (timeInterval) {
                TimeInterval.YEAR -> year++
                TimeInterval.WEEK -> dayOfMonth += 7
                TimeInterval.DAY -> dayOfMonth++
            }
            return this
        }

        fun addTimeIntervals(myDate: MyDate, year: Int = 0, week: Int = 0, day: Int = 0): MyDate {
            return myDate
                    .addTimeIntervals(TimeInterval.YEAR, year)
                    .addTimeIntervals(TimeInterval.WEEK, week)
                    .addTimeIntervals(TimeInterval.DAY, day)
        }

        private fun addTimeIntervals(timeInterval: TimeInterval, i: Int): MyDate {
            var iteration = i
            while (iteration> 0) {
                this + timeInterval
                iteration--
            }
            return this
        }
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

    /**----2.5----**/
    enum class TimeInterval { DAY, WEEK, YEAR }


    fun task1(today: MyDate): MyDate {
        return today + TimeInterval.YEAR + TimeInterval.WEEK
    }

    fun task2(today: MyDate): MyDate {
        return today.addTimeIntervals(today, 2, 3, 5)
    }

    /**----2.7----**/
    class Invokable {
        var numberOfInvocations: Int = 0
            private set
        operator fun invoke(): Invokable {
            numberOfInvocations++
            return this
        }
    }

    fun invokeTwice(invokable: Invokable) = invokable()()
}


