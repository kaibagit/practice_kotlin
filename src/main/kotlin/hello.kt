import coroutine.Coroutine_context_and_dispatchers.log
import java.util.concurrent.Executors
import kotlin.coroutines.experimental.*

/**
 * Created by luliru on 2017/7/2.
 */


fun main(args: Array<String>) {
    log("before coroutine")
    //启动我们的协程
//    asyncCalcMd5("test.zip") {
//        log("in coroutine. Before suspend.")
//        //暂停我们的线程，并开始执行一段耗时操作
//        val result: String = suspendCoroutine {
//            continuation ->
//            log("in suspend block.")
//            continuation.resume(calcMd5(continuation.context[FilePath]!!.path))
//            log("after resume.")
//        }
//        log("in coroutine. After suspend. result = $result")
//    }
    asyncCalcMd5("test.zip") {
        log("in coroutine. Before suspend.")
        //暂停我们的线程，并开始执行一段耗时操作
        val result: String = suspendCoroutine {continuation ->
            log("in suspend block.")
            executor.submit {
                continuation.resume(calcMd5(continuation.context[FilePath]!!.path))
                log("after resume.")
            }
        }
        log("in coroutine. After suspend. result = $result")
        executor.shutdown()
    }
    log("after coroutine")
}
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

private val executor = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "scheduler")
}