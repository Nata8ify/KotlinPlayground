package tryy.kotlin

import google.io2017.Money

fun main(args: Array<String>) {
    var usdMoney: Money = Money(currency = "USD", balance = 34)
    var predictCurrency: (Money, String) -> Boolean = { money, currency -> money.currency == currency }

    /** make thing more harder*/
    predict(money = usdMoney, predictCurrency = predictCurrency, currency = "RUP")
    predict(money = usdMoney, currency = "USD", predictCurrency = { money: Money, currency: String -> println("Predict... ");money.currency == currency })

    var convertBahtToUsd1 = { bahtMoney: Money ->
        if (bahtMoney.currency == "BAHT") {
            Money(balance = bahtMoney.balance.div(32), currency = "USD")
        } else {
            println("NOT BAHT Money")
        }
    } //1
    var convertBahtToUsd2: (Money) -> Money = { bahtMoney -> Money(balance = bahtMoney.balance.div(32), currency = "USD") } //2
    println(convertBahtToUsd1(Money(currency = "YEN", balance = 153)))
    println(convertBahtToUsd2(Money(currency = "BAHT", balance = 32)))

    run {
        println("Run in run{...}")
    }

    val int100 : List<Int> = 1.rangeTo(100).asIterable().toList()
    var prime100 = int100.filter {
        return@filter (1..it).none { i -> i != 1 && i != it && it.rem(i) == 0 }
    }
    println(prime100)

    //Single Abstract Method
    var printer  = object  : Printer {
        override fun print() {
            println("Printing...")
        }
    }
    printer.print()
    var runnable : Runnable = Runnable { print("Just normal runnable run...") }
    runnable.run()

    val simpleInt : Int = 	2_147_483_647 //M31
    val simpleRemiderBy2Set : List<Int> = simpleInt.downTo(2_147_483_000).filter { return@filter it % 2 != 0 }
    println(simpleRemiderBy2Set)
    (0 .. 12 step 1).forEach {
        if(it !in 1..10){
            println(it * 2)
        }
    }
}

fun predict(money: Money, currency: String, predictCurrency: (pMoney: Money, pCurrency: String) -> Boolean) {
    if (predictCurrency(money, currency)) {
        println("Currency Matched!")
    } else {
        println("Currency Not Matched!")
    }
}

@FunctionalInterface
interface Printer {
    fun print() : Unit
}