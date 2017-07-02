package coroutine.Composing_suspending_functions

/**
 * Created by luliru on 2017/7/1.
 */
import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis

// The result type of asyncSomethingUsefulOne is Deferred<Int>
fun asyncSomethingUsefulOne() = async(CommonPool) {
    doSomethingUsefulOne()
}

// The result type of asyncSomethingUsefulTwo is Deferred<Int>
fun asyncSomethingUsefulTwo() = async(CommonPool)  {
    doSomethingUsefulTwo()
}

// note, that we don't have `runBlocking` to the right of `coroutine.Coroutine_basics.coroutine.generator.main` in this example
fun main(args: Array<String>) {
    val time = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val one = asyncSomethingUsefulOne()
        val two = asyncSomethingUsefulTwo()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the coroutine.Coroutine_basics.coroutine.generator.main thread while waiting for the result
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}