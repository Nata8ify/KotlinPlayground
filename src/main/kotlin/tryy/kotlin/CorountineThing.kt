package tryy.kotlin

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import retrofit2.Retrofit
import tryy.kotlin.web.Raw
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    println("-- Start Main --")

    // Run Blocking... (Coroutine)
    /* runBlocking {
         delay(1000L)
         println("Run Blocking!")
     }*/

    // Thread
    /* thread {
         Thread.sleep(1000L)
         println("Run on Thread")
     }*/

    // Launch! (Coroutine)
    /*launch  {
        delay(1000L)
        println("Run Blocking!")
    }*/

//    performanceTestRunner()
//    asyncTestRunner()
    awaitResponseTestRunner()
    println("-- Post Start --")
    Thread.sleep(2000L)
    println("-- End Main --")
}

fun coroutineRun(): Unit {
    val initTime: Long = System.currentTimeMillis()
    launch {
        repeat(100, {
            println("Async Scope :$it")
            delay(10L)
        })
        val finishTime: Long = System.currentTimeMillis()
        println("Corountine time usage : " + finishTime.minus(initTime))
    }
    /*repeat(100, {
        println("Function Scope :$it")
        Thread.sleep(10L)
    })*/
}

fun threadRun(): Unit {
    val initTime: Long = System.currentTimeMillis()
    thread {
        for (i in 1..100) {
            println("Thread Scope :$i")
            Thread.sleep(10L)
        }
        val finishTime: Long = System.currentTimeMillis()
        println("Thread time usage : " + finishTime.minus(initTime))
    }
    /* for (i in 1..100) {
         println("Function Scope :$i ")
         Thread.sleep(10L)
     }*/

    /*val manyThreads : List<Thread> = List(10){thread {
        for (i in 1..100) {
            println("Thread Scope :$i")
            Thread.sleep(10L*i)
        }
    }}
    manyThreads.forEach {
        it.run()
    }*/
}

fun performanceTestRunner(): Unit {
    val atomicInt: AtomicInteger = AtomicInteger()
    for (i in 1..100_000) {
        /*704082756*/
        thread {
            atomicInt.addAndGet(i)
        }

        /*705082704*/
/*        launch {
            atomicInt.addAndGet(i)
        }*/
    }
    println("Atomic finale ${atomicInt.get()}")
}

// Same 'launch{}' but have a callback value
fun asyncTestRunner() : Unit {
    val deferred = (1..1_000_000).map {
        async {
            println("$it")
           customWorkload(it)
        }
    }
    println(deferred[1000000 - 1].isCompleted)
    runBlocking {
        val sumOfN = deferred.sumBy {
            it.await()
        }
        println("sumOfN : "+sumOfN)
    }
}

suspend fun customWorkload(i : Int) : Int {
    delay(1000L) /* Can be called from suspend function or any function feature with 'suspend' */
    return i
}

fun awaitResponseTestRunner() : Unit{
    val defferedResponse  = async { getGoogletRawResponse() }
    async {
        println(defferedResponse.await())
    }
    println("-- END awaitResponseTestRunner --")
}
suspend fun getGoogletRawResponse() : String? {
    val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(Raw.GOOGLE)
            .build()
    val rawService : Raw = retrofit.create(Raw::class.java)
    return rawService.rawGoogle().execute().body()!!.string()
}