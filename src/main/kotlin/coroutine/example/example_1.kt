package coroutine.example

import coroutine.Coroutine_context_and_dispatchers.log
import kotlin.coroutines.experimental.*

/**
 * 同步
 * Created by Administrator on 2017/7/2.
 */
fun main(args: Array<String>) {
    log("before coroutine")
    //启动我们的协程
    asyncCalcMd5("test.zip") {
        log("in coroutine. Before suspend.")
        //暂停我们的线程，并开始执行一段耗时操作
        val result: String = suspendCoroutine { continuation ->
            log("in suspend block.")
            continuation.resume(calcMd5(continuation.context[FilePath]!!.path))
            log("after resume.")
        }
        log("in coroutine. After suspend. result = $result")
    }
    log("after coroutine")
}
