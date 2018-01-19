package database.sql

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class H2{
    val cConn : Connection by lazy {
        Class.forName("org.h2.Driver")
        println(Paths.get("").toAbsolutePath())
        //println(System.getProperty("user.dir"))
        DriverManager.getConnection("jdbc:h2:"+Paths.get("").toAbsolutePath()+"/h2")
    }

    val mConn : Connection by lazy {
        Class.forName("org.h2.Driver")
        DriverManager.getConnection("jdbc:h2:mem:mh2")
    }


    fun initializeCacheSchema(){
        val h2File : File = File(Paths.get("h2.mv.db").toAbsolutePath().toString())
        if(!h2File.exists()){
            println("Creating Schema...")
            val sql : String = "CREATE TABLE sample(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(50));"
            cConn.prepareStatement(sql).use{
                it.execute()
                println("OK!")
                it.close()
            }
        } else {
            println("Initialed")

        }
    }

    fun initializeInMemorySchema(){
        val sql : String = "CREATE TABLE sample(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(50));"
        mConn.prepareStatement(sql).use{
            it.execute()
            println("OK!")
            it.close()
        }
    }

    fun insertName(name: String, conn : Connection){
        if(name == "x"){conn.close();return}
        conn.prepareStatement("INSERT INTO sample (NAME) VALUES (?);").use{
            it.setString(1, name)
            it.executeUpdate()
            it.close()
            println("Inserted..")
        }
        printAll(conn)
    }

    fun printAll(conn : Connection){
        conn.prepareStatement("SELECT * FROM sample;").use{
            val rs : ResultSet = it.executeQuery()
            while(rs.next()){
                println("${rs.getInt(1)} : ${rs.getString(2)}")
            }
            rs.close()
            it.close()
        }

    }

}