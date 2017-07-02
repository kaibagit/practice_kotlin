package coroutine

///**
// * Created by luliru on 2017/7/1.
// */
//import kotlinx.coroutines.experimental.*
//import kotlin.coroutines.experimental.CoroutineContext
//
//var redisIoPool = newFixedThreadPoolContext(10,"redisIoPool")
//var dbIoPool = newFixedThreadPoolContext(10,"redisIoPool")
//
//
//fun coroutine.generator.main(args: Array<String>) = runBlocking<Unit> {
//
//    repeat(10) { i ->
//        var name = "luliru_"+i
//        log("submit ${name}")
//        run(name,redisIoPool,dbIoPool)
//    }
//}
//
//suspend fun run(name: String, redisIoPool: CoroutineContext, dbIoPool: CoroutineContext){
//    log("[${name}] start")
//
//    var job
//}
//
////suspend fun run(name: String, redisIoPool: CoroutineContext, dbIoPool: CoroutineContext){
////    log("[${name}] start")
////
////    var result = async(redisIoPool){
////        getFromRedis(name)
////    }.await()
////
////    log("[${name}] redis result = "+result)
////
////    if(result == null){
////        result = async(dbIoPool){
////            getFromDb(name)
////        }.await()
////
////        log("[${name}] db result = "+result)
////    }
////}
//
//suspend fun getFromRedisFiber(name: String):String?{
//    var job = async(redisIoPool){
//        var result = getFromRedis(name)
//
//    }
//    yield()
//
////    continuation.resume()
//}
//
//fun getFromRedis(name: String):String?{
//    log("[${name}] getFromRedis start")
//    Thread.sleep(1000L)
//    log("[${name}] getFromRedis end")
//    return null
//}
//
//fun getFromDb(name: String):String?{
//    log("[${name}] getFromDb start")
//    Thread.sleep(1000L)
//    log("[${name}] getFromDb end")
//    return "db:"+name
//}
//
//fun start(job:Job,result:String){
//
//}
//
//fun log(msg: String) = println("【${Thread.currentThread().name} $msg")