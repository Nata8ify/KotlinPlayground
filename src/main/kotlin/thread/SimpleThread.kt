package thread

import java.util.concurrent.Executor
import java.util.concurrent.Executors

fun main(srg: Array<String>){
    val stopwatch : Runnable = Runnable {
        var i = 0
        while (true){
            Thread.sleep(1000)
            println("Stopwatch.. ${++i}")
        }
    }

    val timer60 : Runnable = Runnable {
        var i  = 60
        while (true){
            Thread.sleep(1000)
            println("Timer.. ${--i}")
            if(i == 0){break}
        }
    }

    val fibo : Runnable = Runnable {
        var i1st = 0
        var i2nd = 1
        println("Fibonacci.. $i1st")
        println("Fibonacci.. $i2nd")
        while (true){
            var iCurrent = i1st+i2nd
            Thread.sleep(3000)
            println("Fibonacci.. $iCurrent")
            i2nd = i1st + i2nd
            i1st = i2nd - i1st
        }
    }

    val helloWolrd : Runnable = Runnable {
        while (true){
            Thread.sleep(5000)
            println("Hello, World")
        }
    }
    val exct : Executor = Executors.newFixedThreadPool(2)
    exct.execute(stopwatch)
    exct.execute(timer60)
    exct.execute(fibo)
    exct.execute(helloWolrd)
    // 1 1 2 3 5 8 13 ... i
}