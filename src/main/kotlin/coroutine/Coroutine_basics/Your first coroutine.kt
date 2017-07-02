package coroutine.Coroutine_basics
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * Created by vicboma on 23/05/17.
 */
object Main {

    @JvmStatic fun main(args: Array<String>) {
        launch(CommonPool) { // create new coroutine in common thread pool
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
//            Thread.sleep(500L)
            println("World!"+Thread.currentThread()) // print after delay
        }
        println("Hello,"+Thread.currentThread()) // coroutine.Cancellation_and_timeouts.coroutine.Cancellation_and_timeouts.coroutine.Cancellation_and_timeouts.coroutine.Coroutine_basics.coroutine.generator.main function continues while coroutine is delayed
        Thread.sleep(2000L) // block coroutine.Cancellation_and_timeouts.coroutine.Cancellation_and_timeouts.coroutine.Cancellation_and_timeouts.coroutine.Coroutine_basics.coroutine.generator.main thread for 2 seconds to keep JVM alive
    }
}