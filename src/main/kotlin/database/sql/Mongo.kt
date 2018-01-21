package database.sql

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
class Jedi(val name: String , val age: Int)
class Mongo{
    val mongoURI : MongoClientURI by lazy {
        MongoClientURI("mongodb+srv://default:kogy9i8u@sample-f84xa.mongodb.net/test")
    }

    val mongoClient : MongoClient by lazy {
        MongoClient(mongoURI)
    }

    val mongoDatabase : MongoDatabase by lazy {
        mongoClient.getDatabase("sample")
    }
}