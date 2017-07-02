package coroutine.Coroutine_context_and_dispatchers

/**
 * Created by luliru on 2017/7/1.
 */
import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking<Unit> {
    val job = Job() // create a job object to manage our lifecycle
    // now launch ten coroutines for a demo, each working for a different time
    val coroutines = List(10) { i ->
        // they are all children of our job object
        launch(context + job) { // we use the context of coroutine.Coroutine_basics.coroutine.generator.main runBlocking thread, but with our own job object
            delay(i * 200L) // variable delay 0ms, 200ms, 400ms, ... etc
            println("Coroutine $i is done")
        }
    }
    println("Launched ${coroutines.size} coroutines")
    delay(500L) // delay for half a second
    println("Cancelling job!")
    job.cancel() // cancel our job.. !!!
    delay(1000L) // delay for more to see if our coroutines are still working
}