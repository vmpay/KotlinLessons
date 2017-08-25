package eu.vmpay.kotlinlessons

import java.util.*

/**
 * Created by andrew on 24.08.17.
 */
class Lesson1 {

    fun foo(name: String, number: Int = 0, toUpperCase: Boolean = false) =
            (if (toUpperCase) name.toUpperCase() else name) + number

    fun useFoo() = listOf(
            foo("a"),
            foo("b", number = 1),
            foo("c", toUpperCase = true),
            foo(name = "d", number = 2, toUpperCase = true)
    )

    fun containsEven(collection: Collection<Int>): Boolean = collection.any {
        for (entry in collection) {
            if (entry.rem(2) == 0) return true
        }
        return false
    }

    val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

    fun getPattern(): Regex = """\d{2} $month \d{4}""".toRegex()

    inner class Person(val name: String, val age: Int)

    fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
        val email = client?.personalInfo?.email
        if (email != null && message != null)
            mailer.sendMessage(email, message)
    }

    class Client(val personalInfo: PersonalInfo?)
    class PersonalInfo(val email: String?)
    interface Mailer {
        fun sendMessage(email: String, message: String)
    }

    fun eval(expr: Expr): Int =
            when (expr) {
                is Num -> (expr as Num).value
                is Sum -> eval((expr as Sum).left) + eval((expr as Sum).right)
                else -> throw IllegalArgumentException("Unknown expression")
            }

    interface Expr
    class Num(val value: Int) : Expr
    class Sum(val left: Expr, val right: Expr) : Expr

    fun Int.r(): RationalNumber = RationalNumber(this, 1)
    fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first, this.second)

    data class RationalNumber(val numerator: Int, val denominator: Int)

    fun getList(): List<Int> {
        val arrayList = arrayListOf(1, 5, 2)
        Collections.sort(arrayList) { x, y -> y - x }
        return arrayList
    }

    fun getList2(): List<Int> {
        return arrayListOf(1, 5, 2).sortedDescending()//TODO("return the list sorted in descending order")
    }
}