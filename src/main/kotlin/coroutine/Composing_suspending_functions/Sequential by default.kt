package coroutine.Composing_suspending_functions

/**
 * Created by luliru on 2017/7/1.
 */
import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}："+Thread.currentThread())
    }
    println("Completed in $time ms，"+Thread.currentThread())
}