package coroutine.Coroutine_context_and_dispatchers

/**
 * Created by luliru on 2017/7/1.
 */
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")