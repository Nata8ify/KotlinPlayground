package google.io2017

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import rest.client.Post
import java.io.FileReader
import java.math.BigDecimal

fun main(args: Array<String>) {
    var exMoney1 = Money(currency = "USD", balance = 100)
    var exMoney2 = exMoney1.copy(balance = 100)
    var exMoney3 = Money(balance = 100)
    var exMoney4 = Money(balance = 100, currency = "BAHT")
    if (exMoney1 == exMoney2) {
        println("It's Equal")
        if (exMoney1 != exMoney3) {
            println("It's true... ${exMoney1.currency} isn't equal ${exMoney3.currency}")
        }
    }

    var exMoney5: Money? = null
    println("Null Balance = ${exMoney5?.balance}")
//    println("[Shoot on the foot] Null Balance =  ${exMoney5!!.balance}")

    /** Elvis Operator : If exMoney5 is not-null then no value change or else use new Money instance */
//    exMoney5 = Money(balance = 100, currency = "EUR")
    exMoney5 = exMoney5?:Money(currency = "RUP", balance = 5_000)
    exMoney5?.let { println("exMoney5 is not null anymore... $it") }

    val convertBahtToUsdMoney = convertToUsd(exMoney4)
    println("Baht to Usd [$convertBahtToUsdMoney]")

    val instantBD: BigDecimal = 100000000.bd
    val instantStringBD: String = 100000000.str
    println("Is instantBD ($instantBD) a BigDecimal?... ${instantBD is BigDecimal}")

    var nullStr: String? = null
    var notNullStr: String? = "Kotlin"
    println("nullStr? ${nullStr?.length}")
    println("notNullStr!! ${notNullStr!!.length}")


    var moneys = listOf(Money(506000, "YEN"), Money(100, "RUP"), Money(8900, "BAHT"), Money(1000, "BAHT"), Money(256, "YEN"), Money(currency = "BAHT", balance = 1566))
    var predicateCurrency: (String) -> (Boolean) = { currency -> currency.contains("BAHT") }
    println(exHighOrderFunctionForNoBath(moneys, predicateCurrency))


    var yenMoneys = moneys.filter { it.currency.contains("YEN") }
            .sortedBy { it.balance }
    println(yenMoneys)

    var (_, currency) = yenMoneys.first()
    println("$currency")

    var readJson = FileReader("posts.json")
    var posts: List<Post> = Gson().fromJson(readJson, object : TypeToken<List<Post>>() {}.type)
    //println("Post from raw file : $posts")

    //Function type
    var devideBy: (Int, Int) -> Int? = { integer, divider ->
        println("Function type (Divide By) $integer/$divider")
        if (divider == 0) {
            println("Divide by 0 not allowed")
            null
        } else {
            integer / divider
        }
    }
    println(devideBy(15, 0)?.rem(2))

    //Type alias
    putToList(exMoney1);putToList(exMoney2);putToList(exMoney3);putToList(exMoney4)
    println(moneyList.toString())
    println(baht2UsdCurrency(exMoney4))

    //Extension function
    println("aqueous-thicket-56745".toBuilder().append(" - append 1").toString())
    print("Extension : exMoney1's  currency code -> ${exMoney1.toCurrency.currencyCode}")
    exMoney5.reset()
    println(exMoney5.reset())
}

//Type alias
typealias MoneyList = ArrayList<Money>
typealias Currency = (Money, String) -> Money?
private val moneyList: MoneyList by lazy { MoneyList() }
fun putToList(money: Money): Unit {
    moneyList.add(money)
}

fun baht2UsdCurrency(money: Money): Money? {
    val usdCurrency: Currency = { money, currency ->
        if (money.currency != "BAHT") {
            null
        } else {
            Money(money.balance.div(34), currency)
        }
    }
    return usdCurrency(money, "USD")
}


fun exHighOrderFunctionForNoBath(moneys: List<Money>, predicateCurrency: (String) -> (Boolean)): List<Money>? {
    var mutableMoneys = moneys.toMutableList()
    moneys.forEach {
        if (predicateCurrency(it.currency)) {
            println(it)
            mutableMoneys.remove(it)
        }
    }

    return mutableMoneys
}

fun convertToUsd(money: Money): Money = when (money.currency) {
    "USD" -> money
    "BAHT" -> Money(currency = "USD", balance = money.balance.times(34))
    else -> throw Exception("Currency not support")
}

private val Int.bd: BigDecimal
    get() {
        return this.toBigDecimal()
    }
private val Int.str: String
    get() {
        return this.toString()
    }

private val Money.toCurrency: java.util.Currency get(){ return java.util.Currency.getInstance(this.currency)}
fun Money.reset() : Money = Money()

fun String.toBuilder() : StringBuilder = StringBuilder(this)