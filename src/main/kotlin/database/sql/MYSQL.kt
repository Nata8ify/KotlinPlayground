package database.sql

import java.sql.Connection
import java.sql.DriverManager

class MYSQL{
    val conn : Connection by lazy {
        Class.forName("com.mysql.cj.jdbc.Driver")
        DriverManager.getConnection("jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=UTF-8", "root", "")
    }

    data class User(val id: Int, val emails: String, val password : String)
    fun queryAll(): List<User>?{
        val sql = "SELECT * FROM users;"
        val rs = conn.prepareStatement(sql).executeQuery()
        val users = rs.use{
            generateSequence { if(rs.next()){User(rs.getInt(1), rs.getString(2), rs.getString(3))} else {null} }.toList()
            //generateSequence -> http://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/generate-sequence.html
        }
        return users
    }
}