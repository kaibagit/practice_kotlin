package coroutine.Coroutine_context_and_dispatchers

/**
 * Created by luliru on 2017/7/1.
 */
import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking<Unit> {
    // start a coroutine to process some kind of incoming request
    val request = launch(context) { // use the context of `runBlocking`
        // spawns CPU-intensive child job in CommonPool !!!
        val job = launch(context + CommonPool) {
            log("job: I am a child of the request coroutine, but with a different dispatcher")
            delay(1000)
            log("job: I will not execute this line if my parent request is cancelled")
        }
        job.join() // request completes when its sub-job completes
    }
    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    log("coroutine.Coroutine_basics.coroutine.generator.main: Who has survived request cancellation?")
}