package coroutine.Composing_suspending_functions

import kotlinx.coroutines.experimental.delay

/**
 * Created by luliru on 2017/7/1.
 */


suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    println("doSomethingUsefulOne："+Thread.currentThread())
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    println("doSomethingUsefulTwo："+Thread.currentThread())
    return 29
}