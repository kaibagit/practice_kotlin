package coroutine.Cancellation_and_timeouts

/**
 * Created by luliru on 2017/7/1.
 */
import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking<Unit> {
    val job = launch(CommonPool) {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("I'm running finally")
        }
    }
    delay(1300L) // delay a bit
    println("coroutine.Coroutine_basics.coroutine.generator.main: I'm tired of waiting!")
    job.cancel() // cancels the job
    delay(1300L) // delay a bit to ensure it was cancelled indeed
    println("coroutine.Coroutine_basics.coroutine.generator.main: Now I can quit.")
}