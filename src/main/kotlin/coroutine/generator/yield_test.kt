package coroutine.generator

/**
 * Created by Administrator on 2017/7/2.
 */
import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.buildSequence

fun main(args: Array<String>) = runBlocking<Unit> {
    val fibonacci = buildSequence {
        yield(1) // first Fibonacci number
        var cur = 1
        var next = 1
        while (true) {
            yield(next) // next Fibonacci number
            val tmp = cur + next
            cur = next
            next = tmp
        }
    }
    for (i in fibonacci){
        println(i)
        if(i > 100) break //大于100就停止循环
    }
}
