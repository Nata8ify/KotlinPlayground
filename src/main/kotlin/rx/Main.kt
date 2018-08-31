package rx

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tryy.kotlin.web.Genpass

val obsvbPasses : Observable<List<String>?> by lazy {
    val genpass = Retrofit.Builder()
            .baseUrl(Genpass.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Genpass::class.java)
/*    val resquest = genpass.generateAlphabetic(10, 6, 1).request()
    println(resquest.url().toString())*/
    val response = genpass.generateAlphabetic(10, 100, 1).execute()
    println(response.isSuccessful)
    println(response.code())
    Observable.fromArray(response.body())
}
val obsvrPasses : Observer<List<String>?> = object : Observer<List<String>?>{

    var passes : List<String>? = null

    override fun onNext(t: List<String>) {
        passes = (0 until t.size).map {
            it -> t[it]
        }
    }

    override fun onSubscribe(d: Disposable) {
        println("Subbed!")
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }

    override fun onComplete() {
//        passes?.joinToString(prefix = "[", postfix = "]", separator = "|")
        println(passes?.joinToString(prefix = "[", postfix = "]", separator = "|"))
        println("Done!")
    }
}
val obsvrPassesContainsA : Observer<List<String>?> = object : Observer<List<String>?>{

    var passes : List<String>? = null

    override fun onNext(t: List<String>) {
        passes = t.filter { pass -> pass.contains("A") }
    }

    override fun onSubscribe(d: Disposable) {
        println("Subbed!")
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }

    override fun onComplete() {
//        passes?.joinToString(prefix = "[", postfix = "]", separator = "|")
        println(passes?.joinToString(prefix = "[", postfix = "]", separator = "|"))
        println("Done!")
    }
}

fun main(args: Array<String>) {
    obsvbPasses.subscribeWith(obsvrPasses)
    println("----------------------------------------")
    obsvbPasses.subscribeWith(obsvrPassesContainsA)
}