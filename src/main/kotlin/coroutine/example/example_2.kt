package coroutine.example

import coroutine.Coroutine_context_and_dispatchers.log
import java.util.concurrent.Executors
import kotlin.coroutines.experimental.*

/**
 * 异步
 * Created by Administrator on 2017/7/2.
 */
fun main(args: Array<String>) {
    log("before coroutine")
    //启动我们的协程
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

private val executor = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "scheduler")
}



