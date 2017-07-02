package coroutine.example

import coroutine.Coroutine_context_and_dispatchers.log
import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.startCoroutine

/**
 * Created by Administrator on 2017/7/2.
 */
/**
 * 上下文，用来存放我们需要的信息，可以灵活的自定义
 */
class FilePath(val path: String): AbstractCoroutineContextElement(FilePath){
    companion object Key : CoroutineContext.Key<FilePath>
}

fun asyncCalcMd5(path: String, block: suspend () -> Unit) {
    val continuation = object : Continuation<Unit> {
        override val context: CoroutineContext
            get() = FilePath(path)

        override fun resume(value: Unit) {
            log("resume: $value")
        }

        override fun resumeWithException(exception: Throwable) {
            log(exception.toString())
        }
    }
    block.startCoroutine(continuation)
}

fun calcMd5(path: String): String{
    log("calc md5 for $path.")
    //暂时用这个模拟耗时
    Thread.sleep(1000)
    //假设这就是我们计算得到的 MD5 值
    return System.currentTimeMillis().toString()
}