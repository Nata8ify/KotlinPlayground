package database.sql

fun main(argsss: Array<String>){
    val mysql = MYSQL()
    println(mysql.queryAll())
}