package database.sql

import java.sql.Connection
import java.util.*
import javax.swing.text.html.HTML.Tag.H2

val input: Scanner = Scanner(System.`in`)
val h2: H2 = H2()

fun main(args: Array<String>) { /*Main function running for H2 testing*/

    println("[1] Testing embedded mode or [2] Testing in-memory mode : ")
    val option: String = input.nextLine()
    when (option) {
        "1" -> h2Cache() /*Testing embedded mode*/
        "2" -> h2Memory() /*Testing in-memory mode*/
    }

}

fun h2Cache(): Unit {
    h2.initializeCacheSchema()
    while(true){
        println("Enter sample name... : ")
        var name  : String = input.nextLine();
        h2.insertName(name, h2.cConn)
        if(name == "x"){break}
    }
}

fun h2Memory(): Unit {
    h2.initializeInMemorySchema()
    while (true) {
        println("Enter sample name... : ")
        var name  : String = input.nextLine();
        h2.insertName(name, h2.mConn)
        if(name == "x"){break}
    }
}
