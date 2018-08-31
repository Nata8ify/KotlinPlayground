package tryy.kotlin

import google.io2017.Money
import tryy.kotlin.model.Ticket
import java.util.*

class MoneyRange(override val start: Money, override val endInclusive: Money) : ClosedRange<Money>

fun main(args: Array<String>) {
    var bahtMoney = Money(currency = "BAHT", balance = 3400)
    var usDollarMoney = Money(currency = "USD", balance = 100)
    var dongMoney = Money(currency = "DONG", balance = 2426687)
    var yuanMoney = Money(currency = "YUAN", balance = 760)
    var yenMoney = Money(currency = "YEN", balance = 17000)
    var rupeeMoney = Money(currency = "RUP", balance = 7325)
    var euroMoney = Money(balance = 89, currency = "EUR")
    var (c, b) = euroMoney
    var euroCurrency = euroMoney.component2()
    println("Euro currency  : $c - $b")

    val moneyRange = MoneyRange(usDollarMoney, dongMoney)
    var moneys = Arrays.asList(usDollarMoney, bahtMoney, dongMoney, yuanMoney, yenMoney)

    val isContainsRupeeMoney: Boolean = rupeeMoney in moneys
    println(isContainsRupeeMoney)

    val start: Ticket = Ticket(100, 1)
    val end: Ticket = Ticket(200, 3)
    val rangeByGroup: Ticket = Ticket(101, 2)
    val rangeByNo: Ticket = Ticket(1002, 2)
    println("Is within group range = ${rangeByGroup in start..end}")
    println("Is within number range = ${rangeByNo in start..end}")


    val one2Three = 1..3
    val A2V = "a".."v"
    val predictAlphabetic = "w"
    println("Is $predictAlphabetic in range of $A2V ... ${predictAlphabetic in A2V}")
}

class TicketRange(override val endInclusive: Ticket, override val start: Ticket) : ClosedRange<Ticket>

operator fun Ticket.rangeTo(endTicket: Ticket) = TicketRange(this, endTicket)