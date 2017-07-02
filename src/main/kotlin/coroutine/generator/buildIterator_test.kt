package coroutine.generator

import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.buildIterator

/**
 * Created by Administrator on 2017/7/2.
 */
fun main(args: Array<String>) = runBlocking<Unit> {
    val ite=buildIterator {
        var num=1
        println("first")
        yield(num)
        num++
        println("second")
        yield(num)
        (3..4).forEach{
            num+=it
            println("yield $num")
            yield(num)
        }
        (5..7).apply {
            println("yield $this")
            yieldAll(this)
        }
    }
    ite.forEach(::println)
}