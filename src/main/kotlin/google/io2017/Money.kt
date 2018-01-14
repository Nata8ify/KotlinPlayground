package google.io2017

data class Money (val balance : Int, val currency : String){
    constructor(): this(0, "Bath")
    constructor(balance : Int): this(balance, "Bath")
}