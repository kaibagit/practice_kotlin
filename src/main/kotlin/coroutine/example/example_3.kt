//package coroutine.example
//
//import coroutine.Coroutine_context_and_dispatchers.log
//import java.util.concurrent.Executors
//import java.util.concurrent.ForkJoinPool
//import java.util.concurrent.ForkJoinWorkerThread
//import java.util.concurrent.TimeUnit
//import kotlin.coroutines.experimental.*
//
///**
// * 异步
// * Created by Administrator on 2017/7/2.
// */
//open class Pool(val pool: ForkJoinPool)
//    : AbstractCoroutineContextElement(ContinuationInterceptor),
//        ContinuationInterceptor {
//    override fun <T> interceptContinuation(continuation: Continuation<T>)
//            : Continuation<T> =
//            PoolContinuation(pool,
//                    //下面这段代码是要查找其他拦截器，并保证能调用它们的拦截方法
//                    continuation.context.fold(continuation, { cont, element ->
//                        if (element != this@Pool && element is ContinuationInterceptor)
//                            element.interceptContinuation(cont) else cont
//                    }))
//}
//private class PoolContinuation<T>(
//        val pool: ForkJoinPool,
//        val continuation: Continuation<T>
//) : Continuation<T> by continuation {
//    override fun resume(value: T) {
//        if (isPoolThread()) continuation.resume(value)
//        else pool.execute { continuation.resume(value) }
//    }
//    override fun resumeWithException(exception: Throwable) {
//        if (isPoolThread()) continuation.resumeWithException(exception)
//        else pool.execute { continuation.resumeWithException(exception) }
//    }
//    fun isPoolThread(): Boolean = (Thread.currentThread() as? ForkJoinWorkerThread)?.pool == pool
//}
//
//object CommonPool : Pool(ForkJoinPool.commonPool())
//
//fun main(args: Array<String>) {
//    log("before coroutine")
//    //启动我们的协程
//    asyncCalcMd5_2("test.zip") {
//        log("in coroutine. Before suspend.")
//        //暂停我们的线程，并开始执行一段耗时操作
//        val result: String = suspendCoroutine { continuation ->
//            log("in suspend block.")
//            continuation.resume(calcMd5(continuation.context[FilePath]!!.path))
//            log("after resume.")
//        }
//    }
//    log("after coroutine")
//    //加这句的原因是防止程序在协程运行完之前停止
//    CommonPool.pool.awaitTermination(10000, TimeUnit.MILLISECONDS)
//}
//
//fun asyncCalcMd5_2(path: String, block: suspend () -> String) {
//    val continuation = object : Continuation<String> {
//        override val context: CoroutineContext
//                //注意这个写法，上下文可以通过 + 来组合使用
//            get() = FilePath(path) + CommonPool
//    }
//    block.startCoroutine(continuation)
//}
//
//
//
