package google.io2017

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


}

private val Int.bd: BigDecimal
    get() {
        return this.toBigDecimal()
    }
