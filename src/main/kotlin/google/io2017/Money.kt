package google.io2017

data class Money (val balance : Int, val currency : String) : Comparable<Money> {
    constructor(): this(0, "BAHT")
    constructor(balance : Int): this(balance, "BAHT")

    override fun compareTo(other: Money): Int {
        return this.balance - other.balance
    }
}