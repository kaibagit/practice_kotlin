package coroutine.Coroutine_context_and_dispatchers

/**
 * Created by luliru on 2017/7/1.
 */
import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking(CoroutineName("coroutine.Coroutine_basics.coroutine.generator.main")) {
    log("Started coroutine.Coroutine_basics.coroutine.generator.main coroutine")
    // run two background value computations
    val v1 = async(CommonPool + CoroutineName("v1coroutine")) {
        log("Computing v1")
        delay(500)
        252
    }
    val v2 = async(CommonPool + CoroutineName("v2coroutine")) {
        log("Computing v2")
        delay(1000)
        6
    }
    log("The answer for v1 / v2 = ${v1.await() / v2.await()}")
}