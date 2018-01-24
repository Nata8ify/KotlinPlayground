package google.io2017

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import rest.client.Post
import java.io.FileReader
import java.math.BigDecimal

fun main(args: Array<String>){
    var exMoney1= Money(currency = "$", balance = 100)
    var exMoney2= exMoney1.copy(balance = 100)
    var exMoney3= Money(balance = 100)
    if(exMoney1 == exMoney2){
        println("It's Equal")
        if(exMoney1 != exMoney3){
            println("It's true... ${exMoney1.currency} isn't equal ${exMoney3.currency}")
        }
    }

    val instantBD : BigDecimal = 100000000.bd
    println("Is instantBD ($instantBD) a BigDecimal?... ${instantBD is BigDecimal}")

    var nullStr : String? = null
    var notNullStr : String? = "Kotlin"
    println("nullStr? ${nullStr?.length }")
    println("notNullStr!! ${notNullStr!!.length}")


    var moneys = listOf(Money(506000, "YEN"), Money(100, "RUP"), Money(8900, "BATH"), Money(1000, "BATH"), Money(256, "YEN"), Money(currency = "BATH", balance = 1566))
    var predicateCurrency : (String) -> (Boolean) = {currency -> currency.contains("BATH")}
    println(exHighOrderFunctionForNoBath(moneys, predicateCurrency))


    var yenMoneys = moneys.filter {it.currency.contains("YEN")}
            .sortedBy { it.balance }
    println(yenMoneys)

    var (_, currency) = yenMoneys.first()
    println("$currency")

    var readJson = FileReader("posts.json")
    var posts : List<Post> = Gson().fromJson(readJson, object : TypeToken<List<Post>>(){}.type)
    //println("Post from raw file : $posts")
}

fun exHighOrderFunctionForNoBath(moneys : List<Money>, predicateCurrency : (String) -> (Boolean)) : List<Money>? {
    var mutableMoneys =  moneys.toMutableList()
    moneys.forEach { if(predicateCurrency(it.currency)){
        println(it)
        mutableMoneys.remove(it)
    } }

    return mutableMoneys
}

private val Int.bd: BigDecimal get() { return this.toBigDecimal()}
