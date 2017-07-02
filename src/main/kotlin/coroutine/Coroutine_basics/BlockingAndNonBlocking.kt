package coroutine.Coroutine_basics

/**
 * Created by luliru on 2017/6/27.
 */
import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking<Unit> { // start coroutine.Cancellation_and_timeouts.coroutine.Cancellation_and_timeouts.coroutine.Cancellation_and_timeouts.coroutine.Coroutine_basics.coroutine.generator.main coroutine
    launch(CommonPool) { // create new coroutine in common thread pool
        delay(1000L)
        println("World!"+Thread.currentThread())
    }
    println("Hello,"+Thread.currentThread()) // coroutine.Cancellation_and_timeouts.coroutine.Cancellation_and_timeouts.coroutine.Cancellation_and_timeouts.coroutine.Coroutine_basics.coroutine.generator.main coroutine continues while child is delayed
    delay(2000L) // non-blocking delay for 2 seconds to keep JVM alive
}