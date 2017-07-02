package coroutine.Coroutine_basics

import kotlinx.coroutines.experimental.*

/**
 * Created by luliru on 2017/6/29.
 */
fun main(args: Array<String>) = runBlocking<Unit> {
    val jobs = List(100_000) { // create a lot of coroutines and list their jobs
        launch(CommonPool) {
            delay(1000L)
            println("."+Thread.currentThread())
        }
    }
    jobs.forEach { it.join() } // wait for all jobs to complete
}