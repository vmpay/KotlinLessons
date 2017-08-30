package eu.vmpay.kotlinlessons.domain.commands

/**
 * Created by andrew on 30.08.17.
 */
interface Command<out T> {
    fun execute(): T
}